public class RoundRightAnswer extends Round {

    RoundRightAnswer(Questions questionsObj, GUI_Main frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
    }
    @Override
    public void playRound() {
        frame.changeRoundType("Right Answer");

        for (int i = 0; i < numberOfQuestionsPerRound; i++) {
            currentQuestionType = frame.getChosenCategory();
            Question questionObj = getRoundQuestion();


            //The below is standard for every round. Show the question into the frame , clear responses, calculate points and update the frame

            responsesObj = frame.showQuestionAndGetResponses(questionObj,false);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(questionObj,j,1000,0);  //Win points are 1000 and there are no loss points

            updateFrame_ShowPopUp_Clear_Responses(questionObj);
        }
    }
}

