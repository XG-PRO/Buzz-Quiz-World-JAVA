import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Question {
    /**
     * The question itself string
     */
    private String name;

    /**
     * The type of the question is stored in a number
     * 1 = Ιστορια
     * 2 = Γεωγραφία
     * 3 = Τέχνες
     * 4 = Τεχνολογία
     * 5 = Θρησκεία
     */

    private final int type;
    /**
     * The responses are stored in a list
     */
    private String[] responses_array;
    //private String path_to_image;
    private boolean has_been_visited;
    /**
     * Default constructor
     * @param type The type of the question (1= Σωστή απάντηση,2 = Σταμάτησε το χρονόμετρο, 3 = Ποντάρισμα, 4 = Γρήγορη απάντηση, 5 = Θερμόμετρο)
     * @param responses_array A String array that contains the responses,
     *                      THE FIRST RESPONSE IS THE RIGHT ONE
     */
    public Question(String name,int type, String[] responses_array) {
        this.name = name;
        this.type = type;
        this.responses_array = new String[responses_array.length];
        System.arraycopy(responses_array,0,this.responses_array,0,responses_array.length);
        has_been_visited = false;
    }
    /**
     * @return A String array that contains the responses
     */
    public String[] getResponses(){
        String[] temp = new String[responses_array.length];
        System.arraycopy(responses_array,0,temp,0,responses_array.length);
        return temp;
    }
    public String getQuestion() {
        return name;
    }

    public int getType() {
        return type;
    }
    public boolean getVisited(){
        return has_been_visited;
    }
    public void setToVisited(){
        has_been_visited = true;
    }
}
