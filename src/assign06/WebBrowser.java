package assign06;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class WebBrowser {
    private LinkedListStack<URL> back;
    private LinkedListStack<URL> forward;
    private URL current;

    public WebBrowser() {
        this.back = new LinkedListStack<>();
        this.forward = new LinkedListStack<>();
    }

    public WebBrowser(SinglyLinkedList<URL> history) {
        this.current = history.getFirst();
        this.back = new LinkedListStack<>();
        this.forward = new LinkedListStack<>();
        Iterator<URL> iterator = history.iterator();

        iterator.next();
        while (iterator.hasNext()) {
            back.push(iterator.next());
        }
    }

    public void visit(URL webpage) {
        forward.clear();
        back.push(current);
        current = webpage;
    }

    public URL back() throws NoSuchElementException {
        if (back.isEmpty()) {
            throw new NoSuchElementException();
        }

        forward.push(current);
        current = back.pop();
        return current;
    }

    public URL forward() throws NoSuchElementException {
        if (forward.isEmpty()) {
            throw new NoSuchElementException();
        }

        back.push(current);
        current = forward.pop();
        return current;
    }

    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        LinkedListStack<URL> tempBack = back;
        LinkedListStack<URL> flippedBack = new LinkedListStack<>();

        for (int i = 0; i < back.size(); i++) {
            flippedBack.push(tempBack.pop());
        }

        for (int i = back.size(); i > 0; i--) {
            history.insertFirst(flippedBack.pop());
        }

        history.insertFirst(current);
        return history;
    }
}
