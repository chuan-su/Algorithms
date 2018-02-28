package util;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
public class CollectionsTest {
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
    Optional<Integer> result = Collections.binarySearch(dataset, key, Integer::compareTo);
    // Then
    assertThat(result.isPresent(), is(true));
    assertThat(result.get(), is(key));
  }

  @Test
  public void testIndexedBinarySearch() throws Exception {
    // Given
    int key = 75;
    // When
    int index = Collections.indexedBinarySearch(dataset, key, Integer::compareTo);
    // Then
    assertThat(dataset.get(index), is(key));
  }
}
