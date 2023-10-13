package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T> {

    private Node<T> head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void insertFirst(T element) {
        Node<T> node = new Node<>(element);

        if (head != null) {
            node.next = head;
        }
        head = node;
        size++;
    }

    @Override
    public void insert(int index, T element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            
        }
    }

    @Override
    public T getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return head.data;
        }
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public T deleteFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            T deleted = head.data;
            head = head.next;
            size--;
            return deleted;
        }
    }

    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head);
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private class ListIterator<T> implements Iterator<T> {

        private Node<T> current;
        private Node<T> prev;

        public ListIterator(Node<T> head) {
            this.current = head;
            this.prev = null;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                T data = current.data;
                prev = current;
                current = current.next;
                return data;
            }
        }

        @Override
        public void remove() {

        }
    }
}
