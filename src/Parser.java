import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Parser {
    private Scanner reader;
    private Rounds rs;



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
        System.out.println("Welcome to Buzz Quiz World! ");

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
            System.out.println(valid_responses.get(i)+" : "+resp.get(i));

        return resp.get(valid_responses.indexOf(getUserInput(valid_responses)));
    }

    public void showAnswerResult(Question qs, String current_respons)
    {
        if(current_respons.equals(qs.getRightResponse()))
            System.out.println("You answered correctly :D");
        else
        {
            System.out.println("You answered incorrectly :(");
            System.out.println("The actual answer was: " + qs.getRightResponse());
        }

        System.out.println("You answered: " + current_respons);

    }

    public String[] showOptions(ArrayList<String> question_types, String old_question_type, String old_round_type)
    {
        String current_question_type = old_question_type;
        String current_round_type = old_round_type;
        char current_answer = 'a';
        /*
        ArrayList<Character> menu_question = new ArrayList<>();
        menu_question.add('a');
        menu_question.add('b');
        menu_question.add('c');
        ArrayList<Character> round_question = new ArrayList<>();
        round_question.add('a'); // Right Answer
        round_question.add('b');
        */
        //ArrayList<Character> menu_question = Utilities.CreateArrayListCharacter(new Character[] {'a','b','c'});
        ArrayList<Character> menu_question = Utilities.generateLetters(3);
        ArrayList<Character> round_question = Utilities.generateLetters(question_types.size());


        while(current_answer != 'c')
        {
            current_answer = 'c';
            System.out.println("------------");
            System.out.println("MENOU");
            System.out.println("    a. Change Round Type");
            System.out.println("    b. Change Question Type");
            System.out.println("    c. Start Round");
            current_answer = getUserInput(menu_question);
            switch (current_answer)
            {
                case 'a':
                    System.out.println("------------");
                    System.out.println("Current Round Type is: ");
                    switch(current_round_type)
                    {
                        case "a":
                            System.out.println("Right Answer");
                            break;
                        case "b":
                            System.out.println("Bet");
                            break;
                        default:
                            break;
                    }
                    System.out.println("Choose a Round Type: ");
                    System.out.println("    a. Right Answer");
                    System.out.println("    b. Bet");
                    getUserInput(round_question);
                    break;
                case 'b':
                    System.out.println("------------");
                    System.out.println("Current Question Type is: ");
                    if (current_question_type==null)
                        System.out.println("    Random");
                    else
                        System.out.println("    " + current_question_type);


                    System.out.println("Choose a Question Type: ");
                    for (int i = 0; i < Math.min(round_question.size(),question_types.size()); i++)
                        System.out.println(round_question.get(i)+" : "+question_types.get(i));

                    current_question_type =  question_types.get(round_question.indexOf(getUserInput(round_question)));


                    break;
                default:
                    break;
            }
        }
        return new String[] {current_question_type, current_round_type};

    }


    public void endRound()
    {
        System.out.println("The round has ended");
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


