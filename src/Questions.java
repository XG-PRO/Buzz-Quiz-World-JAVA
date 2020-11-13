import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Questions {

    private ArrayList<Question> question_array;
    private HashMap<Integer, ArrayList<Question>> hash;

    public Questions() {
        question_array = new ArrayList<>();
    }

    /**
     *
     * @param name The name of the question
     * @param type The type of the question
     * @param responses_array String array that contains the responses,
     *                        THE FIRST RESPONSE IS THE RIGHT ONE
     */

    public void add_Question(String name, int type, String[] responses_array) {
        //question_array.add(new Question(name,type,responses_array));
        if (!hash.containsKey(type)){
            hash.put(type,new ArrayList<>());
        }
        hash.get(type).add(new Question(name,type,responses_array));
    }
    public void get_random_question(int type){
        if (type==0){
            int random =getRandomNumberUsingNextInt(1,hash.keySet().size());
        }

    }
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
