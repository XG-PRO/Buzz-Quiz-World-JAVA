import java.util.HashMap;

public class Round {
    protected static Questions questionsObj;
    protected static GUI_Main frame;
    protected Responses responsesObj;
    protected static Player [] playersArr;
    protected static HashMap<Player,Integer> gainedPointsHash;
    protected final static int numberOfQuestionsPerRound =  5;
    protected static String currentQuestionType = "Random";
    Round(Questions questionsObj,GUI_Main frame, Player[] playersArr){
        Round.questionsObj = questionsObj;
        Round.frame = frame;
        Round.gainedPointsHash = new HashMap<>(playersArr.length);
        Round.playersArr = playersArr;

    }
    protected void updateFrame_ShowPopUp_Clear_Responses(Question questionObj){
        frame.popupShowGainedPoints(playersArr, gainedPointsHash,questionObj.getRightResponse());
        frame.updatePlayersPoints(playersArr);
        responsesObj.clearReset();
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
    protected Question getRoundQuestion(){
        Question temp = questionsObj.getRandomQuestionWithType(currentQuestionType);
        if (temp == null) { // THE CURRENT QUESTION TYPE HAS NO MORE QUESTIONS
            currentQuestionType = "Random";
            temp = questionsObj.getRandomQuestionWithType("Random");
        }
        if (temp == null) {
            Parser.Exit(0);  //RAN OUT OF QUESTIONS
            temp = new Question("NULL", "NULL", Utilities.CreateArrayListString(new String[]{"NULL"})); // I added this for IntelliJ warnings
        }
        return temp;
    }
    public void playRound(){
    }
}
