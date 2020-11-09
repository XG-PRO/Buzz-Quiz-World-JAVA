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
    }

     */
        String[] temp = {"Proti Erotisi","Deuteri","Triti","Tetarti"};
        Question obj = new Question("Edo einai errotisi",5,temp);
        String[] A = obj.getResponses();
        A[0] = "AAA";
        System.out.println(obj.getResponses()[0]);
    }
}

