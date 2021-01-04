import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponsesTest {


    @Test
    void clear_responses() {
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

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
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

        responses_obj.addPlayerResponse(pl2, "true",0);
        responses_obj.addPlayerResponse(pl1, "false",0);

        assertEquals(responses_obj.getPlayerAtPos(0),pl2);
        assertEquals(responses_obj.getResponseAtPos(0),"true");

        assertEquals(responses_obj.getPlayerAtPos(1),pl1);
        assertEquals(responses_obj.getResponseAtPos(1),"false");

    }

    @Test
    void getPlayerAtPos() {
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

        responses_obj.addPlayerResponse(pl2, "true",0);
        responses_obj.addPlayerResponse(pl1, "false",0);

        assertEquals(responses_obj.getPlayerAtPos(0),pl2);

        assertEquals(responses_obj.getPlayerAtPos(1),pl1);
    }

    @Test
    void getResponseAtPos(){
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

        responses_obj.addPlayerResponse(pl2, "true",0);
        responses_obj.addPlayerResponse(pl1, "false",0);

        assertEquals(responses_obj.getResponseAtPos(0),"true");

        assertEquals(responses_obj.getResponseAtPos(1),"false");
    }

    @Test
    void addPlayerResponseTwoTimes() {
        Player pl1 = new Player("dummy1",new char[]{});
        Player pl2 = new Player("dummy2",new char[]{});
        Responses responses_obj = new Responses(2);

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