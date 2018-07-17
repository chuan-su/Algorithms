package sort;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;

public class Test {

  @org.junit.Test
  public void test() {
    final int[] a = {0};
    CompletableFuture.runAsync(() -> {
      a[0] = a[0] +1;
    }).join();
    System.out.println(a[0]);
  }
}
