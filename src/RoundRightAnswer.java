public class RoundRightAnswer extends Round {

    RoundRightAnswer(Questions questionsObj, GUI_Main frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
    }
    @Override
    public void playRound() {
        frame.changeRoundType("Right Answer");
        currentQuestionType = frame.showMenu(questionsObj.getTypes().toArray(new String[0]), currentQuestionType);
        for (int i = 0; i < numberOfQuestionsPerRound; i++) {
            Question questionObj = getRoundQuestion();

            responsesObj = frame.showQuestionAndGetResponses(questionObj);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(questionObj,j,1000,0);

            updateFrame_ShowPopUp_Clear_Responses(questionObj);
        }
    }
}

