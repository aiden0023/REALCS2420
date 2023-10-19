package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements List<T> {

    private Node<T> head;
    private static int size;

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
            Node<T> currentNode = head;
            Node<T> newNode = new Node<>(element);
            for (int i = 0; i < index-1; i++) {
                currentNode = currentNode.next;
            }

            newNode.next = currentNode.next;
            currentNode.next = newNode;
            size++;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.data;
        }
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }

            T deleted = currentNode.data;
            currentNode = currentNode.next;
            size--;
            return deleted;
        }
    }

    @Override
    public int indexOf(T element) {
        int i = 0;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T nodeData = iterator.next();
            if (nodeData.equals(element)) {
                return i;
            }
            i++;
        }
        return -1;
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
        T[] array = (T[]) new Object[size()];
        int i = 0;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return array;
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

    private static class ListIterator<T> implements Iterator<T> {

        private Node<T> nextNode;
        private Node<T> prevNode;

        public ListIterator(Node<T> head) {
            this.nextNode = head;
            this.prevNode = null;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                T data = nextNode.data;
                prevNode = nextNode;
                nextNode = nextNode.next;
                return data;
            }
        }

        //MAY NEED TO BE FIXED
        @Override
        public void remove() {
            if (prevNode == null) {
                throw new IllegalStateException();
            } else {
                prevNode.next = nextNode;
                prevNode = null;
                size--;
            }
        }
    }
}
