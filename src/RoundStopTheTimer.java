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

            //The below is standard for every round. Show the question into the frame , clear responses, calculate points and update the frame

            responsesObj = frame.showQuestionAndGetResponses(temp,true);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculatorTimer(temp,j);       //Special Calculator method that returns points in accordance to a timer
            updateFrame_ShowPopUp_Clear_Responses(temp);
        }
    }

}
