import java.util.ArrayList;

public class Rounds{
    private Questions qs;
    private Parser ps;
    private int number_of_rounds = 6;
    private int number_of_questions = 5;

    private String current_question_type = null;

    private String current_round_type = "Right Answer";
    private ArrayList<String> rounds_types;

    public Rounds(Questions qs, Parser ps) {
        this.qs = qs;
        this.ps = ps;


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

    }


    public void StartRound(){

        String current_type = null;
        for (int i = 0; i<number_of_rounds;i++) {

            Menu();
            System.out.println("Current Round Count: " + (i+1));
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


    }

    private void Menu()
    {
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
                current_question_type = null;
                temp = qs.getRandomQuestionWithType(null);
            }
            if (temp == null)
                ps.Exit(0);  //RAN OUT OF QUESTIONS

            /*
            ArrayList<Character> valid_responses = new ArrayList<>();
            valid_responses.add('a');
            valid_responses.add('b');
            valid_responses.add('c');
            valid_responses.add('d');
            */
            ArrayList<Character> valid_responses = Utilities.generateLetters(4);


            String current_respons = ps.showQuestion(temp, valid_responses);

            ps.showAnswerResult(temp, current_respons);
        }
        ps.endRound();


    }

    private void RoundType_Bet()
    {

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

