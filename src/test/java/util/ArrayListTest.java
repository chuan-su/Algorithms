package util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayListTest {
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void testRemove() {
        // Given
        list.add("Sweden"); list.add("China"); list.add("Japan");
        // When
        boolean removed = list.remove("Japan");
        // Then
        assertTrue(removed);
        assertEquals(list.size(),2);
    }
}
