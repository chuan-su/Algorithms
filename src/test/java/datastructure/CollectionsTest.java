package datastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class CollectionsTest {
  List<Integer> dataset;

  @Before
  public void setup() throws Exception {
     dataset = IntStream.range(0, 100).boxed().collect(Collectors.toList());
  }

  @Test
  public void testReverse() throws Exception {

    List<Integer> dataSetCopy = new ArrayList<>(dataset);
    java.util.Collections.reverse(dataSetCopy);

    Collections.reverse(dataset);
    assertEquals(dataset, dataSetCopy);
  }

  @Test
  public void testReverseRecursive() throws Exception {
    List<Integer> dataSetCopy = new ArrayList<>(dataset);
    java.util.Collections.reverse(dataSetCopy);

    Collections.reverseRecursive(dataset);
    assertEquals(dataset, dataSetCopy);
  }

}
