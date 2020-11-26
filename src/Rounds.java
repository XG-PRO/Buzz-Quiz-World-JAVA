import java.util.ArrayList;

public class Rounds{
    private final Questions question_obj;
    private final Player player_obj;
    private final int number_of_rounds = 6;
    private final int number_of_questions = 5;

    private String current_question_type = "Random";

    private String current_round_type;
    private ArrayList<String> rounds_types;
    private ArrayList<String> bet_types;

    public Rounds(Questions question_obj, Player player_obj) {
        this.question_obj = question_obj;
        this.player_obj = player_obj;

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
        Parser.Exit(1);


    }

    private void Menu()
    {
        Parser.showPoints(player_obj.getPoints());
        String [] me = Parser.printMENU(question_obj.getTypes(), rounds_types, current_question_type, current_round_type);
        current_round_type = me[1];
        current_question_type = me[0];

    }



    private void RoundType_RightAnswer()
    {
        System.out.println("Round Type: Right Answer");
        for (int i=0;i<number_of_questions;i++) {
            Question temp = question_obj.getRandomQuestionWithType(current_question_type);
            if (temp == null) { // THE CURRENT QUESTION TYPE HAS NO MORE QUESTIONS
                current_question_type = "Random";
                temp = question_obj.getRandomQuestionWithType("Random");
            }
            if (temp == null)
                Parser.Exit(0);  //RAN OUT OF QUESTIONS


            ArrayList<Character> valid_responses = Utilities.generateLetters(4);



            String current_response = Parser.printQuestion(temp, valid_responses);
            if (Parser.printResponseResult(temp, current_response))
            {
                player_obj.increasePoints(1000);
            }
            Parser.showPoints(player_obj.getPoints());
        }
        Parser.printEndRound(player_obj.getPoints());


    }

    private void RoundType_Bet()
    {
        System.out.println("Round Type: Bet");
        for (int i=0;i<number_of_questions;i++) {
            Question temp = question_obj.getRandomQuestionWithType(current_question_type);
            if (temp == null) {
                current_question_type = "Random";
                temp = question_obj.getRandomQuestionWithType("Random");
            }
            if (temp == null)
                Parser.Exit(0);  //RAN OUT OF QUESTIONS


            ArrayList<Character> valid_responses = Utilities.generateLetters(4);

            int bet = Parser.Bet(bet_types, temp.getType());

            String current_response = Parser.printQuestion(temp, valid_responses);
            if (Parser.printResponseResult(temp, current_response))
            {
                player_obj.increasePoints(bet*2);// Duplicate the points of the player
            }
            else
            {
                player_obj.decreasePoints(bet);
            }
            Parser.showPoints(player_obj.getPoints());
        }
        Parser.printEndRound(player_obj.getPoints());

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

