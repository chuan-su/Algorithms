import java.util.Comparator;

/*
 * Selection Sort
 */
public class SelectionSort {

    public static <T> void sort(T[] a, Comparator<T> com){
        // Sort a[] into increasing order.
        int n = a.length;
        for(int i = 0; i < n ; i++){
            for(int j = i+1; j < n; j ++){
                if(com.compare(a[j],a[i]) < 0) swap(i,j,a);
            }
        }
    }
    private static <T> void swap(int i, int j, T[] a){
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
