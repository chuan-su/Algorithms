package diff;

import java.util.List;
import java.util.Objects;

public class DiffResult<E> {

  private final List<E> toInsert, toUpdate, toDelete;

  DiffResult(List<E> toInsert, List<E> toUpdate, List<E> toDelete) {
    this.toInsert = Objects.requireNonNull(toInsert);
    this.toUpdate = Objects.requireNonNull(toUpdate);
    this.toDelete = Objects.requireNonNull(toDelete);
  }

  public List<E> getToInsert() {
    return toInsert;
  }

  public List<E> getToUpdate() {
    return toUpdate;
  }

  public List<E> getToDelete() {
    return toDelete;
  }
}
