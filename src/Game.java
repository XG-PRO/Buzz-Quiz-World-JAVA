import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Game {

    private Questions qs;


    public Game() {
        qs = new Questions();
    }


    void play() {
        readQuestions();
        Question temp = qs.getRandomQuestion();
        System.out.println("NUMBER OF TOTAL QUESTIONS :"+qs.show_number());
        for (int i=0; i<80; i++) {
            if (temp == null){
                System.out.println("NULL");
            }
            else{
                temp.debugShowInfo();
            }
            temp = qs.getRandomQuestion();

        }

    }

    private void readQuestions() {
        //parsing a CSV file into Scanner class constructor

        Scanner sc = null;
        try {
            sc = new Scanner(new File("files/questions.tsv"));
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
            System.exit(-1);

        }
        sc.useDelimiter("\\u0009");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            qs.addQuestion(sc.next(),sc.next(),new String []{sc.next(),sc.next(),sc.next(),sc.next()});
            if(sc.hasNext()){sc.next();}
            if(sc.hasNext()){sc.next();}
            //System.out.print(sc.next()+":");  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner




    }


}

