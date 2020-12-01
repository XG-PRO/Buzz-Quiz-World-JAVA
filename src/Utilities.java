import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/** A generic class that is static and global
 * to assist the developers in further improving
 * the consistency of the code
 */
public class Utilities {

    /** These 3 functions basically ease the structure and internal development
     * of the MENU for the parser. They can all be generally used, either for input or output.
     * The most useful one is the generateLetters function which does
     * all of the above but even more dynamically.
     * @param IN and count indicate the options or number of options the developer wants to generate
     * @return a string OUT which contains ansswer protocols in order.
     */

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
