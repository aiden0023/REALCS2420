package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class Tests {
    SinglyLinkedList<Integer> list;
    Integer[] listAsArray;
    SinglyLinkedList<Integer> emptyList;
    LinkedListStack<Integer> stack;
    LinkedListStack<Integer> emptyStack;

    @BeforeEach
    public void setup() {
        list = new SinglyLinkedList<>();
        list.insertFirst(6);
        list.insertFirst(4);
        list.insertFirst(17);
        list.insertFirst(5);
        list.insertFirst(10);
        list.insertFirst(14);

        listAsArray = new Integer[] {14, 10, 5, 17, 4, 6};

        emptyList = new SinglyLinkedList<>();

        stack = new LinkedListStack<>();
        stack.push(5);
        stack.push(23);
        stack.push(12);
        stack.push(9);
        stack.push(4);
        stack.push(15);

        emptyStack = new LinkedListStack<>();
    }

    @Test
    public void insertFirstTest() {
        list.insertFirst(12);
        assertEquals((int)list.getFirst(), 12);
    }

    @Test
    public void insertTest() {
        list.insert(2, 25);
        assertEquals((int)list.get(2), 25);
    }

    @Test
    public void getFirstTest() {
        assertEquals((int)list.getFirst(), 14);
    }

    @Test
    public void getTest() {
        assertEquals((int)list.get(2), 5);
    }

    @Test
    public void deleteFirstTest() {
        assertEquals((int)list.deleteFirst(), 14);
        assertEquals((int)list.getFirst(), 10);
    }

    @Test
    public void deleteTest() {
        assertEquals((int)list.delete(3), 17);
        assertEquals((int)list.get(3), 4);
    }

    @Test
    public void indexOfTest() {
        assertEquals(list.indexOf(17), 3);
    }

    @Test
    public void sizeListTest() {
        assertEquals(list.size(), 6);
    }

    @Test
    public void isEmptyListTest() {
        assertTrue(emptyList.isEmpty());
    }

    /*@Test
    public void clearTest() {
        list.clear();
        assertTrue(list.isEmpty());
    }*/

    @Test
    public void toArrayTest() {
        Object[] newArray = list.toArray();
        assertArrayEquals(newArray, listAsArray);
    }

    //LinkedListStack Tests

    @Test
    public void clearStackTest() {
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void isEmptyStackTest() {
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    public void peekTest() {
        assertEquals((int)stack.peek(), 15);
    }

    @Test
    public void popTest() {
        assertEquals((int)stack.pop(), 15);
        assertEquals((int)stack.peek(), 4);
    }

    @Test
    public void pushTest() {
        stack.push(99);
        assertEquals((int)stack.peek(), 99);
    }

    @Test
    public void sizeStackTest() {
        assertEquals(stack.size(), 6);
    }

    @Test
    public void
}
