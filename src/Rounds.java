import java.util.ArrayList;

public class Rounds{
    private Questions qs;
    private Parser ps;


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
        RoundType1(5);




    }

    private void RoundType1(int n)
    {
        for (int i=0;i<n;i++) {
            Question temp = qs.getRandomQuestion();
            ArrayList<Character> valid_responses = new ArrayList<>();
            valid_responses.add('a');
            valid_responses.add('b');
            valid_responses.add('c');
            valid_responses.add('d');




           String current_respons = ps.showQuestion(temp, valid_responses);

            if(current_respons==temp.getRightResponse())
                System.out.println("You answered correctly :D");
            else
            {
                System.out.println("You answered incorrectly :(");
                System.out.println("The actual asnwer was: " + temp.getRightResponse());
            }


            System.out.println("You answered: " + current_respons);
        }
        System.out.println("The round has ended");
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

