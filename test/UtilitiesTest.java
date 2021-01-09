import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilitiesTest {

    @Test
    void random_int() {
        for (int i = 0; i < 100; i++) {
            int x = Utilities.random_int(10);
            assertTrue(x<=10 && x>=0);
        }

    }

    @Test
    void whoWonTest() {
        Player[] players = new Player[2];
        for (int i = 0; i<2; i++)
            players[i] = new Player("dummy"+i,new char[]{});
        players[1].increasePoints(1001);
        players[0].increasePoints(100);
        assertFalse(players[0].getHasWon());
        assertFalse(players[1].getHasWon());

        Utilities.whoWon(players);

        assertFalse(players[0].getHasWon());
        assertTrue(players[1].getHasWon());

    }
}