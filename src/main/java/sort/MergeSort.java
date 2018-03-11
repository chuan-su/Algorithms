package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
  private MergeSort(){}

  public static <T>
  void mergeSort(List<T> elements, Comparator<T> comp) {
    int size = elements.size(), mid = size >> 1;

    if (mid == 0) return;

    List<T> subl1 = new ArrayList<>(mid);
    List<T> subl2 = new ArrayList<>(elements.size() - mid);

    int i = 0;
    while (i < mid) {
      subl1.add(elements.remove(0));
      i++;
    }
    while (!elements.isEmpty()) subl2.add(elements.remove(0));

    mergeSort(subl1, comp);
    mergeSort(subl2, comp);
    merge(subl1, subl2, elements, comp);
  }

  private static <T> void merge(List<T> subl1, List<T> subl2, List<T> l, Comparator<T> comp) {
    while (!subl1.isEmpty() && !subl2.isEmpty()) {
      if(comp.compare(subl1.get(0), subl2.get(0)) <= 0)
        l.add(subl1.remove(0));
      else
        l.add(subl2.remove(0));
    }
    while (!subl1.isEmpty()) l.add(subl1.remove(0));
    while (!subl2.isEmpty()) l.add(subl2.remove(0));
  }
}
