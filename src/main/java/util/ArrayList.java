package util;

import java.util.Arrays;

public class ArrayList<E>
    implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData;

    private int size;

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public boolean add(E e) {
        if (size == elementData.length)
            elementData = grow();
        elementData[size] = e;
        size++;
        return true;
    }

    public void add(int index, E element) {
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                         elementData, index + 1,
                        s - index);
        elementData[index] = element;
        size++;
    }

    public void remove(int index) {
        final Object[] elementData = this.elementData;
        final int newSize;
        if ((newSize = (size -1)) - index > 0)
            System.arraycopy(elementData, index + 1,
                             elementData, index,
                            newSize - index);
        elementData[size = newSize] = null;
    }

    public boolean remove(Object o) {
        final Object[] elementData = this.elementData;
        final int size = this.size;
        int i = 0;
        found: {
            if (o == null) {
                for (; i < size; i++)
                    if(elementData[i] == null)
                        break found;
            }else {
                for (; i < size; i++)
                    if(o.equals(elementData[i]))
                        break found;
            }
            return false;
        }
        remove(i);
        return true;
    }

    public int size() {
        return this.size;
    }

    private Object[] grow() {
        return elementData = Arrays.copyOf(elementData,newCapacity(size + 1));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) { // elementData.length == 0, which means elementData is empty
            return minCapacity;
        }
        return newCapacity;
    }

}
