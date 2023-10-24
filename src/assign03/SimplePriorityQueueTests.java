package assign03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Tests for the SimplePriorityQueue class
 *
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-09-07
 */

public class SimplePriorityQueueTests {

    Comparator<String> compString = Comparator.comparing(String::toString);
    private SimplePriorityQueue<Integer> simplePriorityQueue = new SimplePriorityQueue<>();
    private SimplePriorityQueue<Integer> intPriorityQueue = new SimplePriorityQueue<>();
    private SimplePriorityQueue<String> stringPriorityQueue = new SimplePriorityQueue<>();
    ArrayList<Integer> intColl = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        intPriorityQueue.insert(4);
        intPriorityQueue.insert(2);
        intPriorityQueue.insert(1);

        stringPriorityQueue.insert("apple");
        stringPriorityQueue.insert("cat");
        stringPriorityQueue.insert("hello");
        stringPriorityQueue.insert("world");
        stringPriorityQueue.insert("zoo");

        intColl.add(1);
        intColl.add(2);
        intColl.add(3);
    }

    @Test
    public void testEmptyFindMax(){
        simplePriorityQueue.insert(5);
        assertEquals(5, (int) simplePriorityQueue.findMax());
    }

    @Test
    public void testEmptyIsEmpty() {
        Assertions.assertTrue(simplePriorityQueue.isEmpty());
        simplePriorityQueue.insert(5);
        assertFalse(simplePriorityQueue.isEmpty());
    }

    @Test
    public void testEmptyInsert() {
        simplePriorityQueue.insert(7);
        assertTrue(simplePriorityQueue.contains(7));
    }

    @Test
    public void testEmptyInsertAll() {
        simplePriorityQueue.insertAll(intColl);
        assertEquals(3, intColl.size());
    }

    @Test
    public void testEmptyContains() {
        assertFalse(simplePriorityQueue.contains(2));
    }

    @Test
    public void testEmptySize() {
    simplePriorityQueue.size();
    }

    @Test
    public void testEmptyClear() {
        simplePriorityQueue.clear();
    }


   // int tests
   @Test
   public void testIntFindMax(){
       assertEquals(4, (int) intPriorityQueue.findMax());
   }

    @Test
    public void testIntIsEmpty() {
        assertFalse(intPriorityQueue.isEmpty());
    }

    @Test
    public void testIntInsert() {
        intPriorityQueue.insert(3);
        assertTrue(intPriorityQueue.contains(3));
    }

    @Test
    public void testIntContains() {
        assertTrue(intPriorityQueue.contains(2));
    }

    @Test
    public void testIntSize() {
        intPriorityQueue.size();
    }

    @Test
    public void testIntClear() {
        intPriorityQueue.clear();
    }


    //String tests
    @Test
    public void testStringFindMax(){
        assertEquals("zoo", stringPriorityQueue.findMax());
    }

    @Test
    public void testStringIsEmpty() {
        assertFalse(stringPriorityQueue.isEmpty());
    }

    @Test
    public void testStringInsert() {
        stringPriorityQueue.insert("dad");
        assertTrue(stringPriorityQueue.contains("dad"));
    }

    @Test
    public void testStringContains() {
        assertTrue(stringPriorityQueue.contains("world"));
    }

    @Test
    public void testStringSize() {
        stringPriorityQueue.size();
    }

    @Test
    public void testStringClear() {
        stringPriorityQueue.clear();
    }
}
