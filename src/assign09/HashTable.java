package assign09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, V> implements Map<K, V> {

    private ArrayList<LinkedList<MapEntry<K, V>>> table;
    private int size;
    private int capacity;
    private double loadFactor;

    public HashTable() {
        capacity = 20;
        table = new ArrayList<>();
        for(int i = 0; i < capacity; i++)
            table.add(new LinkedList<MapEntry<K, V>>());
        size = 0;
        loadFactor = 0.0;
    }

    @Override
    public void clear() {
        table = new ArrayList<>(); //maybe change? interface docs says O(table length) instead of O(1)
        capacity = 20;
    }

    @Override
    public boolean containsKey(K key) {
        int index = findIndex(key);
        LinkedList<MapEntry<K, V>> list = table.get(index);
        for (MapEntry<K, V> mapEntry : list) {
            if (mapEntry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public List<MapEntry<K, V>> entries() {
        return null;
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);
        LinkedList<MapEntry<K, V>> list = table.get(index);
        for (MapEntry<K, V> mapEntry : list) {
            if (mapEntry.getKey().equals(key)) {
                return mapEntry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        int index = findIndex(key);
        MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
        LinkedList<MapEntry<K, V>> list = table.get(index);
        if (containsKey(key)) {
            V prevValue = remove(key);
            list.add(mapEntry);
            return prevValue;
        } else {
            list.add(mapEntry);
            return null;
        }
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private int findIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
}
