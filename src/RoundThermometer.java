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
            seriCounter.put(item,0);
        while (Collections.max(seriCounter.values())<5){
            currentQuestionType = frame.getChosenCategory();
            Question questionObj = getRoundQuestion();

            responsesObj = frame.showQuestionAndGetResponses(questionObj,false);

            gainedPointsHash.clear();
            for (int j = 0; j < playersArr.length; j++) {
                Player currentPlayer = responsesObj.getPlayerAtPos(j);
                if (pointCalculator(questionObj, j, 0, 0)) {
                    seriCounter.put(currentPlayer, seriCounter.get(currentPlayer)+1);
                }
            }
            if (Collections.max(seriCounter.values()) >= 5)
                for (int j = 0; j < playersArr.length; j++) {
                    Player currentPlayer = responsesObj.getPlayerAtPos(j);
                    if (pointCalculator(questionObj, j, 0, 0) && seriCounter.get(currentPlayer) >=5){
                        pointCalculator(questionObj, j, 5000, 0);
                        break;
                    }

                }




            //responsesObj.clearReset();
            updateFrame_ShowPopUp_Clear_Responses(questionObj);
        }
    }
}

