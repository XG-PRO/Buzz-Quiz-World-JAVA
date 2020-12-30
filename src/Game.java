import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/** The Game Class initiates the start of the game
 * after gathering the resources needed (questions from file)**/

public class Game {
    // @field a questions object that keeps all the questions inside
    // The below parameters are used to complement the usage of the corresponding classes
    private final Questions qs;
    private final Rounds rs;
    Player[] players_arr;

    /**
     * Default Constructor
     */
    public Game() {
        qs = new Questions();


        readFileQuestions("files/quiz.tsv");
        GUI frame = new GUI();
        playersSet(frame);
        rs = new Rounds(qs, players_arr,frame);
    }


    /**
     * Starts the game
     */
    void play() {


        rs.StartRound();

        // Debug code
        /*
        while (temp != null) {
            temp.debugShowInfo();
            temp = qs.getRandomQuestion();
        }
        */

    }

    void playersSet(GUI frame)
    {
        int numberOfPlayers = frame.popupAskNumberOfPlayer();
        players_arr = new Player[numberOfPlayers];

        char[][] acceptable_responses = new char[players_arr.length][4];

            acceptable_responses[0] = new char[]{'Q','W','E','R'};

        if (players_arr.length>1)
            acceptable_responses[1] = new char[]{'1','2','3','4'};

        for (int i=0; i<numberOfPlayers; i++)
        {
            players_arr[i] = new Player("Player " + i, acceptable_responses[i]);
            players_arr[i] = new Player(frame.popupGetPlayerName(i+1), acceptable_responses[i]);
        }


        frame.drawPlayersInfoToGUI(players_arr);
    }


    /**
     * reads the questions from a .tsv(tab separated values) file.
     * Κατηγορία(TAB)Ερώτηση(TAB)Απάντηση 1(TAB)Απάντηση 2(TAB)Απάντηση 3(TAB)Απάντηση 4(TAB)Σωστή απάντηση(TAB)Όνομα εικόνας
     */
    private void readFileQuestions(String filename) { // Source: https://stackoverflow.com/questions/61443542/reading-tsv-file-in-java

        try (BufferedReader TSVReader = new BufferedReader(new FileReader(filename))) {
            final int index_type = 0;
            final int index_question = 1;
            final int index_resp_start = 2;
            final int index_resp_finish = 5;
            final int index_resp_correct = 6;
            //final int index_image_src = 7;

            String line;
            while ((line = TSVReader.readLine()) != null) {  // for every line in the file
                String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                /*
                lineItems[0]//κατηγορία
                lineItems[1]//ερώτηση
                lineItems[2]//απαντηση 1
                lineItems[3]//απαντηση 2
                lineItems[4]//απαντηση 3
                lineItems[5]//απαντηση 4
                lineItems[6]//σωστη απαντηση
                lineItems[7]//ονομα εικόνας
                 */
                int correct_pos = 0;
                ArrayList<String> responses = new ArrayList<>(4); // add the responses
                for (int i = index_resp_start; i <= index_resp_finish; i++) {
                    responses.add(lineItems[i]);
                    if (lineItems[index_resp_correct].equals(lineItems[i])) {
                        correct_pos = i - index_resp_start;
                    }
                }

                if (0 != correct_pos) { // The correct response isn't at pos 0
                    //System.out.println("The correct response isn't at pos 0 : '" + lineItems[index_question] + "' ");
                    Collections.swap(responses, correct_pos, 0); // Move the correct response at pos 1
                }
                if (lineItems[index_type].equals("Random")) {
                    System.out.println("The type of question '" + lineItems[index_question] + "' CAN NOT BE 'Random'!\n");
                    throw new Exception();
                }
                qs.addQuestion(lineItems[index_type], lineItems[index_question], responses);

            }
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to read the .tsv file.");
            System.exit(-1);
        }
    }


}

