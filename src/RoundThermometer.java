import java.util.Collections;
import java.util.HashMap;

public class RoundThermometer extends Round {

    RoundThermometer(Questions questionsObj, GUI_Main frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
    }
    @Override
    public void playRound() {
        frame.changeRoundType("Thermometer");
        HashMap<Player,Integer> seriCounter = new HashMap<>(playersArr.length);
        for (Player item :playersArr)
            seriCounter.put(item,0); //Each player starts with 0 correct answers

        //This is different from the other round types.
        //The loop doesn't end on a set number of questions, but rather continues until the conditions for point increase have been met
        while (Collections.max(seriCounter.values())<5){
            currentQuestionType = frame.getChosenCategory();
            Question questionObj = getRoundQuestion();

            //The below is standard for every round. Show the question into the frame , clear responses, calculate points and update the frame

            responsesObj = frame.showQuestionAndGetResponses(questionObj,false);

            gainedPointsHash.clear();

            //This loop gives no points to players but keeps which players answered correctly for the following loop
            for (int j = 0; j < playersArr.length; j++) {
                Player currentPlayer = responsesObj.getPlayerAtPos(j);
                if (pointCalculator(questionObj, j, 0, 0)) {
                    seriCounter.put(currentPlayer, seriCounter.get(currentPlayer)+1);
                }
            }

            //The first player who answers 5 questions correctly will gain 5000 points and the round will finally end
            if (Collections.max(seriCounter.values()) >= 5)
                for (int j = 0; j < playersArr.length; j++) {
                    Player currentPlayer = responsesObj.getPlayerAtPos(j);
                    if (pointCalculator(questionObj, j, 0, 0) && seriCounter.get(currentPlayer) >=5){
                        pointCalculator(questionObj, j, 5000, 0);
                        break;
                    }

                }

            updateFrame_ShowPopUp_Clear_Responses(questionObj);
        }
    }
}

