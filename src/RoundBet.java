public class RoundBet extends Round{
    private final String[] betTypes;
    RoundBet(Questions questionsObj, GUI_Main frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
         betTypes = new String[]{"250", "500", "750", "1000"};
    }
    @Override
    public void playRound(){
        frame.changeRoundType("Bet");

        for (int i = 0; i < numberOfQuestionsPerRound; i++) {
            currentQuestionType = frame.getChosenCategory();
            Question temp = getRoundQuestion();

            int [] bet_player = new int [playersArr.length];

            for (int j = 0; j< playersArr.length;j++)
                bet_player[j] = Integer.parseInt(betTypes[frame.popupInput("Question Category is:\n" + temp.getType() + "\n\n" + playersArr[j].getName() + " place your bet:", betTypes)]);

            responsesObj = frame.showQuestionAndGetResponses(temp,false);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(temp,j,bet_player[j]*2,bet_player[j]);
            updateFrame_ShowPopUp_Clear_Responses(temp);
        }
    }

}
