package util;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Collections {
  private Collections() {}

  public static <T>
  void reverse(List<T> list) {
    int low = 0;
    int high = list.size() -1 ;
    while(low < high) {
      swap(list, low, high);
      low ++;
      high --;
    }
  }

  /* recursive approach */
  public static <T>
  void reverseRecursive(List<T> list) {
    reverseRange(list, 0, list.size() - 1);
  }

  /* JDK source code */
  public static <T>
  int indexedBinarySearchJDK(List<? extends T> l, T key, Comparator<? super T> c) {
    int low = 0;
    int high = l.size()-1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      T midVal = l.get(mid);
      int cmp = c.compare(midVal, key);

      if (cmp < 0)
        low = mid + 1;
      else if (cmp > 0)
        high = mid - 1;
      else
        return mid; // key found
    }
    return -(low + 1);  // key not found
  }

  /* recursive approach */
  public static <T>
  int indexedBinarySearch(List<T> list, T key, Comparator<T> comp) {
    return indexedBinarySearch(list, key, comp, 0, list.size() - 1 );
  }

  private static <T> int indexedBinarySearch(List<T> list, T key, Comparator<T> comp, int low, int high) {
    if(low > high) return -1;

    int mid = (high + low) >>> 1;
    int result = comp.compare(key, list.get(mid));

    if(result > 0)
      return indexedBinarySearch(list, key, comp, mid + 1, high);
    else if(result < 0)
      return indexedBinarySearch(list, key, comp, low, mid -1);
    else
      return mid;
  }

  private static <T> void reverseRange(List<T> list, int low, int high) {
    if(low < high) {
      swap(list, low, high);
      reverseRange(list, low+1, high -1);
    }
  }

  private static <T> void swap(List<T> list, int i, int j) {
    T tmp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, tmp);
  }

  /* return search result direct */
  public static <T>
  Optional<T> binarySearch(List<T> list, T key, Comparator<T> comp) {
    if(list.isEmpty()) return Optional.empty();
    int mid = list.size() >> 1;

    int result = comp.compare(key, list.get(mid));
    if(result > 0)
      return binarySearch(list.subList(mid + 1, list.size()), key, comp);
    else if(result < 0)
      return binarySearch(list.subList(0, mid), key, comp);
    else
      return Optional.of(list.get(mid));
  }
}
