import java.util.ArrayList;

public class Rounds{
    private Questions qs;
    private Parser ps;
    private String current_question_type = null;
    private int number_of_rounds = 6;
    private int number_of_questions = 5;
    private String current_round_type = "a";


    public Rounds(Questions qs, Parser ps) {
        this.qs = qs;
        this.ps = ps;
    }


    public void StartRound(){
        /**
         * Run Rounds:
         * 1 - Right Answer
         * 2 - Stop the Counter
         * 3 - Bet
         * 4 - Quick Answer
         * 5 - Thermometer
         */
        String current_type = null;
        for (int i = 0; i<number_of_rounds;i++) {

            Menu();
            System.out.println("Current Round Count: " + (i+1));
            switch (current_round_type)
            {
                case "b":
                    RoundType2();
                    break;
                case "c":
                    RoundType3();
                    break;
                case "d":
                    RoundType4();
                    break;
                case "e":
                    RoundType5();
                    break;
                default:
                    RoundType1();
                    break;
            }

        }


    }

    private void Menu()
    {
        String[] me = new String[2];
        me = ps.showOptions(qs.getTypes(), current_question_type, current_round_type);
        current_round_type = me[1];
        current_question_type = me[0];

    }



    private void RoundType1()
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

    private void RoundType2()
    {

    }

    private void RoundType3()
    {

    }

    private void RoundType4()
    {

    }

    private void RoundType5()
    {

    }
}

