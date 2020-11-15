import java.util.*;

public class Questions {
    private HashMap<String,ArrayList<Question>> hash; // A HashMap that key = category of question,
                                                     // value =  an arraylist(question) that contains all the questions of the same category
    private HashMap<String, Iterator<Question>> hashIterators;
    public Questions() {
        //question_array = new ArrayList<>();
        //hash = new HashMap<>();
        hash = new HashMap<>();
        hashIterators = new HashMap<>();

    }

    /**
     *
     * @param name
     * @param type
     * @param responses_array
     */
    public void addQuestion(String name,String type, ArrayList<String> responses_array){
        Question obj = new Question(name,type,responses_array);
        hash.putIfAbsent(obj.getType(), new ArrayList<>());
        hash.get(obj.getType()).add(obj);
        if(hash.get(obj.getType()).size() % 10 == 0){ // Every 5th element the arraylist that contains the questions will be shuffled;
            Collections.shuffle(hash.get(obj.getType()));
        }


        hashIterators.putIfAbsent(obj.getType(), hash.get(obj.getType()).iterator());
    }


    /**
     *
     * @param type A string that contains the type of the question to be returned .
     * @return An object Question with the specified type that HASN'T BEEN REQUESTED AGAIN if no object is found it returns NULL
     */
    public Question getRandomQuestionWithType(String type){
        Iterator<Question> it_current_type = hashIterators.get(type);
        if (it_current_type.hasNext()){ return it_current_type.next();}
        return null;
    }


    /**
     * @return An object Question from a random type. if
     */
    public Question getRandomQuestion(){
        int pos = randint(0,hashIterators.size());
        ArrayList<String> hash_keys = new ArrayList<>(hashIterators.keySet());
        if(hashIterators.get(hash_keys.get(pos)).hasNext()){
            return getRandomQuestionWithType(hash_keys.get(pos));
        }
        //System.out.println(pos);
        for (int i = (pos+1)%hash_keys.size(); i!=pos; i=(i+1)%hash_keys.size())
        {
            //System.out.println(i);
            if(hashIterators.get(hash_keys.get(i)).hasNext()){
                return getRandomQuestionWithType(hash_keys.get(i));
            }
        }
        return null;

    }

    /**
     * Resets all questions status to not shown before
     */
    public void resetAllViewed(){
        for (String key: hash.keySet()){
            hashIterators.put(key,hash.get(key).iterator());
        }
    }


    private int randint(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

}
