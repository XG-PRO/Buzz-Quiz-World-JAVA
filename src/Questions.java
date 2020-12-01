import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/** This class handles the logic and usable functions
 * in regards to the Questions of the Quiz
 *
 */
public class Questions {
    // @field hash is a hashMap (type of question : arraylist(question)) it contains all the questions filtered by type
    private final HashMap<String, ArrayList<Question>> hash;

    // @field hashIterators is a hash map (type of question : iterators) it contains iterators for every type of questions
    private final HashMap<String, Iterator<Question>> hashIterators;

    /**
     * Default Constructor
     */
    public Questions() {
        hash = new HashMap<>();
        hashIterators = new HashMap<>();
    }


    /**
     * @param name            A string that contains the question itself.
     * @param type            A string that contains the type of the question.
     * @param responses_array An ArrayList(String) that contains the responses, THE FIRST RESPONSE IS ALWAYS THE RIGHT ONE.
     */
    public void addQuestion(String type, String name, ArrayList<String> responses_array) {
        Question obj = new Question(name, type, responses_array);
        /*
        hash.putIfAbsent(obj.getType(), new ArrayList<>());
        hash.get(obj.getType()).add(obj);
        if(hash.get(obj.getType()).size() % 10 == 0){ // Every 5th element the arraylist that contains the questions will be shuffled;
            Collections.shuffle(hash.get(obj.getType()));
        }
        hashIterators.put(type, hash.get(obj.getType()).iterator());
        */
        addQuestion(obj);
    }


    /**
     * @param obj A question object
     * Since each question added uses tha hash method, every time a question is shown to the user,
     * it is indicated as answered and thus never shown again until restart
     */
    public void addQuestion(Question obj) {

        hash.putIfAbsent(obj.getType(), new ArrayList<>());
        hash.get(obj.getType()).add(obj);
        if (hash.get(obj.getType()).size() % 10 == 0) { // Every 5th element the arraylist that contains the questions will be shuffled;
            Collections.shuffle(hash.get(obj.getType()));
        }
        hashIterators.put(obj.getType(), hash.get(obj.getType()).iterator());
    }


    /**
     * @param type A string that contains the type of the question to be returned. If type is "Random" it returns a Random type question
     * @return An object Question with the specified type that HASN'T BEEN REQUESTED AGAIN if no object found it returns NULL
     */
    public Question getRandomQuestionWithType(String type) {
        if (type.equals("Random")) {
            return getRandomQuestion();
        }
        if (hashIterators.get(type).hasNext()) {
            return hashIterators.get(type).next();
        }
        return null; // no more questions of this type exist
    }


    /**
     * @return An object Question from a random type.
     * Utilizes the getRandomQuestionWithType but using every single type,
     * in other words either the type the player chooses or a random one chosen
     * in this function. If no questions remain at all, the function returns null
     */
    private Question getRandomQuestion() {
        int pos = Utilities.random_int(hashIterators.size());
        ArrayList<String> hash_keys = new ArrayList<>(hashIterators.keySet());
        if (hashIterators.get(hash_keys.get(pos)).hasNext()) {
            return getRandomQuestionWithType(hash_keys.get(pos));
        }
        //System.out.println(pos);
        for (int i = (pos + 1) % hash_keys.size(); i != pos; i = (i + 1) % hash_keys.size()) {
            //System.out.println(i);
            if (hashIterators.get(hash_keys.get(i)).hasNext()) {
                return getRandomQuestionWithType(hash_keys.get(i));
            }
        }
        return null;

    }


    /**
     * Resets all questions status, to not shown before, useful for restarts
     */
    public void resetAllViewed() {
        for (String key : hash.keySet()) {
            hashIterators.put(key, hash.get(key).iterator());
        }
    }


    /**
     * @return an Arraylist of all the types of questions
     */
    public ArrayList<String> getTypes() {
        return new ArrayList<>(hash.keySet());
    }
}
