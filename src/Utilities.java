import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Utilities {
    public static ArrayList<String> CreateArrayListString(String [] IN) {
        ArrayList<String> OUT = new ArrayList<>(IN.length);
        Collections.addAll(OUT, IN);
        return OUT;
    }
    public static ArrayList<Character> CreateArrayListCharacter(Character [] IN) {
        ArrayList<Character> OUT = new ArrayList<>(IN.length);
        Collections.addAll(OUT, IN);
        return OUT;
    }
    public static ArrayList<Character> generateLetters(int count){
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<Character> OUT = new ArrayList<>();
        if (alphabet.length() < count){
            System.out.println("OTAN DINEIS EPILOGES STON XRISTI AUTA PATHENEIS");
            System.exit(-1);
        }
        for (int i = 0; i < count; i++)
            OUT.add(alphabet.charAt(i));
        return OUT;
    }

    /**
     * Generates a random int in a range [0,max].
     * @param max The max value must be max>0;
     * @return The random int
     */
    public static int random_int(int max) {
            return new Random().nextInt(max);
            //return random.ints(min, max).findFirst().getAsInt();
        }
}
