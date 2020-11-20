import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private Scanner reader;
    private String inputLine;



    public Parser() {
        reader = new Scanner(System.in);
    }
    public char getUserInput(ArrayList<Character> valid_responses){
        char rp;
        System.out.print("> ");
        rp = reader.nextLine().charAt(0);
        while(!valid_responses.contains(rp)){
            System.out.print("> ");
            rp = reader.nextLine().charAt(0);
        }
        return rp;


    }

    public char showQuestion(Question current_qs, ArrayList<Character> valid_responses){
        System.out.println("------------");
        System.out.println(current_qs.getQuestion());
        ArrayList resp = current_qs.getResponses();
        for (int i = 0; i < Math.min(valid_responses.size(),resp.size()); i++)
        {
            System.out.println(valid_responses.get(i)+" : "+resp.get(i));
        }

        return getUserInput(valid_responses);
    }




}
