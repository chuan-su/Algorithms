package datastructure;

import java.util.List;

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
}
