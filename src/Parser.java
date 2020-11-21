import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Parser {
    private Scanner reader;



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
    public String showQuestion(Question current_qs, ArrayList<Character> valid_responses){
        System.out.println("------------");
        System.out.println(current_qs.getQuestion());
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


