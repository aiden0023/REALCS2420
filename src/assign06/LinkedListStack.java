package assign06;

import java.util.NoSuchElementException;

/**
 * A class that creates a stack that uses a SinglyLinkedList to hold the data
 * @author Aiden Fornalski
 * @version 2023-10-19
 *
 * @param <T> - generic
 */
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
        if (isEmpty()) { //check if stack is empty
            throw new NoSuchElementException();
        }

        return list.getFirst(); //peek method is the same as list.getFirst()
    }

    @Override
    public T pop() throws NoSuchElementException {
        if (isEmpty()) { //check if stack is empty
            throw new NoSuchElementException();
        }

        T top = list.getFirst(); //storing data of the top of the stack, so it can be returned at the end of the method
        list.deleteFirst(); //pop method is the same as list.deleteFirst()
        return top;
    }

    @Override
    public void push(T element) {
        list.insertFirst(element); //push method is the same as list.insertFirst(element)
    }

    @Override
    public int size() {
        return list.size();
    }
}
