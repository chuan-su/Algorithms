package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortForkJoin<T> {

  private MergeSortForkJoin() {}

  public static <T> void sort(List<T> elements, Comparator<T> comparator) {
    ForkJoinPool pool = ForkJoinPool.commonPool();

    pool.invoke(new MergeSortTask<T>(elements, comparator));
  }

  private static class MergeSortTask<T> extends RecursiveAction {
    private List<T> elements;
    private Comparator<T> comparator;

    MergeSortTask(List<T> elements, Comparator<T> comparator) {
      this.elements = elements;
      this.comparator = comparator;
    }

    @Override
    protected void compute() {
      int size = elements.size(), mid = size >> 1;

      if (mid == 0) return;

      List<T> subl1 = new ArrayList<>();
      List<T> subl2 = new ArrayList<>();

      for(int i = 0; i < mid; i++)
        subl1.add(elements.remove(0));

      while (!elements.isEmpty())
        subl2.add(elements.remove(0));

      RecursiveAction.invokeAll(new MergeSortTask<>(subl1, comparator), new MergeSortTask<>(subl2, comparator));
      merge(subl1, subl2);
    }

    void merge(List<T> subl1, List<T> subl2) {
      while (!subl1.isEmpty() && !subl2.isEmpty()) {
        if (comparator.compare(subl1.get(0), subl2.get(0)) < 0)
          elements.add(subl1.remove(0));
        else
          elements.add(subl2.remove(0));
      }
      while (!subl1.isEmpty()) {
        elements.add(subl1.remove(0));
      }
      while (!subl2.isEmpty()) {
        elements.add(subl2.remove(0));
      }
    }
  }
}
