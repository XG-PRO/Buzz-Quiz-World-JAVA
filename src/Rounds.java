import java.util.ArrayList;

public class Rounds{
    private Questions qs;
    private Parser ps;
    private String current_type= null;


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
        //MENU
        RoundType1(5);



    }

    private void RoundType1(int n)
    {
        for (int i=0;i<n;i++) {
            Question temp = qs.getRandomQuestionWithType(current_type);
            if (temp == null) {
                current_type = null;
                temp = qs.getRandomQuestionWithType(current_type);
            }
            if (temp == null)
                ps.Exit(0);  //RAN OUT OF QUESTIONS

            ArrayList<Character> valid_responses = new ArrayList<>();
            valid_responses.add('a');
            valid_responses.add('b');
            valid_responses.add('c');
            valid_responses.add('d');


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

