import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    /*
        System.out.println("Hello, let's play Buzz!");
        System.out.println("Write the amount of players (max 2): ");
        int answer = (int) System.in.read();
        boolean play=true;
        while (play==true)
        {
            switch (answer)
            {
                case 1:
                    System.out.println("The game will begin for 1 player");
                    //Players1(); bgazei error
                    break;
                case 2:
                    System.out.println("The game will begin for 2 players");
                    //Players2(); bgazei error
                    break;
                default:
                    System.out.println("Error, please type a number from 1 to 2");
                    break;
            }
            System.out.println("Would you like to play again? (0: yes, 1: no");
            if (answer == 0)
                play=true;
            else
                play=false;
        }

        //Tests you can delete them if you want

        Questions temp = new Questions();
        temp.add_Question("Edo einai errotisi",1,new String[] {"Proti","Deuteri","Triti","Tetarti"});
        temp.add_Question("Edo einai errotisi 1",2,new String[] {"Proti","Deuteri","Triti","Tetarti"});
        temp.add_Question("Edo einai errotisi 1",2,new String[] {"Proti","Deuteri","Triti","Tetarti"});

        Question obj = temp.get_random_question(0);
        System.out.println(obj.getQuestion());
        System.out.println(obj.getType());
        System.out.println(obj.getVisited());
        System.out.println(obj.getResponses()[0]);
        System.out.println(obj.getResponses()[1]);
        System.out.println(obj.getResponses()[2]);
        System.out.println(obj.getResponses()[3]);
*/

        ArrayList<String> arr = new ArrayList<>();
        arr.add("R1");arr.add("R2");

        Questions qs = new Questions();

        for (int i = 0; i<5; i++){
            qs.addQuestion("Question","Type "+(i+1),arr);
        }
        //qs.getRandomQuestionWithType("Type 1").debugShowInfo();
        for (int i = 0; i < 10; i++){
            Question temp = qs.getRandomQuestion();
            if(temp != null){
                System.out.println("Type = "+temp.getType());
            }
            else System.out.println("Null");
            //System.out.println("----");
        }




/*
        HashMap<String,Integer> temp = new HashMap<>();
        temp.putIfAbsent("Α",1);
        temp.putIfAbsent("Α",2);

        Iterator<String> it = temp.keySet().iterator();



        while (it.hasNext()){
            System.out.println(it.next());
        }
        it = temp.keySet().iterator();
        System.out.println(it.next());
        System.out.println(it.next());
         */



    }
}

