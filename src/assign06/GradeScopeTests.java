package assign06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GradeScopeTests {

    SinglyLinkedList<Integer> list;

    @BeforeEach
    public void setup() {
        list = new SinglyLinkedList<>();
        /*list.insertFirst(6);
        list.insertFirst(4);
        list.insertFirst(17);
        list.insertFirst(5);
        list.insertFirst(10);
        list.insertFirst(14);*/
    }

    @Test
    public void removeIndexSize() {
        //Ask TA what this test is
    }

    @Test
    public void getFirstSmallListCheckReturn() {
        list.insert(1, 20);
    }
}
