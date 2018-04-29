package sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HeapSortTest {

  @Test
  public void testHeapSort() {
    // Given
    List<Integer> dataset = Arrays.asList(8, 3, 5, 4, 9, 6, 2, 10);
    // When
    HeapSort.sort(dataset, Integer::compareTo);
    // Then
    assertThat(dataset, is(Arrays.asList(2, 3, 4, 5, 6, 8, 9, 10)));
  }
}
