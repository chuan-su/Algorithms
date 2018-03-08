package sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuickSortTest {

  @Test
  public void testQuickSort() throws Exception {
    // Given
    List<String> dataset = Arrays.asList("C","H","O","A","E","N","M","P","B");
    // When
    QuickSort.sort(dataset, String::compareTo);
    // Then
    List<String> expected = Arrays.asList("A", "B", "C", "E", "H", "M", "N", "O", "P");
    assertEquals(expected, dataset);
  }

  @Test
  public void testQuickSortWithDuplicates() throws Exception {
    // Given
    List<String> dataset = Arrays.asList("C","H","O","A","E","E","M","H","B","F", "Z");
    // When
    QuickSort.sort(dataset, String::compareTo);
    // Then
    List<String> expected = Arrays.asList("A", "B", "C", "E", "E", "F", "H", "H", "M", "O", "Z");
    assertEquals(expected,dataset);
  }
}
