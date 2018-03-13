package datastructure;

import java.util.NoSuchElementException;

public class ArrayDeque<E> {

  private E[] elements;

  /* when head == tail, the array needs to grow to its double original size. */
  private int head, tail;

  public ArrayDeque() {
    this.elements = (E[])new Object[16];
  }

  private void doubleCapacity() {
    assert head == tail;
    int p = head;
    int n = elements.length; // number of elements to right
    int r = n - p;
    int newCapacity = elements.length << 1;
    E[] newElements = (E[])new Object[newCapacity];

    /* elements to the right copied to new array head starting from 0*/
    System.arraycopy(elements, p, newElements, 0, r);
    /* elements to the left copied to new array tail = oldarrary length */
    System.arraycopy(elements, 0, newElements, r, p);
    /* Afterwards, the first 16 elements of new array with length 32 is fileld the old array elements (length 16) */

    head = 0;
    tail = n;
  }

  public void addFirst(E e) {
    if (e == null)
      throw new NullPointerException();
    /* using bitwise and operator makes sure head is always within the range 0..element.length -1
     * has same effect as dec method below */
    head = (head - 1) & (elements.length - 1);
    elements[head] = e;
  }

  public void addLast(E e) {
    if (e == null)
      throw new NullPointerException();

    elements[tail] = e;
    tail = (tail + 1) & (elements.length -1);
    /* using bitwise and operator makes sure tail is always within the range 0..element.length -1
     * has same effect as inc method below */
  }

  public E pollFirst(E e) {
    int h = head;
    E result = elements[h];

    if (result == null)
      return null;

    elements[h] = null;
    head = (h + 1) & (elements.length - 1);
    return result;
  }

  public E pollLast(E e) {
    int t = (tail - 1) & (elements.length - 1) ;
    E result = elements[t];

    if (result == null)
      return null;

    elements[t] = null;
    tail = t;
    return result;
  }

  public E peekFirst() {
    /* head points to index of first element */
    return elements[head];
  }

  public E peekLast() {
    /* tail points to a null value whose previous element is actual the last.
     * therefore we need to first decriment tail in order to get the last element.
     */
    int t = (tail - 1) & (elements.length - 1);
    return elements[t];
  }

  public E getFirst() {
    E result = peekFirst();
    if (result == null)
      throw new NullPointerException();
    return result;
  }

  public E getLast() {
    E result = peekLast();
    if (result == null)
      throw new NullPointerException();
    return result;
  }

  public boolean offerFirst(E e) {
    addFirst(e);
    return true;
  }

  public boolean offerLast(E e) {
    addLast(e);
    return true;
  }

  public E removeFirst(E e) {
    E result = pollFirst(e);
    if (result == null)
      throw new NoSuchElementException();

    return result;
  }

  public E removeLast(E e) {
    E result = pollLast(e);
    if (result == null)
      throw new NoSuchElementException();

    return result;
  }

  private int dec(int i, int modulus) {
    if (--i < 0) i = modulus - 1;
    return i;
  }

  private int inc(int i, int modulus) {
    if (++ i >= modulus) i = 0;
    return i;
  }
}
