import java.util.HashMap;

public class Round {
    protected Questions questionsObj;
    protected final GUI frame;
    protected Responses responsesObj;
    protected final Player [] playersArr;
    protected final HashMap<Player,Integer> gainedPointsHash;
    protected int numberOfQuestions =  5;
    protected String currentQuestionType = "Random";
    Round(Questions questionsObj,GUI frame, Player[] playersArr){
        this.questionsObj = questionsObj;
        this.frame = frame;
        this.gainedPointsHash = new HashMap<>(playersArr.length);
        this.playersArr = playersArr;
    }
    protected void updateFrame(Question temp){
        frame.popupShowGainedPoints(playersArr, gainedPointsHash,temp.getRightResponse());
        frame.updatePlayersPoints(playersArr);
        responsesObj.clearReset();
    }
    protected boolean pointCalculator(Question temp, int pos,int winPoints, int losePoints){
        //Player currentPlayer = responsesObj.getPlayerAtPos(pos);
        if (responsesObj.getResponseAtPos(pos).equals(temp.getRightResponse())) {
            gainedPointsHash.put(responsesObj.getPlayerAtPos(pos),winPoints);
            responsesObj.getPlayerAtPos(pos).increasePoints(winPoints);

            return true;
        }
        else {
            gainedPointsHash.put(responsesObj.getPlayerAtPos(pos), -losePoints);
            responsesObj.getPlayerAtPos(pos).decreasePoints(losePoints);
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
}
