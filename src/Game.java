import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/** The Game Class initiates the start of the game
 *  after gathering the resources needed (questions from file)
 *
 * **/

public class Game {
    // @field a questions object that keeps all the questions inside
    // The below parameters are used to complement the usage of the corresponding classes
    private final Questions questionsObj;

    private Player[] playersArr;


    private final Round[] roundsTypes; // A arraylist that contains all the types of rounds, in which other types of rounds can be added anytime;
    private final GUI_Main frame;

    /**
     * Default Constructor, Starts the GUI, asks the number of players
     * and creates rounds
     */
    public Game() {
        questionsObj = new Questions();

        readFileQuestions();
        frame = new GUI_Main(questionsObj.getTypes());
        frame.popupInfo();

        setNumberOfPlayers();

        //Single player allows only Right Answer, Bet and Stop The Timer
        if (playersArr.length==1)
            roundsTypes = new Round[]{
                    new RoundRightAnswer(questionsObj,frame,playersArr),
                    new RoundStopTheTimer(questionsObj,frame,playersArr),
                    new RoundBet(questionsObj,frame,playersArr)
            };
        //Multiplayer allows all round types
        else
            roundsTypes = new Round[]{
                    new RoundRightAnswer(questionsObj,frame,playersArr),
                    new RoundStopTheTimer(questionsObj,frame,playersArr),
                    new RoundBet(questionsObj,frame,playersArr),
                    new RoundQuickAnswer(questionsObj,frame,playersArr),
                    new RoundThermometer(questionsObj,frame,playersArr)
            };

    }


    /**
     * Starts the game
     */
    void play() {

        int number_of_rounds = 6;   //The number of rounds. Normally changeable but by default is 6
        for (int i = 0; i < number_of_rounds; i++) {
            frame.changeRoundCount(i+1);

            Round currentRoundObj = roundsTypes[Utilities.random_int(roundsTypes.length)];  //A round can have a random type
            currentRoundObj.playRound();    //Play that corresponding round (override)
        }

        //Checks who has won, prints results and terminates
        Utilities.whoWon(playersArr);
        frame.exitFrame(0);
    }

    /**
     * This method ask with a popup the number of players
     */
    void setNumberOfPlayers()
    {
        int numberOfPlayers = frame.popupAskNumberOfPlayer(2);
        playersArr = new Player[numberOfPlayers];

        char[][] acceptable_responses = new char[numberOfPlayers][4];

            acceptable_responses[0] = new char[]{'Q','W','E','R'};

        if (playersArr.length>1)
        {
            acceptable_responses[1] = new char[]{'1','2','3','4'};
            //Further response keys for more players can be added here
        }

        //Creates each player's response keys
        for (int i=0; i<numberOfPlayers; i++)
        {
            playersArr[i] = new Player("Player " + i, acceptable_responses[i]);
            playersArr[i] = new Player(frame.popupGetPlayerName(i+1), acceptable_responses[i]);
        }

        //Show the players into the frame
        frame.drawPlayersInfoToGUI(playersArr);
    }


    /**
     * reads the questions from a .tsv(tab separated values) file.
     * Κατηγορία(TAB)Ερώτηση(TAB)Απάντηση 1(TAB)Απάντηση 2(TAB)Απάντηση 3(TAB)Απάντηση 4(TAB)Σωστή απάντηση(TAB)Όνομα εικόνας
     */
    private void readFileQuestions() {

        String fileName = "packageQuestions/quiz.tsv";
        InputStream f = getClass().getResourceAsStream(fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(f))) {
            final int index_type = 0;
            final int index_question = 1;

            final int index_resp_start = 2;
            final int index_resp_finish = 5;

            final int index_resp_correct = 6;
            final int index_image_src = 7;

            String line;
            while ((line = reader.readLine()) != null) {  // for every line in the file
                String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]

                int correct_pos = 0;
                ArrayList<String> responses = new ArrayList<>(4); // add the responses
                for (int i = index_resp_start; i <= index_resp_finish; i++) {
                    responses.add(lineItems[i]);
                    if (lineItems[index_resp_correct].equals(lineItems[i])) {
                        correct_pos = i - index_resp_start;
                    }
                }

                if (0 != correct_pos) { // The correct response isn't at pos 0 because the person who wrote the question doesnt know what standards mean
                    //System.out.println("The correct response isn't at pos 0 : '" + lineItems[index_question] + "' ");
                    Collections.swap(responses, correct_pos, 0); // Move the correct response at pos 1
                }
                if (lineItems[index_type].equals("Random")) {
                    System.out.println("The CATEGORY of question '" + lineItems[index_question] + "' CAN NOT BE 'Random'!\n");
                    System.exit(-1);
                }
                if (lineItems[index_image_src].equals("NoImage"))
                    questionsObj.addQuestion(lineItems[index_type], lineItems[index_question], responses);
                else
                    questionsObj.addQuestionImage(lineItems[index_type], lineItems[index_question], responses,lineItems[index_image_src]);

            }
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to read the .tsv file.");
            e.printStackTrace();
            System.exit(-1);
        }

    }


}

