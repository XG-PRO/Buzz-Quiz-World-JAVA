import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



public class Game {

    private Questions qs;


    public Game() {
        qs = new Questions();
    }


    void play() {
        readQuestions("files/quiz.tsv");
        qs.addQuestion("ελληνικα","QS1","a1","a2","a3","a4");
        System.out.println(qs.getRandomQuestionWithType("Science").getType());


    }

    private void readQuestions(String filepath) {
        //parsing a CSV file into Scanner class constructor
        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new File("files/quiz.tsv"));
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!");
            System.exit(-1);

        }
        sc.useDelimiter("\t");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            qs.addQuestion(sc.next(),sc.next(),new String []{sc.next(),sc.next(),sc.next(),sc.next()});
            if(sc.hasNext()){sc.next();}
            if(sc.hasNext()){sc.next();}
            //System.out.print(sc.next()+":");  //find and returns the next complete token from this scanner
        }
        sc.close();  //closes the scanner


         */

        ArrayList<String[]> Data = new ArrayList<>(); //initializing a new ArrayList out of String[]'s
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(filepath))) {
            String line = null;
            while ((line = TSVReader.readLine()) != null) {
                String[] lineItems = line.split("\t"); //splitting the line and adding its items in String[]
                /*
                System.out.println(lineItems[0]);//κατηγορία
                System.out.println(lineItems[1]);//ερώτηση
                System.out.println(lineItems[2]);//απαντηση 1
                System.out.println(lineItems[3]);//απαντηση 2
                System.out.println(lineItems[4]);//απαντηση 3
                System.out.println(lineItems[5]);//απαντηση 4
                System.out.println(lineItems[5]);//σωστη απαντηση
                System.out.println(lineItems[6]);//ονομα εικόνας
                 */
                qs.addQuestion(lineItems[0],lineItems[1],lineItems[2],lineItems[3],lineItems[4],lineItems[5]);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong ");
            System.exit(-1);
        }




    }


}

