import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HighScoresTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addScore() {
        HighScores highScoresObj = new HighScores();

        //highScoresObj.addHighScore("Player 1",100);
        //highScoresObj.addHighScore("Player 2",100);
        highScoresObj.printScoresInOrder();

        System.out.println("----");


        //highScoresObj.addHighScore("Player 2",150);
        highScoresObj.printScoresInOrder();


    }
}