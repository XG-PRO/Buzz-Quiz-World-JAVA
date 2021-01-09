import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HighScoresTest {
    HighScores highScoresObj;
    Player pl1;
    Player pl2;

    String [][] table;
    String [][] expected;
    @BeforeEach
    void setUp() {
        this.highScoresObj = new HighScores();
        this.pl1 = new Player("dummy1",new char[]{});
        this.pl2 = new Player("dummy2",new char[]{});
        highScoresObj.deleteAllScores();

    }

    @AfterEach
    void tearDown() {
        this.highScoresObj = null;
        this.pl1 = null;
        this.pl2 = null;
    }

    @Test
    void addScore() {
        pl1.increasePoints(100);
        pl1.setHasWon(false);
        highScoresObj.addHighScore(pl1);

        table = highScoresObj.getHighScoresTable();
        expected = new String[][]{{"dummy1","100","0"}};
        assertArrayEquals(table,expected);


        pl1.decreasePoints(80);
        pl1.setHasWon(true);
        highScoresObj.addHighScore(pl1);

        table = highScoresObj.getHighScoresTable();
        expected = new String[][]{{"dummy1", "100", "1"}};
        assertArrayEquals(table,expected);

        pl1.decreasePoints(80);
        pl1.decreasePoints(800);
        highScoresObj.addHighScore(pl1);

        table = highScoresObj.getHighScoresTable();
        expected = new String[][]{{"dummy1", "100", "2"}};
        assertArrayEquals(table,expected);

        pl2.increasePoints(200);
        highScoresObj.addHighScore(pl2);
        table = highScoresObj.getHighScoresTable();
        expected = new String[][]{{"dummy2", "200", "0"},{"dummy1", "100", "2"}};
        assertArrayEquals(table,expected);

        pl2.decreasePoints(1000);
        highScoresObj.addHighScore(pl2);
        table = highScoresObj.getHighScoresTable();
        expected = new String[][]{{"dummy2", "200", "0"},{"dummy1", "100", "2"}};
        assertArrayEquals(table,expected);
    }
    @Test
    void extremeCase(){

        pl1.increasePoints(100);
        pl2.increasePoints(100);

        highScoresObj.addHighScore(pl1);
        highScoresObj.addHighScore(pl2);

        table = highScoresObj.getHighScoresTable();
        expected = new String[][]{{"dummy2", "100", "0"},{"dummy1", "100", "0"}};
        assertArrayEquals(table,expected);
    }

}