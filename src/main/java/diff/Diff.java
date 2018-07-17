package diff;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static java.util.stream.Collectors.toList;

public class Diff<E> {
  private final DiffMarker diffMarker;

  private final Comparator<E> comparator;
  private final List<E> existingDataSet, newDataSet;

  private Diff(List<E> existingDataSet, List<E> newDataSet, Comparator<E> comparator) {
    this.existingDataSet = existingDataSet;
    this.newDataSet = newDataSet;
    this.comparator = comparator;

    this.diffMarker = new DiffMarker(existingDataSet.size(), newDataSet.size());
  }

  private DiffResult<E> computeDiffResult() {
    ForkJoinPool pool = ForkJoinPool.commonPool();
    pool.invoke(new DiffAction(0, newDataSet.size()));

    List<E> toInsert = diffMarker.getToInsertMarks().stream().mapToObj(newDataSet::get).collect(toList());
    List<E> toUpdate = diffMarker.getToUpdateMarks().stream().mapToObj(newDataSet::get).collect(toList());
    List<E> toDelete = diffMarker.getToDeleteMarks().stream().mapToObj(existingDataSet::get).collect(toList());

    return new DiffResult<>(toInsert, toUpdate, toDelete);
  }

  private class DiffAction extends RecursiveAction {
    private static final int sThreshold = 100;

    private int high, low;

    DiffAction(int low, int high) {
      this.low = low;
      this.high = high;
    }

    @Override
    protected void compute() {
      if ((high - low) < sThreshold) {
        computeDirectly(low, high);
        return;
      }
      int middle = (high + low) >> 1;
      invokeAll(new DiffAction(low, middle), new DiffAction(middle, high));
    }

    private void computeDirectly(int low, int high) {
      for (int i = low; i < high; i ++) {
        E newData = newDataSet.get(i);
        int index = Collections.binarySearch(existingDataSet, newData, comparator);
        if (index < 0) {
          diffMarker.markToInsert(i);
          continue;
        }
        if (!Objects.equals(newData, existingDataSet.get(index))) {
          diffMarker.markToUpdate(i);
        }
        diffMarker.markToKeep(index);
      }
    }
  }

  public static <E> DiffResult<E> compute(List<E> existingDataSet, List<E> newDataSet, Comparator<E> comparator) {
    // Sort existing data set first.
    List<E> sortedExistingDateSet = existingDataSet.stream().sorted(comparator).collect(toList());

    Diff<E> diff = new Diff<>(sortedExistingDateSet, newDataSet, comparator);

    return diff.computeDiffResult();
  }
}
