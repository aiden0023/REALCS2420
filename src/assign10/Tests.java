package assign10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A testing class for assignment 10.
 *
 * @author Aiden Fornalski
 * @version 2023-11-16
 */
public class Tests {
    private BinaryMaxHeap<Integer> integerHeap;
    private BinaryMaxHeap<String> stringHeap;

    @BeforeEach
    void setUp() {
        integerHeap = new BinaryMaxHeap<>();
        stringHeap = new BinaryMaxHeap<>(Comparator.reverseOrder());
    }

    @Test
    void testAddAndPeek() {
        integerHeap.add(30);
        integerHeap.add(10);
        integerHeap.add(50);

        assertEquals(50, integerHeap.peek());
        Assertions.assertEquals(3, integerHeap.size());
        Assertions.assertFalse(integerHeap.isEmpty());
    }

    @Test
    void testExtractMax() {
        integerHeap.add(30);
        integerHeap.add(10);
        integerHeap.add(50);

        assertEquals(50, integerHeap.extractMax());
        Assertions.assertEquals(2, integerHeap.size());
        assertEquals(30, integerHeap.peek());
    }

    @Test
    void testExtractMaxEmptyHeap() {
        assertThrows(NoSuchElementException.class, () -> integerHeap.extractMax());
    }

    @Test
    void testClear() {
        integerHeap.add(30);
        integerHeap.add(10);
        integerHeap.add(50);

        integerHeap.clear();

        assertTrue(integerHeap.isEmpty());
        assertEquals(0, integerHeap.size());
        assertThrows(NoSuchElementException.class, () -> integerHeap.peek());
    }

    @Test
    void testBuildHeap() {
        List<Integer> integerList = Arrays.asList(20, 30, 5, 10, 50);
        integerHeap = new BinaryMaxHeap<>(integerList);

        assertEquals(50, integerHeap.extractMax());
        assertEquals(30, integerHeap.extractMax());
        assertEquals(20, integerHeap.extractMax());
        assertEquals(10, integerHeap.extractMax());
        assertEquals(5, integerHeap.extractMax());
    }

    @Test
    void testBuildHeapWithComparator() {
        List<String> stringList = Arrays.asList("apple", "banana", "grape", "kiwi", "orange");
        stringHeap = new BinaryMaxHeap<>(stringList, Comparator.reverseOrder());

        Assertions.assertEquals("apple", stringHeap.extractMax());
        Assertions.assertEquals("banana", stringHeap.extractMax());
        Assertions.assertEquals("grape", stringHeap.extractMax());
        Assertions.assertEquals("kiwi", stringHeap.extractMax());
        Assertions.assertEquals("orange", stringHeap.extractMax());
    }

    @Test
    void testToArray() {
        integerHeap.add(30);
        integerHeap.add(10);
        integerHeap.add(50);

        Object[] array = integerHeap.toArray();
        for (int i = 0; i < array.length; i++) {
            assertArrayEquals(new Object[]{50, 10, 30}, array);
        }
    }

    @Test
    void testToArrayEmptyHeap() {
        Object[] array = integerHeap.toArray();
        assertArrayEquals(new Object[]{}, array);
    }

    @Test
    void testAddAndExtractMaxMultiple() {
        integerHeap.add(30);
        integerHeap.add(10);
        integerHeap.add(50);

        assertEquals(50, integerHeap.extractMax());

        integerHeap.add(25);
        integerHeap.add(30);
        integerHeap.add(40);

        assertEquals(40, integerHeap.extractMax());
        assertEquals(30, integerHeap.extractMax());
        assertEquals(30, integerHeap.extractMax());
        assertEquals(25, integerHeap.extractMax());
        assertEquals(10, integerHeap.extractMax());
    }
}
