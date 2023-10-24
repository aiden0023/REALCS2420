package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * A test class for assignment 06
 * @author Aiden Fornalski
 * @version 2023-10-19
 */
public class Tests {
    SinglyLinkedList<Integer> list;
    Integer[] listAsArray;
    SinglyLinkedList<Integer> emptyList;
    LinkedListStack<Integer> stack;
    LinkedListStack<Integer> emptyStack;
    WebBrowser browser;
    WebBrowser emptyBrowser;
    SinglyLinkedList<URL> browserList;

    @BeforeEach
    public void setup() throws MalformedURLException {
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

        browserList = new SinglyLinkedList<>();
        browserList.insertFirst(new URL("https://a"));
        browserList.insertFirst(new URL("https://b"));
        browserList.insertFirst(new URL("https://c"));
        browserList.insertFirst(new URL("https://d"));
        browser = new WebBrowser(browserList);

        emptyBrowser = new WebBrowser();
    }

    //SinglyLinkedList Tests

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
    public void oobInsertTest() {
        assertThrows(IndexOutOfBoundsException.class, ()-> {emptyList.insert(5, 5);});
    }

    @Test
    public void getFirstTest() {
        assertEquals((int)list.getFirst(), 14);
    }

    @Test
    public void emptyGetFirstTest() {
        assertThrows(NoSuchElementException.class, ()-> {emptyList.getFirst();});
    }

    @Test
    public void getTest() {
        assertEquals((int)list.get(2), 5);
    }

    @Test
    public void emptyGetTest() {
        assertThrows(IndexOutOfBoundsException.class, ()-> {emptyList.get(5);});
    }

    @Test
    public void deleteFirstTest() {
        assertEquals((int)list.deleteFirst(), 14);
        assertEquals((int)list.getFirst(), 10);
    }

    @Test
    public void emptyDeleteFirstTest() {
        assertThrows(NoSuchElementException.class, ()-> {emptyList.deleteFirst();});
    }

    @Test
    public void deleteTest() {
        assertEquals((int)list.delete(3), 17);
        assertEquals((int)list.get(3), 4);
    }

    @Test
    public void emptyDeleteTest() {
        assertThrows(IndexOutOfBoundsException.class, ()-> {emptyList.delete(5);});
    }

    @Test
    public void indexOfTest() {
        assertEquals(list.indexOf(17), 3);
    }

    @Test
    public void dneIndexOfTest() {
        assertEquals(-1, emptyList.indexOf(1));
    }

    @Test
    public void sizeListTest() {
        assertEquals(list.size(), 6);
    }

    @Test
    public void isEmptyListTest() {
        assertTrue(emptyList.isEmpty());
    }

    @Test
    public void clearTest() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void toArrayTest() {
        Object[] newArray = list.toArray();
        assertArrayEquals(newArray, listAsArray);
    }

    @Test
    public void hasNextIteratorTest() {
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void nextIteratorTest() {
        Iterator<Integer> iterator = list.iterator();
        assertEquals(14, (int)iterator.next());
    }

    @Test
    public void emptyNextIteratorTest() {
        Iterator<Integer> iterator = emptyList.iterator();
        assertThrows(NoSuchElementException.class, ()-> {iterator.next();});
    }

    @Test
    public void removeIteratorTest() {
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(10, (int)list.get(0));
    }

    @Test
    public void emptyRemoveIteratorTest() {
        Iterator<Integer> iterator = list.iterator();
        assertThrows(NoSuchElementException.class, ()-> {iterator.remove();});
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
    public void emptyPeekTest() {
        assertThrows(NoSuchElementException.class, ()-> {emptyStack.peek();});
    }

    @Test
    public void emptyPopTest() {
        assertThrows(NoSuchElementException.class, ()-> {emptyStack.pop();});
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

    //WebBrowser Tests

    @Test
    public void visitTest() throws MalformedURLException {
        browser.visit(new URL("https://new"));
        assertEquals("https://d", browser.back().toString());
        assertEquals("https://new", browser.forward().toString());
    }

    @Test
    public void backTest() {
        assertEquals("https://c", browser.back().toString());
    }

    @Test
    public void emptyBackTest() {
        assertThrows(NoSuchElementException.class, ()-> {emptyBrowser.back();});
    }

    @Test
    public void forwardTest() {
        assertEquals("https://c", browser.back().toString());
        assertEquals("https://d", browser.forward().toString());
    }

    @Test
    public void emptyForwardTest() {
        assertThrows(NoSuchElementException.class, ()-> {emptyBrowser.forward();});
    }

    @Test
    public void historyTest() {
        Object[] historyArray = browser.history().toArray();
        assertArrayEquals(browserList.toArray(), historyArray);
    }

    @Test
    public void histroySizeTest() throws MalformedURLException {
        emptyBrowser.visit(new URL("https://a"));
        emptyBrowser.visit(new URL("https://b"));
        emptyBrowser.visit(new URL("https://c"));
        assertEquals(3, emptyBrowser.history().size());
    }
}
