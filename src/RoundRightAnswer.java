public class RoundRightAnswer extends Round{

    RoundRightAnswer(Questions questionsObj,GUI frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
    }
    void playRound(){
        frame.changeRoundType("Right Answer");
        currentQuestionType = frame.showMenu(questionsObj.getTypes().toArray(new String[0]), currentQuestionType);
        for (int i = 0; i < numberOfQuestions; i++) {
            Question temp = getRoundQuestion();

            responsesObj = frame.showQuestionAndGetResponses(temp);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(temp,j,1000,0);
            updateFrame(temp);
        }
    }

}
