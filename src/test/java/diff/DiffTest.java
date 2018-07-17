package diff;

import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DiffTest {
  private List<Person> existingDataSet, newDataSet;
  private List<Person> toInsert, toUpdate, toDelete;
  private int size = 100000;

  @Before
  public void setup() {
    toInsert = new ArrayList<>();
    toUpdate = new ArrayList<>();
    toDelete = new ArrayList<>();

    existingDataSet = IntStream.range(0, size)
      .mapToObj(i -> new Person(i, "name_" + i))
      .collect(toList());

    newDataSet = existingDataSet.stream()
      .map(person -> new Person(person.id, person.name))
      .collect(toList());

    // remove 10000 data
    IntStream.range(0, 10000)
      .map(i -> ThreadLocalRandom.current().nextInt(0, newDataSet.size()))
      .forEach(i -> {
        // To Delete
        Person personToDelete = newDataSet.remove(i);
        toDelete.add(personToDelete);
      });

    // Update around 5000 data
    Set<Integer> indexes =
      IntStream.range(0, 5000).mapToObj(i -> ThreadLocalRandom.current().nextInt(0, newDataSet.size())).collect(toSet());

    indexes.stream()
      .mapToInt(Integer::intValue)
      .forEach(index -> {
        Person personToUpdate = newDataSet.get(index);
        personToUpdate.name = "updatedName_"+ index;
        toUpdate.add(personToUpdate);
      });

    // Add 999 new data
    IntStream.range(100, 1000)
      .forEach(i -> {
        // To Insert
        Person newPerson = new Person((size + i), "NewName_" + i);
        newDataSet.add(newPerson);
        toInsert.add(newPerson);
      });
  }

  @Test
  public void testDiff() {
    // When
    DiffResult<Person> result = Diff.compute(existingDataSet, newDataSet, Comparator.comparingInt(c1 -> c1.id));

    // Then
    assertEquals(toInsert.size(), result.getToInsert().size());
    assertEquals(toUpdate.size(), result.getToUpdate().size());
    assertEquals(toDelete.size(), result.getToDelete().size());

    assertTrue(toInsert.containsAll(result.getToInsert()));
    assertTrue(toUpdate.containsAll(result.getToUpdate()));
    assertTrue(toDelete.containsAll(result.getToDelete()));

  }
}
