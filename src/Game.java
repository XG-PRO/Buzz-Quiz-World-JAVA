import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    // @field a questions object that keeps all the questions inside
    private final Questions qs;


    /**
     * Default Constructor
     */
    public Game() {
        qs = new Questions();
    }

    /**
     * Starts the game logic
     */
    void play() {
        readFileQuestions();
        // Debug code
        Question temp = qs.getRandomQuestion();
        while (temp != null) {
            temp.debugShowInfo();
            temp = qs.getRandomQuestion();
        }
    }


    /**
     * reads the questions from a .tsv(tab separated values) file
     * Κατηγορία(TAB)Ερώτηση(TAB)Απάντηση 1(TAB)Απάντηση 2(TAB)Απάντηση 3(TAB)Απάντηση 4(TAB)Σωστή απάντηση(TAB)Όνομα εικόνας
     */
    private void readFileQuestions() { // Source: https://stackoverflow.com/questions/61443542/reading-tsv-file-in-java

        try (BufferedReader TSVReader = new BufferedReader(new FileReader("files/quiz.tsv"))) {
            String line;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                /*
                System.out.println(lineItems[0]);//κατηγορία
                System.out.println(lineItems[1]);//ερώτηση
                System.out.println(lineItems[2]);//απαντηση 1
                System.out.println(lineItems[3]);//απαντηση 2
                System.out.println(lineItems[4]);//απαντηση 3
                System.out.println(lineItems[5]);//απαντηση 4
                System.out.println(lineItems[6]);//σωστη απαντηση
                System.out.println(lineItems[7]);//ονομα εικόνας
                 */
                int correct_pos = 0;
                ArrayList<String> responses = new ArrayList<>(4);
                for (int i = 2; i < 6; i++) {
                    responses.add(lineItems[i]);
                    if (!lineItems[6].equals(lineItems[i])) {
                        System.out.println("The question '"+lineItems[1]+"' has the true response at wrong pos");
                        correct_pos = i - 2;
                    }
                }
                if (0 != correct_pos) { // The correct response isn't at pos 0
                    Collections.swap(responses, correct_pos, 0); // Move the correct response at pos 1
                }
                correct_pos = 0;
                qs.addQuestion(lineItems[0], lineItems[1], responses);

            }
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to read the tsv file.");
            System.exit(-1);
        }
    }


}

