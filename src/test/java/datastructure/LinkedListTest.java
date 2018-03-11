package datastructure;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LinkedListTest {

    private LinkedList<String> list;

    @Before
    public void setup() {
        list = new LinkedList<>();
    }

    @Test
    public void testIndexOf() {
        // Given
        list.add("Sweden");
        list.add("China");
        list.add("Japan");
        // When
        int index = list.indexOf("China");
        int notFound = list.indexOf("USA");
        // Then
        assertEquals(1, index);
        assertEquals( -1, notFound);
    }
}
