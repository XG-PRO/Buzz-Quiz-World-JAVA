import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Parser {
    private final Scanner reader;




    public Parser() {
        reader = new Scanner(System.in);
    }
    public char getUserInput(ArrayList<Character> valid_responses){
        String sp;
        char rp = ' ';
        System.out.print("> ");
        sp = reader.nextLine();
        if (!sp.isEmpty()){
            rp = sp.charAt(0);
        }
        while(!valid_responses.contains(rp)){
            System.out.println("Please enter an acceptable response key (Only the first character of your answer will be counted):");
            System.out.print("> ");
            sp = reader.nextLine();
            if (!sp.isEmpty()){
                rp = sp.charAt(0);
            }
        }
        return rp;

    }

    public void Welcome()
    {
        System.out.println("Welcome to ...");
        System.out.println("\n" +
                "██████╗░██╗░░░██╗███████╗███████╗  ░██████╗░██╗░░░██╗██╗███████╗\n" +
                "██╔══██╗██║░░░██║╚════██║╚════██║  ██╔═══██╗██║░░░██║██║╚════██║\n" +
                "██████╦╝██║░░░██║░░███╔═╝░░███╔═╝  ██║██╗██║██║░░░██║██║░░███╔═╝\n" +
                "██╔══██╗██║░░░██║██╔══╝░░██╔══╝░░  ╚██████╔╝██║░░░██║██║██╔══╝░░\n" +
                "██████╦╝╚██████╔╝███████╗███████╗  ░╚═██╔═╝░╚██████╔╝██║███████╗\n" +
                "╚═════╝░░╚═════╝░╚══════╝╚══════╝  ░░░╚═╝░░░░╚═════╝░╚═╝╚══════╝");
    }



    public String showQuestion(Question current_qs, ArrayList<Character> valid_responses){
        System.out.println("------------");
        System.out.println(current_qs.getQuestion()+"\t ("+current_qs.getType()+")");
        ArrayList<String> resp = current_qs.getResponses();
        Collections.shuffle(resp);
        for (int i = 0; i < Math.min(valid_responses.size(),resp.size()); i++)
            System.out.println("\t" + valid_responses.get(i)+" : "+resp.get(i));

        return resp.get(valid_responses.indexOf(getUserInput(valid_responses)));
    }

    public boolean showAnswerResult(Question qs, String current_response)
    {
        System.out.println("You answered: " + current_response);

        if(current_response.equals(qs.getRightResponse()))
        {
            System.out.println("You answered correctly :D");
            return true;
        }
        else
        {
            System.out.println("You answered incorrectly :(");
            System.out.println("The actual answer was: " + qs.getRightResponse());
            return false;
        }

    }

    public String[] showOptions(ArrayList<String> question_types, ArrayList<String> round_types,String old_question_type, String old_round_type)
    {
        String current_question_type = old_question_type;
        String current_round_type = old_round_type;
        question_types.add("Random");
        char current_answer = 'a';

        ArrayList<Character> abc_menu_question = Utilities.generateLetters(5);
        ArrayList<Character> abc_type_question = Utilities.generateLetters(question_types.size());
        ArrayList<Character> abc_type_round = Utilities.generateLetters(round_types.size());


        while(current_answer != 'c')
        {
            System.out.println("------------");
            System.out.println("MENU");
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
                    for (int i = 0; i < Math.min(abc_type_round.size(),round_types.size()); i++)
                        System.out.println(abc_type_round.get(i)+" : "+round_types.get(i));

                    current_round_type =  round_types.get(abc_type_round.indexOf(getUserInput(abc_type_round)));
                    break;
                case 'b':
                    System.out.println("------------");
                    System.out.println("Current Question Type is: ");
                    System.out.println("\t" + current_question_type);


                    System.out.println("Choose a Question Type: ");
                    for (int i = 0; i < Math.min(abc_type_question.size(),question_types.size()); i++)
                        System.out.println(abc_type_question.get(i)+" : "+question_types.get(i));

                    current_question_type =  question_types.get(abc_type_question.indexOf(getUserInput(abc_type_question)));

                    break;
                case 'd':
                    System.out.println("Welcome to Buzz Quiz World!\nThere are 2 types of rounds. " +
                            "In each round you answer a set number of random questions from either a random category or a category of your selection.\n" +
                            "By default, every round at the start is random, but you can change it from the MENU.\n" +
                            "In Right Answer, the player get 1000 point for every right answer and loses none when answering incorrectly\n" +
                            "In Bet, the player places a Bet regardless of their current point count, and gains double the amount of the points bet if he \n" +
                            "answers correctly, and loses the sum if he answers incorrectly.\n" +
                            "If you've selected a category and the questions presented are not from the category you originally selected,\n" +
                            "this means that there are no more questions remaining from that category and they are presented at random");
                    break;
                case 'e':
                    Exit(1);

                default:
                    break;
            }
        }
        return new String[] {current_question_type, current_round_type};

    }


    public void endRound(int current_points)
    {

        System.out.println("The round has ended");
        System.out.println("Your current points are: "+ current_points);
    }

/*
public char showQuestion(Question current_qs, ArrayList<Character> valid_responses){
        System.out.println("------------");
        System.out.println(current_qs.getQuestion());
        ArrayList<String> resp = current_qs.getResponses();
        for (int i = 0; i < Math.min(valid_responses.size(),resp.size()); i++)
        {
            System.out.println(valid_responses.get(i)+" : "+resp.get(i));
        }

        return getUserInput(valid_responses);
    }



     */

    public void showPoints(int points)
    {
        System.out.println("Your current score is: ["+points+"]");
    }


    public int Bet(ArrayList<String> bet_types, String current_question_type)
    {
        ArrayList<Character> abc_type_bet = Utilities.generateLetters(bet_types.size());


        System.out.println("------------");
        System.out.println("The current question category is: ["+current_question_type+"]");
        System.out.println("Place your bet: ");
        for (int i = 0; i < Math.min(abc_type_bet.size(),bet_types.size()); i++)
            System.out.println(abc_type_bet.get(i)+" : "+bet_types.get(i));


        String current_bet=  bet_types.get(abc_type_bet.indexOf(getUserInput(abc_type_bet)));

        return Integer.parseInt(current_bet);
    }

    public void Exit(int error){
        if (error == 1){// Normal exit
            System.out.println("Thank you for playing");
            System.exit(0);
        }
        if (error == 0) // No more questions
        {
            System.out.println("There are no more questions in the directory. You answered them all!");
            System.exit(1);
        }

    }

}


