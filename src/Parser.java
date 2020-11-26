import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class implements the user interface.
 * It is responsible to print and to get input to/from the terminal.
 */
public class Parser {


    /**
     * A function that prints in the terminal responses and gets the user choice.
     * If the user provides an responses that is not valid, it asks again for an input.
     *
     * @param abc_valid_responses An ArrayList(Character) containing the valid responses
     * @return The response from the user. where response is <b>INSIDE</b> the provided parameter.
     */
    private static char getUserInput(ArrayList<Character> abc_valid_responses){
        Scanner reader = new Scanner(System.in);
        String in_str;
        char in_char = ' ';
        System.out.print("> ");
        in_str = reader.nextLine();
        if (!in_str.isEmpty()){
            in_char = in_str.charAt(0);
        }
        while(!abc_valid_responses.contains(in_char)){ // While the user doesn't give an proper response
            System.out.println("Please enter an acceptable response key (Only the first character of your answer will be counted):");
            System.out.print("> ");
            in_str = reader.nextLine();
            if (!in_str.isEmpty()){
                in_char = in_str.charAt(0);
            }
        }
        return in_char;
    }


    /**
     * Prints a welcome message
     */
    public static void printWelcome()
    {
        System.out.println("Welcome to ...");
        System.out.println(" ██████╗░██╗░░░██╗███████╗███████╗  ░██████╗░██╗░░░██╗██╗███████╗\n" +
                            "██╔══██╗██║░░░██║╚════██║╚════██║  ██╔═══██╗██║░░░██║██║╚════██║\n" +
                            "██████╦╝██║░░░██║░░███╔═╝░░███╔═╝  ██║██╗██║██║░░░██║██║░░███╔═╝\n" +
                            "██╔══██╗██║░░░██║██╔══╝░░██╔══╝░░  ╚██████╔╝██║░░░██║██║██╔══╝░░\n" +
                            "██████╦╝╚██████╔╝███████╗███████╗  ░╚═██╔═╝░╚██████╔╝██║███████╗\n" +
                            "╚═════╝░░╚═════╝░╚══════╝╚══════╝  ░░░╚═╝░░░░╚═════╝░╚═╝╚══════╝\n\n");
    }


    /**
     * <p>This function prints the following:</p>
     * <i>
     *     a : Response 1
     *     b : Response 2
     *     c : Response 3
     *         ...
     * </i>
     * @param abc_responses An ArrayList that contains the characters that the user can press.
     * @param responses An ArrayList that contains the responses.
     * @param shuffle_responses Boolean if true will shuffle the responses ArrayList <b>IT WILL CHANGE THE ORDER OF THE PROVIDED ARRAYLIST!</b>.
     * @return A string that is INSIDE the responses ArrayList.
     */
    private static String printResponsesGetInput(ArrayList<Character> abc_responses,ArrayList<String> responses, boolean shuffle_responses){
        if (shuffle_responses)
            Collections.shuffle(responses);
        for (int i = 0; i < Math.min(abc_responses.size(),responses.size()); i++)
            System.out.println("\t" + abc_responses.get(i)+" : "+responses.get(i));
        return responses.get(abc_responses.indexOf(getUserInput(abc_responses)));
    }


    /**
     * It just prints the question and waits for a response from the user.
     * @param current_question An Question object to print.
     * @param abc_responses An ArrayList that contains the characters that the user can press.
     * @return A string that belongs in the Question responses.
     */
    public static String printQuestion(Question current_question, ArrayList<Character> abc_responses){
        System.out.println("------------");
        System.out.println(current_question.getQuestion()+"\t("+current_question.getType()+")");
        return printResponsesGetInput(abc_responses, current_question.getResponses(), true);
    }

    /**
     * Just prints if the response
     * @param current_question An Question object.
     * @param user_response What the user responded to the question.
     * @return true if the user_response was correct/ the user responded correctly, false if he didnt.
     */
    public static boolean printResponseResult(Question current_question, String user_response)
    {
        System.out.println("\nYou answered: " + user_response);
        if(user_response.equals(current_question.getRightResponse()))
        {
            System.out.println("You answered correctly :D");
            return true;
        }
        System.out.println("You answered incorrectly :(");
        System.out.println("\tThe actual answer was: " + current_question.getRightResponse()+"\n");
        return false;
    }


    /**
     * A function that prints the menu that let the use change the next round type, and Questions type.
     *
     * @param question_types An Arraylist that all the Questions types
     * @param round_types An Arraylist that all the types of round
     * @param old_question_type The current questions type.
     * @param old_round_type The current round type.
     * @return An String[2] that [0] = current_question_type, [1] = round_type.
     */
    public static String[] printMENU(ArrayList<String> question_types, ArrayList<String> round_types,String old_question_type, String old_round_type)
    {
        String current_question_type = old_question_type;
        String current_round_type = old_round_type;
        question_types.add("Random");
        char current_answer = 'a';

        ArrayList<Character> abc_menu_question = Utilities.generateLetters(5);
        ArrayList<Character> abc_type_question = Utilities.generateLetters(question_types.size());
        ArrayList<Character> abc_types_rounds = Utilities.generateLetters(round_types.size());

        while(current_answer != 'c')
        {
            System.out.println("\n------------");

            System.out.println("Round Menu");
            System.out.println("\ta. Change Round Type, current Round Type is: ["+ current_round_type +"]");
            System.out.println("\tb. Change Question Type, current Question Type is: ["+ current_question_type +"]");
            System.out.println("\tc. Start Round");
            System.out.println("\td. Info");
            System.out.println("\te. End Game");

            current_answer = getUserInput(abc_menu_question);
            switch (current_answer)
            {
                case 'a':
                    System.out.println("------------");
                    System.out.println("Current Round Type is: "+current_round_type);
                    System.out.println("Choose a Round Type: ");

                    current_round_type = printResponsesGetInput(abc_types_rounds,round_types,false);
                    break;
                case 'b':
                    System.out.println("------------");
                    System.out.println("Current Question Type is: ");
                    System.out.println("\t" + current_question_type);
                    System.out.println("Choose a Question Type: ");

                    current_question_type =  printResponsesGetInput(abc_type_question, question_types,false);
                    break;
                case 'd':
                    System.out.println("------INFO------\n" +
                            "Welcome to Buzz Quiz World!\nThere are 2 types of rounds.\n" +
                            "In each round you answer a set number of random questions from either a random category or a category of your selection.\n" +
                            "By default, every question and round type is Random, but that can be changed before the start of every round (Round Menu)\n\n" +
                            "If you've selected a category and the questions presented are not from the category you originally selected,\n" +
                            "this means that there are no more questions remaining from that category and they are presented randomly.\n\n"+
                            "Types of Rounds:\n"+
                            "\t1.Right Answer, the player get 1000 point for every right answer and loses none when answering incorrectly\n" +
                            "\t2.Bet, the player places a Bet regardless of their points, if he answers correctly he gains double if not he loses points accordingly to his Bet");
                    break;
                case 'e':
                    Exit(1);

                default:
                    break;
            }
        }
        return new String[] {current_question_type, current_round_type};

    }


    /**
     * This function print the "The round has ended" along with the points.
     * @param current_points The player points.
     */
    public static void printEndRound(int current_points){
        System.out.println("The round has ended");
        showPoints(current_points);
    }


    /**
     * Prints the Player points
     * @param points int player points
     */
    public static void showPoints(int points){
        System.out.println("Your current score is: ["+points+"]");
    }


    /**
     * Ask from the user to place a bet
     * @param bet_types An ArrayList that contains the bets that the user can choose.
     * @param current_question_type A String that contains the question type of the current question.
     * @return An int with the value of the bet.
     */
    public static int Bet(ArrayList<String> bet_types, String current_question_type)
    {
        ArrayList<Character> abc_type_bet = Utilities.generateLetters(bet_types.size());
        System.out.println("------------");
        System.out.println("The current question category is: ["+current_question_type+"]");
        System.out.println("Place your bet: ");
        String current_bet=  printResponsesGetInput(abc_type_bet, bet_types, false);

        return Integer.parseInt(current_bet);
    }


    /**
     * A method that prints endMessages and exits
     * @param error if error == 1 prints "Thank you for playing", if error == 0 prints No more questions error.
     */
    public static void Exit(int error){
        if (error == 1){// Normal exit
            System.out.println("Thank you for playing");
            System.exit(0);
        }
        else if (error == 0){// No more questions
            System.out.println("Sadly there are no more questions in the directory. You answered them all?");
            System.exit(1);
        }
    }
}