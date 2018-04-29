package sort;

import org.junit.Test;

import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * Created by suchuan on 2017-04-12.
 */
public class InsertionSortTest {
  @Test
  public void InsertionSort() {
    // Given
    String[] a = {"Z","F","E","B","H"};
    // When
    InsertionSort.sort(a, String::compareTo);
    // Then
    Stream.of(a).reduce((prev, curr) -> {
      assertTrue(prev.compareTo(curr) < 0);
      return curr;
    });
  }
}
