import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponsesTest {
    Player pl1;
    Player pl2;
    Responses responses_obj;
    @BeforeEach
    void setUp(){
        pl1 = new Player("dummy1",new char[]{});
        pl2 = new Player("dummy2",new char[]{});
        responses_obj = new Responses(2);
    }
    @AfterEach
    void teardown() {
        pl1 = null;
        pl2 = null;
        responses_obj.clearReset();
    }
    @Test
    void clear_responses() {


        responses_obj.addPlayerResponse(pl2, "true",0);
        responses_obj.addPlayerResponse(pl2, "false",0);


        responses_obj.clearReset();



        responses_obj.addPlayerResponse(pl1, "false",0);
        responses_obj.addPlayerResponse(pl2, "true",0);

        assertEquals(responses_obj.getPlayerAtPos(1),pl2);
        assertEquals(responses_obj.getResponseAtPos(1),"true");

        assertEquals(responses_obj.getPlayerAtPos(0),pl1);
        assertEquals(responses_obj.getResponseAtPos(0),"false");
    }

    @Test
    void addPlayerResponse() {

        responses_obj.addPlayerResponse(pl2, "true",0);
        responses_obj.addPlayerResponse(pl1, "false",0);

        assertEquals(responses_obj.getPlayerAtPos(0),pl2);
        assertEquals(responses_obj.getResponseAtPos(0),"true");

        assertEquals(responses_obj.getPlayerAtPos(1),pl1);
        assertEquals(responses_obj.getResponseAtPos(1),"false");

    }

    @Test
    void getPlayerAtPos() {

        responses_obj.addPlayerResponse(pl2, "true",0);
        responses_obj.addPlayerResponse(pl1, "false",0);

        assertEquals(responses_obj.getPlayerAtPos(0),pl2);

        assertEquals(responses_obj.getPlayerAtPos(1),pl1);
    }

    @Test
    void getResponseAtPos(){

        responses_obj.addPlayerResponse(pl2, "true",0);
        responses_obj.addPlayerResponse(pl1, "false",0);

        assertEquals(responses_obj.getResponseAtPos(0),"true");

        assertEquals(responses_obj.getResponseAtPos(1),"false");
    }

    @Test
    void addPlayerResponseTwoTimes() {

        responses_obj.addPlayerResponse(pl2, "10",0);
        responses_obj.addPlayerResponse(pl1, "20",0);

        responses_obj.addPlayerResponse(pl2, "15",0);
        responses_obj.addPlayerResponse(pl1, "23",0);

        assertEquals(responses_obj.getPlayerAtPos(0),pl2);
        assertEquals(responses_obj.getResponseAtPos(0),"10");

        assertEquals(responses_obj.getPlayerAtPos(1),pl1);
        assertEquals(responses_obj.getResponseAtPos(1),"20");

        responses_obj.clearReset();

        responses_obj.addPlayerResponse(pl1, "23",0);
        responses_obj.addPlayerResponse(pl2, "15",0);
        assertEquals(responses_obj.getResponseAtPos(0),"23");
        assertEquals(responses_obj.getResponseAtPos(1),"15");
    }
}