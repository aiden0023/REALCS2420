package assign03;

import java.util.*;

/**
 * A simple priority queue that supports access of the maximum element only.
 *
 * @author Aiden Fornalski and Henry Sippel
 * @version 2023-09-07
 *
 * @param <E> - the type of elements contained in this priority queue
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>, Iterable<E> {

    private E[] queue;
    private int size;
    private Comparator<? super E> cmp;

    public SimplePriorityQueue() {
        queue = (E[]) new Object[20];
        size = 0;
        cmp = null;
    }

    public SimplePriorityQueue(Comparator<? super E> cmp) {
        queue = (E[]) new Object[20];
        size = 0;
        this.cmp = cmp;
    }

    @Override
    public E findMax() throws NoSuchElementException {
        if (this.isEmpty()) { //run empty check
            throw new NoSuchElementException();
        } else {
            return queue[size-1];
        }
    }

    @Override
    public E deleteMax() throws NoSuchElementException {
        if (this.isEmpty()) { //run empty check
            throw new NoSuchElementException();
        } else {
            E max = findMax();
            queue[--size] = null;
            return max;
        }
    }

    @Override
    public void insert(E item) {
        if (size == queue.length) {
            enlargeQueue();
        }
        int index = findInsertionIndex(item);
        shiftQueue(index, 1);
        queue[index] = item;
        size++;
    }

    @Override
    public void insertAll(Collection<? extends E> coll) {
        E[] collArray = (E[]) coll.toArray();
        for (int i = 0; i < coll.size(); i++) {
            insert(collArray[i]);
        }
    }

    @Override
    public boolean contains(E item) {
        int index = binarySearch(item);
        return index >= 0 && index < size && queue[index].equals(item);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        queue = (E[]) new Object[20];
        size = 0;
    }

    private int binarySearch(E target) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            E midItem = queue[mid];

            if (midItem == null) {
                return -(mid + 1);
            }

            int cmp = compare(target, midItem);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -(left + 1);
    }

    /**
     * Controls what compareTo method is used in methods above.
     *
     * @param o1 - object 1
     * @param o2 - object 2
     * @return - result of compareTo method
     */
    private int compare(E o1, E o2) {
        if (cmp == null) { //default comparator
            return ((Comparable<? super E>) o1).compareTo(o2);
        } else { //custom comparator
            return cmp.compare(o1, o2);
        }
    }

    /**
     * Shifts the items in the queue.
     * @param startIndex - the starting index of the shift
     * @param distance - how many indexes should the items be shifted over by
     */
    private void shiftQueue(int startIndex, int distance) {
        System.arraycopy(queue, startIndex, queue, startIndex + distance, size - startIndex);
    }

    /**
     * Doubles the size of the queue by 2.
     */
    private void enlargeQueue() {
        int newCapacity = queue.length * 2;
        queue = Arrays.copyOf(queue, newCapacity);
    }

    /**
     * Finds the index of where the item should be inserted into the queue utilizing
     * binary search.
     * @param item - item to be inserted
     * @return - the index of where the item should be inserted
     */
    private int findInsertionIndex(E item) {
        int index = binarySearch(item);
        if (index >= 0) {
            return index;
        } else {
            return -(index+1);
        }
    }

    //TEST HELPER METHODS
    public E[] getQueue() {
        return queue;
    }

    public void setQueue(E[] queue) {
        this.queue = queue;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    public class CustomIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < queue.length;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            } else {
                currentIndex++;
                return queue[currentIndex];
            }
        }

        @Override
        public void remove() {

        }
    }
}
