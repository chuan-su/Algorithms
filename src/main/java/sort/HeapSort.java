package sort;

import java.util.Comparator;
import java.util.List;

public class HeapSort {

  private HeapSort() {}

  public static <T> void
  sort(List<T> elements, Comparator<T> comparator) {
    heapify(elements, comparator); // Construct heap.
    // Sort down
    for (int i = elements.size() - 1; i > 0; i--) {
      exchange(elements, 0, i); // similar to Selection sort. Put maximum value to the end of array.
      siftDown(elements, 0, (i - 1), comparator); // repair heap so that element at 0 is maximum
    }
  }
  // Construct heap starting from last parent node n/2 - 1
  private static <T> void heapify(List<T> elements, Comparator<T> comparator) {
    int i = (elements.size() >>> 1) - 1; // The last parent node.
    for(; i >= 0; i--)
      siftDown(elements, i, (elements.size() - 1), comparator);
  }

  // Compare with child left node. 2n + 1
  private static <T> void siftDown(List<T> elements, int k, int n, Comparator<T> comparator) {
    int next;
    while ((next = (k << 1) + 1) <= n) { // child next left node in the heap.
      if (next < n && less(elements, next, next + 1, comparator)) next ++;
      if(!less(elements, k, next, comparator)) break;
      exchange(elements, k, next);
      k = next;
    }
  }

  private static <T> boolean less(List<T> elements, int k, int j, Comparator<T> comparator) {
    return comparator.compare(elements.get(k), elements.get(j)) < 0;
  }

  private static <T> void exchange(List<T> elements, int k, int j) {
    T tmp = elements.set(k, elements.get(j));
    elements.set(j, tmp);
  }
}
