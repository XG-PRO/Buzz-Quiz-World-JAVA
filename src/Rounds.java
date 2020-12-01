import java.util.ArrayList;

/**
 * This class implements the logic of each round. It could be implemented with inheritance but..
 * we decided instead to use methods to implement each type of round.
 */
public class Rounds {

    private final Questions questions_obj; // A questions object to get questions
    private final Player player_obj; // A player object to use the Player class and change their points
    private final int number_of_questions = 5; // The number of questions for each round
    private final ArrayList<String> rounds_types; // A arraylist that contains all the types of rounds, in which other types of rounds can be added antyime
    private final ArrayList<String> bet_types;   // A arraylist that contains all the bets the user can bet (ex 250,500...)
    private String current_question_type = "Random"; // The initial round question type
    private String current_round_type; // The current round type


    /**
     * Default Constructor
     *
     * @param questions_obj A Questions object that contains all the questions.
     * @param player_obj    A Player object to change the points
     */
    public Rounds(Questions questions_obj, Player player_obj) {
        this.questions_obj = questions_obj;
        this.player_obj = player_obj;

        //rounds_types = Utilities.CreateArrayListString(new String[] {"Right Answer","Bet","Stop The Counter","Quick Answer","Thermometer"});
        rounds_types = Utilities.CreateArrayListString(new String[]{"Right Answer", "Bet"});
        bet_types = Utilities.CreateArrayListString(new String[]{"250", "500", "750", "1000"});

    }


    /**
     * This method starts the rounds
     */
    public void StartRound() {

        // The number of rounds
        int number_of_rounds = 6;
        for (int i = 0; i < number_of_rounds; i++) {

            current_round_type = rounds_types.get(Utilities.random_int(rounds_types.size())); // Each Round Type is picked randomly but the user can change it
            //System.out.println("Current Round Count: " + (i+1)+"/"+number_of_rounds);
            Parser.PrintRoundNumber(i + 1, number_of_rounds); //
            RoundMenu();
            switch (current_round_type) {
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

    /**
     * This method when it is called shows the Menu before each round
     * letting the use choose the type of questions and round he wants.
     * <b>THIS METHOD CHANGES THE FIELDS "current_round_type" AND "current_question_type"<b/>
     */
    private void RoundMenu() {
        Parser.showPoints(player_obj.getPoints());
        String[] me = Parser.printMENU(questions_obj.getTypes(), rounds_types, current_question_type, current_round_type);
        current_round_type = me[1];
        current_question_type = me[0];

    }


    /**
     * This method implements the Right Answer
     * It functions by presenting questions equal to the set number of questions per Round (constant in our case),
     * and handles what is shown and gather from the user in order for their points to be handled.
     * It can change the Player, Questions  objects
     */
    private void RoundType_RightAnswer() {
        Parser.PrintRoundType("Right Answer");
        for (int i = 0; i < number_of_questions; i++) {
            Question temp = questions_obj.getRandomQuestionWithType(current_question_type);
            if (temp == null) { // THE CURRENT QUESTION TYPE HAS NO MORE QUESTIONS
                current_question_type = "Random";
                temp = questions_obj.getRandomQuestionWithType("Random");
            }
            if (temp == null) {
                Parser.Exit(0);  //RAN OUT OF QUESTIONS
                temp = new Question("NULL", "NULL", Utilities.CreateArrayListString(new String[]{"NULL"})); // I added this for IntelliJ warnings
            }

            ArrayList<Character> valid_responses = Utilities.generateLetters(4);


            String current_response = Parser.printQuestion(temp, valid_responses);
            if (Parser.printResponseResult(temp, current_response)) {
                player_obj.increasePoints(1000);
            }
            Parser.showPoints(player_obj.getPoints());
        }
        Parser.printEndRound(player_obj.getPoints());


    }


    /**
     * This method implements the Bet
     * Functions exactly as Right Answer, with the added method of betting points
     * It can change the Player,Questions  objects
     */
    private void RoundType_Bet() {
        Parser.PrintRoundType("Bet");
        for (int i = 0; i < number_of_questions; i++) {
            Question temp = questions_obj.getRandomQuestionWithType(current_question_type);
            if (temp == null) {
                current_question_type = "Random";
                temp = questions_obj.getRandomQuestionWithType("Random");
            }
            if (temp == null) {
                Parser.Exit(0);  //RAN OUT OF QUESTIONS
                temp = new Question("NULL", "NULL", Utilities.CreateArrayListString(new String[]{"NULL"})); // I added this for IntelliJ warnings
            }

            ArrayList<Character> valid_responses = Utilities.generateLetters(4);

            int bet = Parser.Bet(bet_types, temp.getType());

            String current_response = Parser.printQuestion(temp, valid_responses);
            if (Parser.printResponseResult(temp, current_response)) {
                player_obj.increasePoints(bet * 2);// Duplicate the points of the player
            } else {
                player_obj.decreasePoints(bet);
            }
            Parser.showPoints(player_obj.getPoints());
        }
        Parser.printEndRound(player_obj.getPoints());

    }

    /** Unused types of Round which can be implemented in the future if needed **/

    private void RoundType_StopTheCounter() {

    }

    private void RoundType_QuickAnswer() {

    }

    private void RoundType_Thermometer() {

    }
}

