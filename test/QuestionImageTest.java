import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionImageTest {

    @Test
    void getResponses() {
    }

    @Test
    void getQuestion() {
    }

    @Test
    void getType() {
    }

    @Test
    void debugShowInfo() {
    }

    @Test
    void getRightResponse() {
    }

    @Test
    void getImageName() {
        Question obj = new QuestionImage("TEST","Type",new ArrayList<>(),"image.jpg");
        assertEquals(obj instanceof QuestionImage,true);
        assertEquals( ((QuestionImage)obj).getImageName(),"image.jpg");
    }
}