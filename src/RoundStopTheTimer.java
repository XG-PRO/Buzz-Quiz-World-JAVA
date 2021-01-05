public class RoundStopTheTimer extends Round{

    RoundStopTheTimer(Questions questionsObj, GUI_Main frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
    }
    @Override
    public void playRound(){
        frame.changeRoundType("Stop The Timer");

        for (int i = 0; i < numberOfQuestionsPerRound; i++) {
            currentQuestionType = frame.getChosenCategory();
            Question temp = getRoundQuestion();

            int [] win_player = new int [playersArr.length];

            responsesObj = frame.showQuestionAndGetResponses(temp,true);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculatorTimer(temp,j);
            updateFrame_ShowPopUp_Clear_Responses(temp);
        }
    }

}
