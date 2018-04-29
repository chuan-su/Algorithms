package sort;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MergeSortForkJoinTest {

  @Test
  public void MergeSort(){
    //Given
    List<String> a = Stream.of("Z", "F", "E", "B", "Z", "A", "C", "N", "M").collect(Collectors.toList());
    // When
    MergeSortForkJoin.sort(a, String::compareTo);
    // Then
    a.stream().reduce((prev, curr) -> {
      assertTrue(prev.compareTo(curr) <= 0);
      return curr;
    });

    List<String> expected = Stream.of("A","B","C","E","F", "M", "N", "Z","Z").collect(Collectors.toList());
    assertEquals(expected, a);
  }
}
