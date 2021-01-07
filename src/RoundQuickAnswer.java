public class RoundQuickAnswer extends Round{

    RoundQuickAnswer(Questions questionsObj, GUI_Main frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
    }
    @Override
    public void playRound(){
        frame.changeRoundType("Quick Answer");

        for (int i = 0; i < numberOfQuestionsPerRound; i++) {
            currentQuestionType = frame.getChosenCategory();
            Question temp = getRoundQuestion();


            //The below is standard for every round. Show the question into the frame , clear responses, calculate points and update the frame

            responsesObj = frame.showQuestionAndGetResponses(temp,false);

            gainedPointsHash.clear(); // = new HashMap<Player,Integer>(playersArr.length);

            int winner_points = 1000;   //The starting win points.
            for (int j = 0; j < playersArr.length; j++)
                if (pointCalculator(temp,j,winner_points,0))    //No loss points, and win points are divided by two for every player who answers correctly
                    winner_points/=2;
            updateFrame_ShowPopUp_Clear_Responses(temp);
        }
    }

}
