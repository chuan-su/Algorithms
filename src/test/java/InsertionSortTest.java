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

        String[] a = {"Z","F","E","B","H"};
        final Comparator<String> com = (s1, s2) -> s1.compareTo(s2);

        InsertionSort.sort(a,com);

        Stream.of(a).reduce((prev, curr) -> {
            assertTrue(com.compare(prev,curr) < 0);
            return curr;
        });
    }
}
