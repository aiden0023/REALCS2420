package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GradescopeTestsForBugFixes {

    SimplePriorityQueue<Integer> queue4096 = new SimplePriorityQueue<>();
    SimplePriorityQueue<Integer> queue5 = new SimplePriorityQueue<>();
    SimplePriorityQueue<Integer> insertAllQueue5 = new SimplePriorityQueue<>();
    Collection<Integer> collInsertAllQueue5 = new ArrayList<>();
    SimplePriorityQueue<Integer> emptyQueue = new SimplePriorityQueue<>();
    Collection<Integer> collInsertAllQueue4096 = new ArrayList<>();
    SimplePriorityQueue<Integer> insertAllQueue4096 = new SimplePriorityQueue<>();
    SimplePriorityQueue<Integer> queue4096Comparator = new SimplePriorityQueue<>(new CustomComparator());
    SimplePriorityQueue<Integer> queue5Comparator = new SimplePriorityQueue<>(new CustomComparator());
    SimplePriorityQueue<Integer> insertAllQueue5Comparator = new SimplePriorityQueue<>(new CustomComparator());
    SimplePriorityQueue<Integer> insertAllQueue4096Comparator = new SimplePriorityQueue<>(new CustomComparator());

    public class CustomComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    @BeforeEach
    public void setup() {
        for (int i = 1; i <= 4096; i++) {
            queue4096.insert(i);
            queue4096Comparator.insert(i);
            collInsertAllQueue4096.add(i);
        }

        collInsertAllQueue5.add(1);
        collInsertAllQueue5.add(2);
        collInsertAllQueue5.add(3);
        collInsertAllQueue5.add(4);
        collInsertAllQueue5.add(5);

        queue5.insert(1);
        queue5.insert(2);
        queue5.insert(3);
        queue5.insert(4);
        queue5.insert(5);

        queue5Comparator.insert(1);
        queue5Comparator.insert(2);
        queue5Comparator.insert(3);
        queue5Comparator.insert(4);
        queue5Comparator.insert(5);
    }

    @Test
    public void emptyInsertAllComparable() {
        emptyQueue.insertAll(collInsertAllQueue5);
        assertFalse(emptyQueue.isEmpty());
        assertEquals(5, emptyQueue.size());
        assertEquals(5, emptyQueue.findMax());
    }

    @Test
    public void findMaxOn4096ItemQueueComparator() {
        assertEquals(1, queue4096Comparator.findMax());
    }

    @Test
    public void findMaxAfterDeleteMax5ItemQueueComparable() {
        queue5.deleteMax();
        assertEquals(4, queue5.findMax());
    }

    @Test
    public void findMaxAfterDeleteMax5ItemQueueComparator() {
        queue5Comparator.deleteMax();
        assertEquals(2, queue5Comparator.findMax());
    }

    @Test
    public void size0WhenDeleteAllOn5ItemQueueComparable() {
        insertAllQueue5.insertAll(collInsertAllQueue5);
        insertAllQueue5.deleteMax();
        insertAllQueue5.deleteMax();
        insertAllQueue5.deleteMax();
        insertAllQueue5.deleteMax();
        insertAllQueue5.deleteMax();
        assertEquals(0, insertAllQueue5.size());
    }

    @Test
    public void size0WhenDeleteAllOn5ItemQueueComparator() {
        insertAllQueue5Comparator.insertAll(collInsertAllQueue5);
        insertAllQueue5Comparator.deleteMax();
        insertAllQueue5Comparator.deleteMax();
        insertAllQueue5Comparator.deleteMax();
        insertAllQueue5Comparator.deleteMax();
        insertAllQueue5Comparator.deleteMax();
        assertEquals(0, insertAllQueue5Comparator.size());
    }

    @Test
    public void deleteMaxDeletesAllInOrderOn5ItemQueueComparable() {
        insertAllQueue5.insertAll(collInsertAllQueue5);
        insertAllQueue5.deleteMax();
        assertEquals(4, insertAllQueue5.findMax());
        insertAllQueue5.deleteMax();
        assertEquals(3, insertAllQueue5.findMax());
        insertAllQueue5.deleteMax();
        assertEquals(2, insertAllQueue5.findMax());
        insertAllQueue5.deleteMax();
        assertEquals(1, insertAllQueue5.findMax());
        insertAllQueue5.deleteMax();
        assertEquals(0, insertAllQueue5.size());
    }

    @Test
    public void deleteMaxDeletesAllInOrderOn5ItemQueueComparator() {
        insertAllQueue5Comparator.insertAll(collInsertAllQueue5);
        insertAllQueue5Comparator.deleteMax();
        assertEquals(2, insertAllQueue5Comparator.findMax());
        insertAllQueue5Comparator.deleteMax();
        assertEquals(3, insertAllQueue5Comparator.findMax());
        insertAllQueue5Comparator.deleteMax();
        assertEquals(4, insertAllQueue5Comparator.findMax());
        insertAllQueue5Comparator.deleteMax();
        assertEquals(5, insertAllQueue5Comparator.findMax());
        insertAllQueue5Comparator.deleteMax();
        assertEquals(0, insertAllQueue5Comparator.size());
    }

    @Test
    public void emptyContainsComparable() {
        assertFalse(emptyQueue.contains(5));
    }

    @Test
    public void isEmptyAfterDeletingAll4096ItemQueueComparable() {
        insertAllQueue4096.insertAll(collInsertAllQueue4096);
        for (int i = 0; i < 4096; i++) {
            insertAllQueue4096.deleteMax();
        }
        assertTrue(insertAllQueue4096.isEmpty());
    }

    @Test
    public void isEmptyAfterDeletingAll4096ItemQueueComparator() {
        insertAllQueue4096Comparator.insertAll(collInsertAllQueue4096);
        for (int i = 0; i < 4096; i++) {
            insertAllQueue4096Comparator.deleteMax();
        }
        assertTrue(insertAllQueue4096Comparator.isEmpty());
    }

    @Test
    public void deleteMaxDeletesAllInOrder4096ItemQueueComparable() {
        insertAllQueue4096.insertAll(collInsertAllQueue4096);
        for (int i = 4096; i > 1; i--) {
            insertAllQueue4096.deleteMax();
            assertEquals(i-1, insertAllQueue4096.findMax());
        }
        insertAllQueue4096.deleteMax();
        assertEquals(0, insertAllQueue4096.size());
    }

    @Test
    public void deleteMaxDeletesAllInOrder4096ItemQueueComparator() {
        insertAllQueue4096Comparator.insertAll(collInsertAllQueue4096);
        for (int i = 1; i < 4096; i++) {
            insertAllQueue4096Comparator.deleteMax();
            assertEquals(i+1, insertAllQueue4096Comparator.findMax());
        }
        insertAllQueue4096Comparator.deleteMax();
        assertEquals(0, insertAllQueue4096Comparator.size());
    }

    @Test
    public void isEmptyAfterDeletingAll5ItemQueueComparable() {
        insertAllQueue5.insertAll(collInsertAllQueue5);
        for (int i = 0; i < 5; i++) {
            insertAllQueue5.deleteMax();
        }
        assertTrue(insertAllQueue5.isEmpty());
    }

    @Test
    public void isEmptyAfterDeletingAll5ItemQueueComparator() {
        insertAllQueue5Comparator.insertAll(collInsertAllQueue5);
        for (int i = 0; i < 5; i++) {
            insertAllQueue5Comparator.deleteMax();
        }
        assertTrue(insertAllQueue5Comparator.isEmpty());
    }

    @Test
    public void containsMissingElementOn4096ItemQueueComparator() {
        assertFalse(queue4096Comparator.contains(4097));
    }

    @Test
    public void containsMissingElementAfterDeleteMax5ItemQueueComparable() {
        queue5.deleteMax();
        assertFalse(queue5.contains(5));
    }

    @Test
    public void containsMissingElementAfterDeleteMax5ItemQueueComparator() {
        queue5Comparator.deleteMax();
        assertFalse(queue5Comparator.contains(1));
    }

    @Test
    public void size0AfterDeletingAll4096ItemQueueComparable() {
        insertAllQueue4096.insertAll(collInsertAllQueue4096);
        for (int i = 0; i < 4096; i++) {
            insertAllQueue4096.deleteMax();
        }
        assertEquals(0, insertAllQueue4096.size());
    }

    @Test
    public void size0AfterDeletingAll4096ItemQueueComparator() {
        insertAllQueue4096Comparator.insertAll(collInsertAllQueue4096);
        for (int i = 0; i < 4096; i++) {
            insertAllQueue4096Comparator.deleteMax();
        }
        assertEquals(0, insertAllQueue4096Comparator.size());
    }

    @Test
    public void checkSizeAfterEveryDelete4096ItemQueueComparable() {
        insertAllQueue4096.insertAll(collInsertAllQueue4096);
        for (int i = 4096; i > 0; i --) {
            insertAllQueue4096.deleteMax();
            assertEquals(i-1, insertAllQueue4096.size());
        }
    }

    @Test
    public void checkSizeAfterEveryDelete4096ItemQueueComparator() {
        insertAllQueue4096Comparator.insertAll(collInsertAllQueue4096);
        for (int i = 4096; i > 0; i --) {
            insertAllQueue4096Comparator.deleteMax();
            assertEquals(i-1, insertAllQueue4096Comparator.size());
        }
    }

    @Test
    public void deletesInOrder4096ItemQueueComparable() {
        insertAllQueue4096.insertAll(collInsertAllQueue4096);
        for (int i = 4096; i > 0; i --) {
            assertEquals(i, insertAllQueue4096.deleteMax());
        }
    }

    @Test
    public void deletesInOrder4096ItemQueueComparator() {
        insertAllQueue4096Comparator.insertAll(collInsertAllQueue4096);
        for (int i = 1; i <= 4096; i++) {
            assertEquals(i, insertAllQueue4096Comparator.deleteMax());
        }
    }
}
