import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponsesTest {


    @Test
    void clear_responses() {
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

        responses_obj.addPlayerResponse(pl2, true);
        responses_obj.addPlayerResponse(pl1, false);

        responses_obj.clear_responses();


        responses_obj.addPlayerResponse(pl1, false);
        responses_obj.addPlayerResponse(pl2, true);

        assertEquals(responses_obj.getPlayerAtPos(1),pl2);
        assertEquals(responses_obj.getResponseAtPos(1),true);

        assertEquals(responses_obj.getPlayerAtPos(0),pl1);
        assertEquals(responses_obj.getResponseAtPos(0),false);
    }

    @Test
    void addPlayerResponse() {
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

        responses_obj.addPlayerResponse(pl2, true);
        responses_obj.addPlayerResponse(pl1, false);

        assertEquals(responses_obj.getPlayerAtPos(0),pl2);
        assertEquals(responses_obj.getResponseAtPos(0),true);

        assertEquals(responses_obj.getPlayerAtPos(1),pl1);
        assertEquals(responses_obj.getResponseAtPos(1),false);

    }

    @Test
    void getPlayerAtPos() {
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

        responses_obj.addPlayerResponse(pl2, true);
        responses_obj.addPlayerResponse(pl1, false);

        assertEquals(responses_obj.getPlayerAtPos(0),pl2);

        assertEquals(responses_obj.getPlayerAtPos(1),pl1);
    }

    @Test
    void getResponseAtPos(){
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

        responses_obj.addPlayerResponse(pl2, true);
        responses_obj.addPlayerResponse(pl1, false);

        assertEquals(responses_obj.getResponseAtPos(0),true);

        assertEquals(responses_obj.getResponseAtPos(1),false);
    }
}