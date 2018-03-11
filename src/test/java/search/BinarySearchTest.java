package search;

import org.junit.Before;
import org.junit.Test;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class BinarySearchTest {
  List<Integer> dataset;

  @Before
  public void setup() throws Exception {
    dataset = IntStream.range(0, 100).boxed().collect(Collectors.toList());
  }

  @Test
  public void testBinarySearch() throws Exception {
    // Given
    int key = 29;
    // When
    Optional<Integer> result = BinarySearch.binarySearch(dataset, key, Integer::compareTo);
    // Then
    assertThat(result.isPresent(), is(true));
    assertThat(result.get(), is(key));
  }

  @Test
  public void testIndexedBinarySearch() throws Exception {
    // Given
    int key = 75;
    // When
    int index = BinarySearch.indexedBinarySearch(dataset, key, Integer::compareTo);
    // Then
    assertThat(dataset.get(index), is(key));
  }

  @Test
  public void testIndexedBinarySearchCornerCase() throws Exception {
    // Given
    int key = 0;
    // When
    int index = BinarySearch.indexedBinarySearch(dataset, key, Integer::compareTo);
    // Then
    assertThat(dataset.get(index), is(key));
  }

  @Test
  public void testIndexedBinarySearchCornerCase2() throws Exception {
    // Given
    int key = 99;
    // When
    int index = BinarySearch.indexedBinarySearch(dataset, key, Integer::compareTo);
    // Then
    assertThat(dataset.get(index), is(key));
  }

  @Test
  public void testIndexedBinarySearchNotFound() throws Exception {
    // Given
    int key = 111;
    // When
    int index = BinarySearch.indexedBinarySearch(dataset, key, Integer::compareTo);
    // Then
    assertEquals(-1, index);
  }

}
