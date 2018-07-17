package diff;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import java.util.BitSet;

@ThreadSafe
class DiffMarker {

  private final int existingDataSetSize, newDataSetSize;

  @GuardedBy("toInsert") private final BitSet toInsert;
  @GuardedBy("toUpdate") private final BitSet toUpdate;
  @GuardedBy("toDelete") private final BitSet toDelete;

  DiffMarker(int existingDataSetSize, int newDataSetSize) {
    this.existingDataSetSize = existingDataSetSize;
    this.newDataSetSize = newDataSetSize;

    this.toInsert = new BitSet(newDataSetSize);
    this.toUpdate = new BitSet(newDataSetSize);

    this.toDelete = new BitSet(existingDataSetSize);
    this.toDelete.flip(0, existingDataSetSize);
  }

  void markToInsert(int index) {
    if (index < 0 || index >= newDataSetSize) {
      throw new ArrayIndexOutOfBoundsException("Cannot mark to Insert, index out of bound");
    }
    synchronized (toInsert) {
      toInsert.set(index);
    }
  }

  void markToUpdate(int index) {
    if (index < 0 || index >= newDataSetSize) {
      throw new ArrayIndexOutOfBoundsException("Cannot mark to Update, index out of bound");
    }
    synchronized (toUpdate) {
      toUpdate.set(index);
    }
  }

  void markToKeep(int index) {
    if (index < 0 || index >= existingDataSetSize) {
      throw new ArrayIndexOutOfBoundsException("Cannot mark to Keep, index out of bound");
    }
    synchronized (toDelete) {
      toDelete.clear(index);
    }
  }

  BitSet getToInsertMarks() {
    BitSet bitSet;
    synchronized (toInsert) {
      bitSet = (BitSet)toInsert.clone();
    }
    return bitSet;
  }

  BitSet getToUpdateMarks() {
    BitSet bitSet;
    synchronized (toUpdate) {
      bitSet = (BitSet)toUpdate.clone();
    }
    return bitSet;
  }

  BitSet getToDeleteMarks() {
    BitSet bitSet;
    synchronized (toDelete) {
      bitSet = (BitSet)toDelete.clone();
    }
    return bitSet;
  }
}
