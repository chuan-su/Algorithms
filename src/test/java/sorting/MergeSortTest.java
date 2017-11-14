package sorting;

import org.junit.Test;
import sorting.MergeSort;

import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * Created by Inserve on 13/04/17.
 */
public class MergeSortTest {
    @Test
    public void MergeSort(){
        String[] a = {"Z","F","E","B","H"};
        assertTrue("Z".compareTo("H") > 0);
        MergeSort.sort(a);
        Stream.of(a).forEach(System.out::print);
        Stream.of(a).reduce((prev, curr) -> {
            assertTrue(prev.compareTo(curr) <= 0);
            return curr;
        });
    }
}
