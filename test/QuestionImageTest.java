import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionImageTest {

    @Test
    void getImageName() {
        Question[] te = new Question[2];
        te[0] = new Question("Question","Type",new ArrayList<>());
        te[1] = new QuestionImage("QuestionImage","Type",new ArrayList<>(), "image.jpg");
        assertEquals( ((QuestionImage) te[1]).getImageName(),"image.jpg");
    }
}