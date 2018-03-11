package search;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BinarySearch {

  private BinarySearch() {}

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
