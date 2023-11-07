package assign06;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that creates and simulates a web browser
 * @author Aiden Fornalski
 * @version 2023-10-19
 */
public class WebBrowser {
    private Stack<URL> back;
    private Stack<URL> forward;
    private URL current;

    public WebBrowser() {
        this.back = new LinkedListStack<>();
        this.forward = new LinkedListStack<>();
    }

    public WebBrowser(SinglyLinkedList<URL> history) {
        this.current = history.getFirst(); //set current webpage to first in the history list
        this.back = new LinkedListStack<>();
        this.forward = new LinkedListStack<>();
        Iterator<URL> iterator = history.iterator();
        LinkedListStack<URL> tempBack = new LinkedListStack<>();

        iterator.next();
        while (iterator.hasNext()) { //add history list to a temp stack
            tempBack.push(iterator.next());
        }

        for (int i = 0; i < history.size()-1; i++) { //add temp stack to back stack to correctly order the back stack
            back.push(tempBack.pop());
        }
    }

    /**
     * Simulates visiting a webpage.
     *
     * @param webpage - the webpage you wish to "visit"
     */
    public void visit(URL webpage) {
        if (current != null) { //checking to see if current is null or not
            forward.clear();
            back.push(current);
        }
        current = webpage; //set current webpage to the visited webpage
    }

    /**
     * Simulates pressing the back button on a webpage
     *
     * @return - the first webpage from the back stack
     * @throws NoSuchElementException - if the back stack is empty
     */
    public URL back() throws NoSuchElementException {
        if (back.isEmpty()) { //check to see if back stack is empty
            throw new NoSuchElementException();
        }

        forward.push(current); //add current webpage to forward stack
        current = back.pop(); //set current to first webpage from back stack and remove that webpage from the back stack
        return current;
    }

    /**
     * Simulates pressing the forward button on a webpage
     *
     * @return - the first webpage from the forward stack
     * @throws NoSuchElementException - if the forward stack is empty
     */
    public URL forward() throws NoSuchElementException {
        if (forward.isEmpty()) { //check to see if forward stack is empty
            throw new NoSuchElementException();
        }

        back.push(current); //add current webpage to the back stack
        current = forward.pop(); //set current to first webpage from forward stack and remove that webpage from the forward stack
        return current;
    }

    /**
     * Simulates checking the browser history (only for the back stack and current webpage)
     *
     * @return - a SinglyLinkedList of the browser history
     */
    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        LinkedListStack<URL> flippedBack = new LinkedListStack<>();
        int size = back.size();

        for (int i = 0; i < size; i++) { //reverse the back stack and set it to a temp stack
            flippedBack.push(back.pop());
        }

        for (int i = 0; i < size; i++) { //add temp stack to history AND reload the back stack
            URL temp = flippedBack.pop();
            history.insertFirst(temp);
            back.push(temp);
        }

        history.insertFirst(current); //add current webpage to the top of the history list
        return history;
    }
}
