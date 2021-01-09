import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNull;

class QuestionsTest {
    Questions questionsObj;
    final int  SIZE = 10;
    @BeforeEach
    void setUp(){
        questionsObj = new Questions();
        for (int i = 0; i < SIZE; i++) {
            questionsObj.addQuestion(new Question("QS"+i,"Type"+i,new ArrayList<>()));
        }
    }

    @Test
    void testRunOutQuestions(){
        for (int i = 0; i < SIZE; i++) {
            questionsObj.getRandomQuestionWithType("Random");
        }
        assertNull(questionsObj.getRandomQuestionWithType("Random"));

    }
}