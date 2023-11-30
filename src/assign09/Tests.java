package assign09;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for the HashTable class and StudentXHash classes.
 *
 * @author Aiden Fornalski
 * @version 2023-11-16
 */
public class Tests {

    private HashTable<String, Integer> hashTable = new HashTable<>();

    @Test
    public void putTest() {
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        assertEquals(10, hashTable.get("test"));
    }

    @Test
    public void putOverwriteTest() {
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        hashTable.put("test", 40);
        assertEquals(40, hashTable.get("test"));
    }

    @Test
    public void clearTest() {
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        hashTable.clear();
        assertEquals(0, hashTable.size());
        assertFalse(hashTable.containsKey("test"));
        assertFalse(hashTable.containsKey("test2"));
        assertFalse(hashTable.containsKey("test3"));
    }

    @Test
    public void containsTest() {
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        assertTrue(hashTable.containsKey("test2"));
        assertTrue(hashTable.containsValue(30));
        assertFalse(hashTable.containsKey("test5"));
        assertFalse(hashTable.containsValue(100));
    }

    @Test
    public void entriesTest() {
        LinkedList<MapEntry<String, Integer>> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            hashTable.put("test"+i, i);
            list.add(new MapEntry<>("test"+i, i));
        }
        List<MapEntry<String, Integer>> tableList = hashTable.entries();
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i).equals(tableList.get(i)));
        }
    }

    @Test
    public void getTest() {
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        assertEquals(30, hashTable.get("test3"));
    }

    @Test
    public void isEmptyTest() {
        assertTrue(hashTable.isEmpty());
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        assertFalse(hashTable.isEmpty());
        hashTable.clear();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void removeTest() {
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        assertEquals(10, hashTable.get("test"));
        assertTrue(hashTable.containsKey("test"));
        hashTable.remove("test");
        assertNull(hashTable.get("test"));
        assertFalse(hashTable.containsKey("test"));
    }

    @Test
    public void sizeTest() {
        hashTable.put("test", 10);
        hashTable.put("test2", 20);
        hashTable.put("test3", 30);
        assertEquals(3, hashTable.size());
    }

    @Test
    public void loadFactorTest() {
        for (int i = 0; i < 99; i++) {
            hashTable.put("test"+i, i);
        }
    }
}
