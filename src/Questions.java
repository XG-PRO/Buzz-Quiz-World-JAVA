import java.util.*;

public class Questions {

    private ArrayList<Question> question_array;
    private HashMap<Integer, ArrayList<Question>> hash;

    public Questions() {
        question_array = new ArrayList<>();
        hash = new HashMap<>();
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
    public Question get_random_question(int type){
        if (type<0 || type>5)
        {
            type = 0;
        }
        if (type==0){
            ArrayList<Integer> hash_keys = new ArrayList<>();
            hash_keys.addAll(hash.keySet());
            type = hash_keys.get(getRandomNumberUsingNextInt(0,hash_keys.size()));
        }
        ArrayList<Question> temp = hash.get(type);
        int pos = getRandomNumberUsingNextInt(0,temp.size());
        if (!temp.get(pos).getVisited()){
            return temp.get(pos);
        }
        for (int i = pos+1; i!=pos; i++)
        {
            if (i>=temp.size()){
                i =0;
            }
            if(!temp.get(i).getVisited()){
                return temp.get(pos);
            }

        }
        System.out.println("Error!!!!!!!!!!!!!!!!");
        return new Question("ERROR",-1, new String[]{"Error"});


    }
    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
