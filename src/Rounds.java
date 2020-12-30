import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the logic of each round. It could be implemented with inheritance but..
 * we decided instead to use methods to implement each type of round.
 */
public class Rounds {

    private final Questions questions_obj; // A questions object to get questions
    private final Player[] playersArr; // A player object to use the Player class and change their points
    private final int number_of_questions = 5; // The number of questions for each round
    private final ArrayList<String> rounds_types; // A arraylist that contains all the types of rounds, in which other types of rounds can be added antyime
    private String[] bet_types;   // A arraylist that contains all the bets the user can bet (ex 250,500...)
    private String current_question_type = "Random"; // The initial round question type
    private String current_round_type; // The current round type
    private GUI frame;
    private Responses responsesObj;
    /**
     * Default Constructor
     *
     * @param questions_obj A Questions object that contains all the questions.
     * @param playersArr    A Player object to change the points
     */
    public Rounds(Questions questions_obj, Player[] playersArr, GUI frame) {
        this.questions_obj = questions_obj;
        this.playersArr = playersArr;
        this.frame = frame;
        this.responsesObj = new Responses(playersArr.length);

        //rounds_types = Utilities.CreateArrayListString(new String[] {"Right Answer","Bet","Stop The Counter","Quick Answer","Thermometer"});
        //rounds_types = Utilities.CreateArrayListString(new String[]{"Right Answer", "Bet"});
        rounds_types = Utilities.CreateArrayListString(new String[]{"Bet",});
        bet_types = new String[]{"250", "500", "750", "1000"};

    }


    /**
     * This method starts the rounds
     */
    public void StartRound() {

        // The number of rounds
        int number_of_rounds = 6;
        for (int i = 0; i < number_of_rounds; i++) {

            current_round_type = rounds_types.get(Utilities.random_int(rounds_types.size())); // Each Round Type is picked randomly but the user can change it
            //System.out.println("Current Round Count: " + (i+1)+"/"+number_of_rounds);
            //Parser.PrintRoundNumber(i + 1, number_of_rounds); //
            frame.changeRoundCount(i+1);
            RoundMenu();
            switch (current_round_type) {
                case "Bet":
                    RoundType_Bet();
                    break;
                case "Stop The Counter":
                    RoundType_StopTheCounter();
                    break;
                case "Quick Answer":
                    RoundType_QuickAnswer();
                    break;
                case "Thermometer":
                    RoundType_Thermometer();
                    break;
                default:
                    RoundType_RightAnswer();
                    break;
            }

        }
        //Parser.Exit(1);

    }

    /**
     * This method when it is called shows the Menu before each round
     * letting the use choose the type of questions and round he wants.
     * <b>THIS METHOD CHANGES THE FIELDS "current_round_type" AND "current_question_type"<b/>
     */
    private void RoundMenu() {
    //    Parser.showPoints(player_obj.getPoints());
      //  String[] me = Parser.printMENU(questions_obj.getTypes(), rounds_types, current_question_type, current_round_type);
        //current_round_type = me[1];
        //current_question_type = me[0];

    }


    /**
     * This method implements the Right Answer
     * It functions by presenting questions equal to the set number of questions per Round (constant in our case),
     * and handles what is shown and gather from the user in order for their points to be handled.
     * It can change the Player, Questions  objects
     */
    private void RoundType_RightAnswer() {
        frame.changeRoundType("Right Answer");
        for (int i = 0; i < number_of_questions; i++) {
            Question temp = questions_obj.getRandomQuestionWithType(current_question_type);
            if (temp == null) { // THE CURRENT QUESTION TYPE HAS NO MORE QUESTIONS
                current_question_type = "Random";
                temp = questions_obj.getRandomQuestionWithType("Random");
            }
            if (temp == null) {
                Parser.Exit(0);  //RAN OUT OF QUESTIONS
                temp = new Question("NULL", "NULL", Utilities.CreateArrayListString(new String[]{"NULL"})); // I added this for IntelliJ warnings
            }
            //System.out.printf("ID OF OBJECT INSIDE ROUNDS : "+responsesObj.hashCode());
            frame.showQuestionAndGetResponses(temp, playersArr, responsesObj);
            while (!responsesObj.haveAllPlayersResponed()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //System.out.printf("Next Question");
            int[] gainedPoints;
            gainedPoints = new int[playersArr.length];


            for (int j = 0; j < playersArr.length; j++) {
                if (responsesObj.getResponseAtPos(j).equals(temp.getRightResponse())) {
                    gainedPoints[j] = 1000;
                    responsesObj.getPlayerAtPos(j).increasePoints(1000);
                } else
                    gainedPoints[j] = 0;
            }

            frame.popupShowGainedPoints(playersArr, gainedPoints);
            frame.updatePlayersPoints(playersArr);
            responsesObj.clear_responses();


        }

    }



    /**
     * This method implements the Bet
     * Functions exactly as Right Answer, with the added method of betting points
     * It can change the Player,Questions  objects
     */
    private void RoundType_Bet() {
        frame.changeRoundType("Bet");
        for (int i = 0; i < number_of_questions; i++) {
            Question temp = questions_obj.getRandomQuestionWithType(current_question_type);
            if (temp == null) { // THE CURRENT QUESTION TYPE HAS NO MORE QUESTIONS
                current_question_type = "Random";
                temp = questions_obj.getRandomQuestionWithType("Random");
            }
            if (temp == null) {
                Parser.Exit(0);  //RAN OUT OF QUESTIONS
                temp = new Question("NULL", "NULL", Utilities.CreateArrayListString(new String[]{"NULL"})); // I added this for IntelliJ warnings
            }

            int [] bet_player = new int [playersArr.length];

            for (int j = 0; j< playersArr.length;j++)
                bet_player[j] = Integer.parseInt(bet_types[frame.popupInput("Question Category is:\n" + temp.getType() + "\n\n" + playersArr[j].getName() + " place your bet:", bet_types)]);


            //System.out.printf("ID OF OBJECT INSIDE ROUNDS : "+responsesObj.hashCode());
            frame.showQuestionAndGetResponses(temp, playersArr, responsesObj);
            while (!responsesObj.haveAllPlayersResponed()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //System.out.printf("Next Question");
            int[] gainedPoints;
            gainedPoints = new int[playersArr.length];


            for (int j = 0; j < playersArr.length; j++) {
                if (responsesObj.getResponseAtPos(j).equals(temp.getRightResponse())) {
                    gainedPoints[j] = bet_player[j] * 2;
                    responsesObj.getPlayerAtPos(j).increasePoints( bet_player[j] * 2);
                }
                else {
                    gainedPoints[j] = -bet_player[j];
                    responsesObj.getPlayerAtPos(j).decreasePoints(bet_player[j]);
                }
            }

            frame.popupShowGainedPoints(playersArr, gainedPoints);
            frame.updatePlayersPoints(playersArr);
            responsesObj.clear_responses();


        }

    }

    /** Unused types of Round which can be implemented in the future if needed **/

    private void RoundType_StopTheCounter() {

    }

    private void RoundType_QuickAnswer() {

    }

    private void RoundType_Thermometer() {

    }


}