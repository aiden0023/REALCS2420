package assign10;

import org.junit.Ignore;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class that creates a BinaryMaxHeap object. The binary heap is backed by a
 * priority queue array, in which the root starts at index 0.
 *
 * @param <E> - generic type
 * @author Aiden Fornalski
 * @version 2023-11-16
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

    E[] heap;
    int size;
    Comparator<? super E> comparator;

    @SuppressWarnings("unchecked")
    public BinaryMaxHeap() {
        this.heap = (E[]) new Object[20];
        this.size = 0;
        this.comparator = null;
    }

    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(Comparator<? super E> comparator) {
        this.heap = (E[]) new Object[20];
        this.size = 0;
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list) {
        this.heap = (E[]) list.toArray();
        this.size = list.size();
        this.comparator = null;
        buildHeap(); //builds heap from list
    }

    @SuppressWarnings("unchecked")
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> comparator) {
        this.heap = (E[]) list.toArray();
        this.comparator = comparator;
        this.size = list.size();
        buildHeap(); //builds heap from list
    }

    @Override
    public void add(E item) {
        if (size == heap.length) { //check to see if backing array needs to be resized
            heap = resize();
        }

        if (isEmpty()) {
            heap[0] = item; //insert item to index 0 (root) if heap is empty
        } else { //normal insertion
            heap[size] = item; //adds item to next empty index (leaf) in backing array (tree)
            percolateUp(size); //resort
        }
        size++;
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return heap[0]; //root/max is at index 0 always
        }
    }

    @Override
    public E extractMax() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        E max = heap[0]; //temp storage for return
        heap[0] = heap[size-1]; //last item in backing array moved to root index
        percolateDown(0); //resort
        size--;
        return max;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0; //if size is 0, return true
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        heap = (E[]) new Object[20]; //new backing array
        size = 0; //resetting size
    }

    @Override
    public E[] toArray() {
        @SuppressWarnings("unchecked")
        E[] array = (E[]) new Object[size]; //new array to return
        System.arraycopy(heap, 0, array, 0, array.length); //copy backing array to return array
        return array;
    }

    /**
     * Resizes the heap by doubling the length of the array.
     *
     * @return - resized array
     */
    private E[] resize() {
        @SuppressWarnings("unchecked")
        E[] newArr = (E[]) new Object[heap.length*2]; //new array doubled the size of backing array
        System.arraycopy(heap, 0, newArr, 0, heap.length); //copy backing array to new doubled array
        return newArr;
    }

    /**
     * Basic swap method for swapping items in the backing array.
     *
     * @param i - index of item
     * @param j - index of item
     */
    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Simple method that correctly compares items in the backing array
     * according to if the object has a set comparator or not.
     *
     * @param i - index of item
     * @param j - index of item
     * @return - output of the compare function
     */
    private int innerCompare(int i, int j) {
        if (comparator != null) {
            return comparator.compare(heap[i], heap[j]); //use given comparator
        } else {
            @SuppressWarnings("unchecked")
            Comparable<? super E> comparable = (Comparable<? super E>) heap[i];
            return comparable.compareTo(heap[j]); //use natural comparator
        }
    }

    /**
     * A method that resorts the backing array after inserting an item
     * by percolating up.
     *
     * @param index - starting index of percolation
     */
    private void percolateUp(int index) {
        while (index > 0) { //loops up to root
            int parentIndex = (index - 1) / 2;
            if (innerCompare(index, parentIndex) >= 0) { //if item at index is >= item at parentIndex
                swap(index, parentIndex);
                index = parentIndex; //new starting index
            } else {
                break;
            }
        }
    }

    /**
     * A method that resorts the backing array after inserting a list of
     * items or extracting the max value from the heap by percolating down.
     *
     * @param index
     */
    private void percolateDown(int index) {
        int root = index;
        int child = 2 * index + 1;

        while (child < size) { //while child index is not out of bounds of size of backing array
            if (child < size-1 && innerCompare(child, child+1) <= 0) {
                child++; //switch to adjacent child
            }

            if (innerCompare(child, root) >= 0) { //if item at child index is >= item at root index
                swap(child, root);
                root = child; //new root
                child *= 2; //new child
            } else {
                break;
            }
            child++; //adjusting for new child index
        }
    }

    /**
     * Method to build a heap given a list of items. Called in some
     * constructors for this object.
     */
    private void buildHeap() {
        int root = (size/2)-1; //starting root
        while (root >= 0) {
            percolateDown(root); //sort
            root--; //decrement
        }
    }
}
