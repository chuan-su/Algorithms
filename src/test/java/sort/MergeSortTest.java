package sort;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MergeSortTest {
  @Test
  public void MergeSort(){

    /* Given
     * As a not, Arrays.asList("Z","F","E","B","H") will not work
     * as the list retured by 'Arrays' utility is immutable and its remove() will
     * throw an UnsupportedOperationException.
     * https://stackoverflow.com/questions/6260113/unsupportedoperationexception-in-abstractlist-remove-when-operating-on-arrayli
     */
    List<String> a = Stream.of("Z", "F", "E", "B", "Z", "A", "C", "N", "M").collect(Collectors.toList());

    // When
    MergeSort.mergeSort(a, String::compareTo);

    // Then
    a.stream().reduce((prev, curr) -> {
      assertTrue(prev.compareTo(curr) <= 0);
      return curr;
    });

    List<String> expected = Stream.of("A","B","C","E","F", "M", "N", "Z","Z").collect(Collectors.toList());
    assertEquals(expected, a);
  }
}
