import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {
    Question questionObj;
    @BeforeEach
    void setUp() {
        ArrayList<String> expect = new ArrayList<>();
        expect.add("A");expect.add("B");expect.add("C");expect.add("D");
        questionObj = new Question("Test?","Type", expect);
    }


    @Test
    void getResponses() {
        ArrayList<String> in = questionObj.getResponses();
        ArrayList<String> expect = new ArrayList<>();
        expect.add("A");expect.add("B");expect.add("C");expect.add("D");
        for (int i = 0; i < in.size(); i++) {
            assertEquals(in.get(i), expect.get(i));
        }
    }

    @Test
    void getQuestion() {
        assertEquals(questionObj.getQuestion(),"Test?");
    }

    @Test
    void getType() {
        assertEquals(questionObj.getType(),"Type");
    }

    @Test
    void getRightResponse() {
        assertEquals(questionObj.getRightResponse(),"A");
    }
}