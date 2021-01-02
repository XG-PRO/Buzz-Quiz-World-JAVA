public class RoundQuickAnswer extends Round{

    RoundQuickAnswer(Questions questionsObj, GUI frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
    }
    @Override
    public void playRound(){
        frame.changeRoundType("Quick Answer");
        for (int i = 0; i < numberOfQuestionsPerRound; i++) {
            Question temp = getRoundQuestion();

            responsesObj = frame.showQuestionAndGetResponses(temp);

            gainedPointsHash.clear(); // = new HashMap<Player,Integer>(playersArr.length);

            int winner_points = 1000;
            for (int j = 0; j < playersArr.length; j++)
                if (pointCalculator(temp,j,winner_points,0))
                    winner_points/=2;
            updateFrame_ShowPopUp_Clear_Responses(temp);
        }
    }

}
