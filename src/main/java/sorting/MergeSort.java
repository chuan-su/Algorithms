package sorting;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Inserve on 13/04/17.
 */
public class MergeSort {

    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {  // Sort a[lo..hi].
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);       // Sort left half.
        sort(a, mid+1, hi);     // Sort right half.
        merge(a, lo, mid, hi);  // Merge results (code on page 271).
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi)
    {  // Merge a[lo..mid] with a[mid+1..hi].

        for (int k = lo; k <= hi; k++)  // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = a[k];

        int i = lo, j = mid+1;
        Stream.of(a).forEach(System.out::print);
        System.out.println();
        System.out.println(" " + mid);

        for (int k = lo; k <= hi; k++)  // Merge back to a[lo..hi].
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi )              a[k] = aux[i++];
            else if (a[j].compareTo(a[i]) <= 0){
                a[k] = aux[j++];
            }
            else{
                a[k] = aux[i++];
            }
    }

}
