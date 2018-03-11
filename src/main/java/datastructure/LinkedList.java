package datastructure;

public class LinkedList<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    public LinkedList() {}

    public boolean add(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(last,element,null);
        last = newNode;
        if(l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        return true;
    }

    public int indexOf(Object o) {
        int index = 0;
        found: {
            if (o == null) {
                for (Node<E> x = first; x != null; x = x.next, index++)
                    if (x.element == null)
                        break found;
            } else {
                for (Node<E> x = first; x != null; x = x.next, index++)
                    if (o.equals(x.element))
                        break found;
            }
            return -1;
        }
        return index;
    }

    private Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for(int i = 0; i < index; i++)
                x = x.next;
            return x;
        }
        else {
            Node<E> x = last;
            for(int i = size; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }
    }
}
