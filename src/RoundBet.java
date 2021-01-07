public class RoundBet extends Round{
    private final String[] betTypes;
    RoundBet(Questions questionsObj, GUI_Main frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
         betTypes = new String[]{"250", "500", "750", "1000"};  //Allowed bet amounts. More can be added in the same string
    }
    @Override
    public void playRound(){
        frame.changeRoundType("Bet");

        for (int i = 0; i < numberOfQuestionsPerRound; i++) {
            currentQuestionType = frame.getChosenCategory();
            Question temp = getRoundQuestion();

            int [] bet_player = new int [playersArr.length];    //The current bet of a player in int

            for (int j = 0; j< playersArr.length;j++)
                bet_player[j] = Integer.parseInt(betTypes[frame.popupInput("Question Category is:\n" + temp.getType() + "\n\n" + playersArr[j].getName() + " place your bet:", betTypes)]);

            //The below is standard for every round. Show the question into the frame , clear responses, calculate points and update the frame
            responsesObj = frame.showQuestionAndGetResponses(temp,false);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(temp,j,bet_player[j]*2,bet_player[j]);  //Win points are double the bet and loss points are the bet
            updateFrame_ShowPopUp_Clear_Responses(temp);
        }
    }

}
