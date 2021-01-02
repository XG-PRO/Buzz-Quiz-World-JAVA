import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implements the logic of each round. It could be implemented with inheritance but..
 * we decided instead to use methods to implement each type of round.
 */
public class Rounds {

    private final Questions questions_obj; // A questions object to get questions
    private final Player[] playersArr; // A player object to use the Player class and change their points
    private final int number_of_questions = 5; // The number of questions for each round
    private final ArrayList<String> rounds_types; // A arraylist that contains all the types of rounds, in which other types of rounds can be added anytime
    private String[] bet_types;   // A arraylist that contains all the bets the user can bet (ex 250,500...)
    private String current_question_type = "Random"; // The initial round question type
    private String current_round_type; // The current round type
    private GUI frame;
    private Responses responsesObj;
    private HashMap gainedPointsHash;
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
        this.gainedPointsHash = new HashMap<Player,Integer>(playersArr.length);

        //rounds_types = Utilities.CreateArrayListString(new String[] {"Right Answer","Bet","Stop The Counter","Quick Answer","Thermometer"});
        //rounds_types = Utilities.CreateArrayListString(new String[]{"Right Answer", "Bet"});
        rounds_types = Utilities.CreateArrayListString(new String[]{"Quick Answer",});
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
    private Question getRoundQuestion(){
        Question temp = questions_obj.getRandomQuestionWithType(current_question_type);
        if (temp == null) { // THE CURRENT QUESTION TYPE HAS NO MORE QUESTIONS
            current_question_type = "Random";
            temp = questions_obj.getRandomQuestionWithType("Random");
        }
        if (temp == null) {
            Parser.Exit(0);  //RAN OUT OF QUESTIONS
            temp = new Question("NULL", "NULL", Utilities.CreateArrayListString(new String[]{"NULL"})); // I added this for IntelliJ warnings
        }
        return temp;
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
            Question temp = getRoundQuestion();

            responsesObj = frame.showQuestionAndGetResponses(temp);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(temp,j,1000,0);
            updateFrameAndResetResponses(temp);
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
            Question temp = getRoundQuestion();

            int [] bet_player = new int [playersArr.length];

            for (int j = 0; j< playersArr.length;j++)
                bet_player[j] = Integer.parseInt(bet_types[frame.popupInput("Question Category is:\n" + temp.getType() + "\n\n" + playersArr[j].getName() + " place your bet:", bet_types)]);

            responsesObj = frame.showQuestionAndGetResponses(temp);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(temp,j,bet_player[j]*2,bet_player[j]);

            updateFrameAndResetResponses(temp);
        }

    }

    /** Unused types of Round which can be implemented in the future if needed **/

    private void RoundType_StopTheCounter() {

    }

    private void RoundType_QuickAnswer() {
        frame.changeRoundType("Quick Answer");
        for (int i = 0; i < number_of_questions; i++) {
            Question temp = getRoundQuestion();

            responsesObj = frame.showQuestionAndGetResponses(temp);

            gainedPointsHash.clear(); // = new HashMap<Player,Integer>(playersArr.length);

            int winner_points = 1000;
            for (int j = 0; j < playersArr.length; j++)
                if (pointCalculator(temp,j,winner_points,0))
                    winner_points/=2;

            updateFrameAndResetResponses(temp);
        }
    }

    private void RoundType_Thermometer() {

    }

    private boolean pointCalculator(Question temp, int pos,int winPoints, int losePoints){
        Player currentPlayer = responsesObj.getPlayerAtPos(pos);
        if (responsesObj.getResponseAtPos(pos).equals(temp.getRightResponse())) {
            gainedPointsHash.put(currentPlayer,winPoints);
            currentPlayer.increasePoints(winPoints);

            return true;
        }
        else {
            gainedPointsHash.put(currentPlayer, -losePoints);
            currentPlayer.decreasePoints(losePoints);
            return false;
        }
    }
    private void updateFrameAndResetResponses(Question temp) {
        frame.popupShowGainedPoints(playersArr, gainedPointsHash,temp.getRightResponse());
        frame.updatePlayersPoints(playersArr);
        responsesObj.clearReset();
    }

}