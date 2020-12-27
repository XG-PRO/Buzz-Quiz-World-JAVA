import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getPoints() {
        Player obj = new Player("dummy",new char[]{'a','b'});
        assertEquals(obj.getPoints(),0);

        obj.increasePoints(100);
        assertEquals(obj.getPoints(),100);
        obj.decreasePoints(200);
        assertEquals(obj.getPoints(),-100);
    }

    @Test
    void increasePoints() {
        Player obj = new Player("dummy",new char[]{'a','b'});
        assertEquals(obj.getPoints(),0);

        obj.increasePoints(100);
        assertEquals(obj.getPoints(),100);
        obj.increasePoints(50);
        obj.increasePoints(50);
        assertEquals(obj.getPoints(),200);
    }

    @Test
    void decreasePoints() {
        Player obj = new Player("dummy",new char[]{'a','b'});
        assertEquals(obj.getPoints(),0);

        obj.increasePoints(100);
        assertEquals(obj.getPoints(),100);
        obj.decreasePoints(50);
        assertEquals(obj.getPoints(),50);
    }

    @Test
    void getName() {
        Player obj = new Player("dummy",new char[]{'a','b'});
        assertEquals(obj.getName(),"dummy");
    }

    @Test
    void getKeyboard_responses() {
        Player obj = new Player("dummy",new char[]{'a','b','c','d'});
        assertArrayEquals(obj.getKeyboard_responses(),new char[]{'a','b','c','d'});
    }
}