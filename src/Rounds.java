import java.util.ArrayList;

public class Rounds{
    private final Questions qs;
    private final Parser ps;
    private final Player pl;
    private final int number_of_rounds = 6;
    private final int number_of_questions = 5;

    private String current_question_type = "Random";

    private String current_round_type ;
    private ArrayList<String> rounds_types;
    private ArrayList<String> bet_types;

    public Rounds(Questions qs, Parser ps, Player pl) {
        this.qs = qs;
        this.ps = ps;
        this.pl = pl;


        /*
         * Run Rounds:
         * 1 - Right Answer
         * 2 - Bet
         * 3 - Stop The Counter
         * 4 - Quick Answer
         * 5 - Thermometer
         */
        //rounds_types = Utilities.CreateArrayListString(new String[] {"Right Answer","Bet","Stop The Counter","Quick Answer","Thermometer"});
        rounds_types = Utilities.CreateArrayListString(new String[] {"Right Answer","Bet"});
        bet_types = Utilities.CreateArrayListString(new String[] {"250","500","750","1000"});

    }


    public void StartRound(){

        for (int i = 0; i<number_of_rounds;i++) {

            current_round_type = rounds_types.get(Utilities.random_int(0, rounds_types.size()));
            System.out.println("Current Round Count: " + (i+1)+"/"+number_of_rounds);
            Menu();
            switch (current_round_type)
            {
                case "Bet":
                    RoundType_Bet();
                    break;
                case "Stop The Counter":
                    RoundType_StopTheCounter();
                    break;
                case "Quick Answer":
                    RoundType_QuickAnswer();
                    break;
                case "Thermometer":
                    RoundType_Thermometer();
                    break;
                default:
                    RoundType_RightAnswer();
                    break;
            }

        }
        ps.Exit(1);


    }

    private void Menu()
    {
        ps.showPoints(pl.getPoints());
        String [] me = ps.showOptions(qs.getTypes(), rounds_types, current_question_type, current_round_type);
        current_round_type = me[1];
        current_question_type = me[0];

    }



    private void RoundType_RightAnswer()
    {
        System.out.println("Round Type: Right Answer");
        for (int i=0;i<number_of_questions;i++) {
            Question temp = qs.getRandomQuestionWithType(current_question_type);
            if (temp == null) {
                current_question_type = "Random";
                temp = qs.getRandomQuestionWithType("Random");
            }
            if (temp == null)
                ps.Exit(0);  //RAN OUT OF QUESTIONS


            ArrayList<Character> valid_responses = Utilities.generateLetters(4);



            String current_response = ps.showQuestion(temp, valid_responses);
            if (ps.showAnswerResult(temp, current_response))
            {
                pl.increasePoints(1000);
            }
            ps.showPoints(pl.getPoints());
        }
        ps.endRound(pl.getPoints());


    }

    private void RoundType_Bet()
    {
        System.out.println("Round Type: Bet");
        for (int i=0;i<number_of_questions;i++) {
            Question temp = qs.getRandomQuestionWithType(current_question_type);
            if (temp == null) {
                current_question_type = "Random";
                temp = qs.getRandomQuestionWithType("Random");
            }
            if (temp == null)
                ps.Exit(0);  //RAN OUT OF QUESTIONS


            ArrayList<Character> valid_responses = Utilities.generateLetters(4);

            int bet = ps.Bet(bet_types, temp.getType());

            String current_response = ps.showQuestion(temp, valid_responses);
            if (ps.showAnswerResult(temp, current_response))
            {
                pl.increasePoints(bet*2);// Duplicate the points of the player
            }
            else
            {
                pl.decreasePoints(bet);
            }
            ps.showPoints(pl.getPoints());
        }
        ps.endRound(pl.getPoints());

    }

    private void RoundType_StopTheCounter()
    {

    }

    private void RoundType_QuickAnswer()
    {

    }

    private void RoundType_Thermometer()
    {

    }
}

