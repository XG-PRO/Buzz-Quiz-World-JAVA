import java.util.HashMap;

public class Round {
    protected static Questions questionsObj;
    protected static GUI_Main frame;
    protected Responses responsesObj;
    protected static Player [] playersArr;
    protected static HashMap<Player,Integer> gainedPointsHash;
    protected final static int numberOfQuestionsPerRound =  5;  //Normally changeable, by default is 5
    protected static String currentQuestionType = "Random";
    Round(Questions questionsObj,GUI_Main frame, Player[] playersArr){
        Round.questionsObj = questionsObj;
        Round.frame = frame;
        Round.gainedPointsHash = new HashMap<>(playersArr.length);
        Round.playersArr = playersArr;

    }

    /**
     * This method shows the popup gained points, updates the player points in the frame, clears the responsesObj
     * @param questionObj  represents the given question
     */
    protected void updateFrame_ShowPopUp_Clear_Responses(Question questionObj){

        frame.popupShowGainedPoints(gainedPointsHash,questionObj.getRightResponse(),responsesObj);  //Shows the current question's results before it changes
        frame.updatePlayersPoints(playersArr);  //Updates the results into the players' score on the frame
        responsesObj.clearReset();  //Clears the responses given from the players
    }

    /**
     * This method calculates the points for a player who answered at 'pos' position.
     * @param questionObj The question object.
     * @param pos The order of the player.
     * @param winPoints The points that the player who responded at pos position will get if his responses was right.
     * @param losePoints The points that the player who responded at pos position will get if his responses was wrong.
     * @return True if player response was right / false if was wrong.
     */
    protected boolean pointCalculator(Question questionObj, int pos,int winPoints, int losePoints){
        Player currentPlayer = responsesObj.getPlayerAtPos(pos);

        //If a player's response is equal to the right answer of the corresponding question, increase his points, else, decrease them
        if (responsesObj.getResponseAtPos(pos).equals(questionObj.getRightResponse())) {
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

    /**
     * A special calculator that runs the original pointCalculator
     * but with the timer as a win value
     * @param questionObj  current question
     * @param pos current player in accordance to his response
     * @return  pointCalculator values regarding the timer
     */

    protected boolean pointCalculatorTimer(Question questionObj, int pos){
        Player currentPlayer = responsesObj.getPlayerAtPos(pos);
        if (currentPlayer == null)
            return false;
        return pointCalculator(questionObj,pos,(int) (responsesObj.getTimeAtPos(pos)*0.2),0);

    }

    /**
     * Shows a question in the frame using legacy code
     * @return current question
     */
    protected Question getRoundQuestion(){
        //Gets a random question with a given type (random by default)
        Question temp = questionsObj.getRandomQuestionWithType(currentQuestionType);

        if (temp == null) { // If the current question type has no more questions, take a random category instead
            currentQuestionType = "Random";
            temp = questionsObj.getRandomQuestionWithType("Random");
        }
        if (temp == null) { //If there are no more questions in the directory, save the results, show the winners and terminate the game
            Utilities.whoWon(playersArr);
            frame.popupShowWinners();
            frame.exitFrame(1);
            temp = new Question("NULL", "NULL", Utilities.CreateArrayListString(new String[]{"NULL"})); // I added this for IntelliJ warnings (legacy code)
        }
        return temp;
    }

    /**
     * The main method of executing a round
     * overridden by the corresponding round category
     */
    public void playRound(){
    }


}
