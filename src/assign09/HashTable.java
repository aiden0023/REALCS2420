package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A hashtable class that utilizes a backing arraylist for storage and separate
 * chaining for collision handling.
 *
 * @param <K> - key
 * @param <V> - value
 * @author Aiden Fornalski
 * @version 2023-11-16
 */
public class HashTable<K, V> implements Map<K, V> {

    private ArrayList<LinkedList<MapEntry<K, V>>> table;
    private int size;
    private int capacity;
    private double loadFactor;
    private double LOAD_FACTOR_LIMIT = 5.0;
    private int collisions;

    public HashTable() {
        capacity = 20;
        table = new ArrayList<>();
        for (int i = 0; i < capacity; i++) { //populating hashtable with linked lists
            table.add(new LinkedList<MapEntry<K, V>>());
        }
        size = 0;
        loadFactor = 0.0;
        collisions = 0;
    }

    @Override
    public void clear() {
        table = new ArrayList<>(); //new backing arraylist
        capacity = 20;
        size = 0;
        loadFactor = 0.0;
        for (int i = 0; i < capacity; i++) { //populating hashtable with linked lists
            table.add(new LinkedList<MapEntry<K, V>>());
        }
    }

    @Override
    public boolean containsKey(K key) {
        int index = findIndex(key); //hashed index
        LinkedList<MapEntry<K, V>> list = table.get(index); //grabbing linked list from hashed index
        for (MapEntry<K, V> mapEntry : list) { //iterating through linked list to find key
            if (mapEntry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (LinkedList<MapEntry<K, V>> list : table) { //iterates through hashtable
            for (MapEntry<K, V> mapEntry : list) { //iterates through list at each hashed index to find value
                if (mapEntry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<MapEntry<K, V>> entries() {
        LinkedList<MapEntry<K, V>> list = new LinkedList<>(); //creates return list
        for (LinkedList<MapEntry<K, V>> linkedList : table) { //iterates through entire hashtable
            list.addAll(linkedList); //adds each map entry to return list
        }
        return list;
    }

    @Override
    public V get(K key) {
        int index = findIndex(key); //hashed index
        LinkedList<MapEntry<K, V>> list = table.get(index); //grabbing linked list from hashed index
        for (MapEntry<K, V> mapEntry : list) { //iterates through linked list to grab key-value pair
            if (mapEntry.getKey().equals(key)) {
                return mapEntry.getValue(); //return value from found map entry
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0; //return boolean of if size is 0
    }

    @Override
    public V put(K key, V value) {
        if (loadFactor >= LOAD_FACTOR_LIMIT) { //if the load factor is >= LOAD_FACTOR_LIMIT (5.0)
            for (int i = 0; i < capacity; i++) { //increases amount of indexes by capacity
                table.add(new LinkedList<MapEntry<K, V>>());
            }
            capacity = capacity*2; //reassign new capacity value (doubled of previous capacity)
        }

        int index = findIndex(key); //hashed index
        MapEntry<K, V> mapEntry = new MapEntry<>(key, value); //create new map entry object with key-value pair
        LinkedList<MapEntry<K, V>> list = table.get(index); //grabbing linked list from hashed index
        if (containsKey(key)) { //if key already exists in hashtable, overwrite the value associated with the key
            V prevValue = remove(key); //storing temp value to return
            list.addFirst(mapEntry);
            size++;
            loadFactor = calculateLoadFactor(size, capacity); //recalculate current load factor
            return prevValue;
        } else {
            list.add(mapEntry);
            size++;
            loadFactor = calculateLoadFactor(size, capacity); //recalculate current load factor
            return null;
        }
    }

    @Override
    public V remove(K key) {
        int index = findIndex(key); //hashed index
        LinkedList<MapEntry<K, V>> list = table.get(index); //grabbing linked list from hashed index
        V prevValue = null; //creating temp value to return
        Iterator<MapEntry<K, V>> iterator = list.iterator(); //create iterator of the list
        while (iterator.hasNext()) {
            MapEntry<K, V> mapEntry = iterator.next(); //iterate through list
            if (mapEntry.getKey().equals(key)) {
                prevValue = mapEntry.getValue(); //reassign temp value
                iterator.remove();
                size--;
                loadFactor = calculateLoadFactor(size, capacity); //recalculate current load factor
            }
        }
        return prevValue;
    }

    @Override
    public int size() {
        return size; //return amount of items in the hashtable (items = map entries)
    }

    /**
     * Finds the hash of a key.
     *
     * @param key - key to hash
     * @return - index of key
     */
    private int findIndex(K key) {
        return Math.abs(key.hashCode()) % capacity; //calculating the hashed index
    }

    /**
     * Calculates the current load factor.
     *
     * @param size - amount of items in hashtable
     * @param capacity - capacity of hashtable
     * @return - load factor
     */
    private double calculateLoadFactor(int size, int capacity) {
        return (double) size/capacity; //calculating the load factor
    }
}
