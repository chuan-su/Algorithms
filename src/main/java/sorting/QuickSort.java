package sorting;

import java.util.Comparator;
import java.util.List;

public class QuickSort<E> {
  private QuickSort() {}

  public static <E> void
  sort(List<E> elements, Comparator<E> comp) {
    sort(elements, comp,0, elements.size() - 1);
  }

  private static <E> void sort(List<E> elements, Comparator<E> comp, int low, int high) {
    if(low > high) return;
    int l = low, r = high - 1;
    E pivot = elements.get(high);
    while (l < r) {
      while (l <= r && comp.compare(elements.get(l), pivot) < 0) l ++;
      while (r >= l && comp.compare(elements.get(r), pivot) > 0) r --;
      if(l < r) {
        E tmp = elements.get(l);
        elements.set(l, elements.get(r));
        elements.set(r, tmp);
      }
    }
    E tmp = elements.get(l);
    if(comp.compare(tmp, pivot) > 0) {
      elements.set(l, pivot);
      elements.set(high, tmp);
    }

    sort(elements, comp, low, l - 1);
    sort(elements, comp, l + 1, high);
  }
}
