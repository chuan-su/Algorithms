import java.util.Comparator;

/**
 * Created by suchuan on 2017-04-12.
 */

public class InsertionSort {

    /**
     * For each i from 0 to N-1, exchange a[i] with the entries that are smaller in a[0] through a[i-1].
     * As the index i travels from left to right, the entries to its left are in sorted order in the array,
     * so the array is fully sorted when i reaches the right end.
     */

    public static <T> void sort(T[] a, Comparator com) {
        // Sort a[] into increasing order.
        int n = a.length;
        for(int i = 1; i < n; i++){
            // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
            for(int j = i; j > 0 && com.compare(a[j],a[j-1]) < 0; j --) swap(j,j-1,a);
        }
    }
    private static <T> void swap(int i, int j, T[] a)
    {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
