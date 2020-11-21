import java.util.ArrayList;

public class Utilities {
    public static ArrayList<String> CreateArrayListString(String [] IN) {
        ArrayList<String> OUT = new ArrayList<>(IN.length);
        for (String item : IN)
            OUT.add(item);
        return OUT;
    }
    public static ArrayList<Character> CreateArrayListCharacter(Character [] IN) {
        ArrayList<Character> OUT = new ArrayList<>(IN.length);
        for (Character item : IN)
            OUT.add(item);
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
}
