package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<T> implements Stack<T> {

    private SinglyLinkedList<T> list;

    public LinkedListStack() {
        this.list = new SinglyLinkedList<>();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.getFirst();
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T top = list.getFirst();
        list.deleteFirst();
        return top;
    }

    @Override
    public void push(T element) {
        list.insertFirst(element);
    }

    @Override
    public int size() {
        return list.size();
    }
}
