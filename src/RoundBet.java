public class RoundBet extends Round{
    private String[] bet_types;
    RoundBet(Questions questionsObj, GUI frame, Player[] playersArr) {
        super(questionsObj, frame, playersArr);
         bet_types= new String[]{"250", "500", "750", "1000"};
    }
    void playRound(){
        frame.changeRoundType("Bet");
        for (int i = 0; i < numberOfQuestions; i++) {
            Question temp = getRoundQuestion();

            int [] bet_player = new int [playersArr.length];

            for (int j = 0; j< playersArr.length;j++)
                bet_player[j] = Integer.parseInt(bet_types[frame.popupInput("Question Category is:\n" + temp.getType() + "\n\n" + playersArr[j].getName() + " place your bet:", bet_types)]);

            responsesObj = frame.showQuestionAndGetResponses(temp);

            gainedPointsHash.clear();

            for (int j = 0; j < playersArr.length; j++)
                pointCalculator(temp,j,bet_player[j]*2,bet_player[j]);
            updateFrame(temp);
        }
    }

}
