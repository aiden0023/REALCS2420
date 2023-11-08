package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that creates a singly linked list
 * @author Aiden Fornalski
 * @version 2023-10-19
 * @param <T> - generic
 */
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
            node.setNext(head);
        }
        head = node;
        size++;
    }

    @Override
    public void insert(int index, T element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            insertFirst(element);
        } else {
            Node<T> currentNode = head;
            Node<T> newNode = new Node<>(element);
            for (int i = 0; i < index-1; i++) {
                currentNode = currentNode.getNext();
            }

            if (currentNode.getNext() == null) {
                currentNode.setNext(newNode);
            } else {
                newNode.setNext(currentNode.getNext());
                currentNode.setNext(newNode);
            }
            size++;
        }
    }

    @Override
    public T getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return head.getData();
        }
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            return currentNode.getData();
        }
    }

    @Override
    public T deleteFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            T deleted = head.getData();
            head = head.getNext();
            size--;
            return deleted;
        }
    }

    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return deleteFirst();
        } else {
            Node<T> currentNode = head;
            for (int i = 0; i < index-1; i++) {
                currentNode = currentNode.getNext();
            }

            T deleted = currentNode.getNext().getData();
            currentNode.setNext(currentNode.getNext().getNext());
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
        return new ListIterator(head);
    }

    /**
     * Iterator class to be used for SinglyLinkedList
     */
    private class ListIterator implements Iterator<T> {

        private Node<T> nextNode;
        private Node<T> prevNode;

        public ListIterator(Node<T> head) {
            this.nextNode = head;
            this.prevNode = null;
        }

        /**
         * Checks to see if the iterator as a next
         *
         * @return - if the iterator as a next (true or false)
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
         * Iterates to the next node in the list
         *
         * @return - the next node
         */
        @Override
        public T next() {
            if (!hasNext()) { //check to see if the iterator has a next
                throw new NoSuchElementException();
            } else {
                T data = nextNode.getData(); //storing node data to return
                prevNode = nextNode;
                nextNode = nextNode.getNext();
                return data;
            }
        }

        /**
         * Removes the previous node iterated
         */
        @Override
        public void remove() {
            if (prevNode == null) { //check to see if the iterator has a previous
                throw new IllegalStateException();
            }

            if (prevNode == head) { //check to see if the previous node was the head of the list
                head = nextNode;
            } else {
                Node<T> temp = head;
                while (temp.getNext() != prevNode) { //loop through list until reaching prevNode
                    temp = temp.getNext();
                }
                temp.setNext(nextNode);
            }

            prevNode = null;
            size--;
        }
    }

    /**
     * A simple class to create a node object
     *
     * @param <T> - generic
     */
    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
