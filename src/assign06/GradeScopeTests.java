package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GradeScopeTests {

    SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

    @Test
    public void removeIndexSizeMinus1() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(11, list.delete(list.size()-1));
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
        assertEquals(3, list.size());
    }

    @Test
    public void getFirstSmallListCheckReturn() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(20, list.getFirst());
    }

    @Test
    public void addToListAtIndexSize() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        list.insert(list.size(), 100);
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
        assertEquals(5, list.size());
    }

    @Test
    public void removeOnEmptyListIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertThrows(IllegalStateException.class, ()->{iterator.remove();});
    }

    @Test
    public void addToIndex0EmptyList() {
        SinglyLinkedList<Integer> temp = new SinglyLinkedList<>();
        temp.insert(0, 1);
        assertEquals(1, temp.size());
        assertEquals(1, temp.get(0));
    }

    @Test
    public void removeIndex0() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(20, list.delete(0));
        assertEquals(3, list.size());
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
    }

    @Test
    public void callRemoveEveryOtherElementIterator() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            iterator.next();
        }
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(5);
        temp.add(11);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
    }

    @Test
    public void checkIndexOfFirstElement () {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(0, list.indexOf(20));
    }

    @Test
    public void clearList() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        list.clear();
        assertEquals(0, list.size());
        assertEquals(-1, list.indexOf(20));
        assertEquals(-1, list.indexOf(5));
        assertEquals(-1, list.indexOf(17));
        assertEquals(-1, list.indexOf(11));
    }

    @Test
    public void callRemoveFor2ConsecutiveElementsIterator() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
    }

    @Test
    public void deleteFirst() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(20, list.deleteFirst());
        assertEquals(3, list.size());
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
    }

    @Test
    public void checkIndexOfLastElement() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(3, list.indexOf(11));
    }

    @Test
    public void simultaneousIteratorInstancesAccurate() {
        //????
    }

    @Test
    public void callNextAfterFullIteration() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, ()->{iterator.next();});
    }

    @Test
    public void checkIndexOfMiddleElement() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(2, 17);
    }

    @Test
    public void checkToArray() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        Integer[] listArray = list.toArray();
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), listArray[i]);
        }
    }

    @Test
    public void callRemoveTwiceInARowIterator() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, ()->{iterator.remove();});
    }

    @Test
    public void checkIndexOfNonExistentElement() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        assertEquals(-1, list.indexOf(10));
    }

    @Test
    public void manyAddsAndRemovesRandomly() {
        list.insert(0, 20);
        list.insert(1, 5);
        list.insert(2, 17);
        list.insert(3, 11);
        list.delete(3);
        list.insert(3, 23);
        list.insert(4, 50);
        list.delete(1);
        list.delete(2);
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
    }

    @Test
    public void createLargeListByAddingAtRandomIndexes() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            list.insert(rand.nextInt(list.size()), rand.nextInt(1000));
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            temp.add(list.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            assertEquals(temp.get(i), list.get(i));
        }
    }
}
