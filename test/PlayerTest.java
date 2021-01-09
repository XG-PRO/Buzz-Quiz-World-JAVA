import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    Player obj;
    @BeforeEach
    void setUp() {
        obj = new Player("dummy",new char[]{'a','b','c','d'});
        assertEquals(obj.getPoints(),0);
    }

    @AfterEach
    void tearDown() {
        obj = null;
    }

    @Test
    void getPoints() {

        obj.increasePoints(100);
        assertEquals(obj.getPoints(),100);
        obj.decreasePoints(200);
        assertEquals(obj.getPoints(),-100);
    }

    @Test
    void increasePoints() {
        obj.increasePoints(100);
        assertEquals(obj.getPoints(),100);
        obj.increasePoints(50);
        obj.increasePoints(50);
        assertEquals(obj.getPoints(),200);
    }

    @Test
    void decreasePoints() {
        obj.increasePoints(100);
        assertEquals(obj.getPoints(),100);
        obj.decreasePoints(50);
        assertEquals(obj.getPoints(),50);
    }

    @Test
    void getName() {
        assertEquals(obj.getName(),"dummy");
    }

    @Test
    void getKeyboard_responses() {
        assertArrayEquals(obj.getKeyboard_responses(),new char[]{'a','b','c','d'});
    }
}