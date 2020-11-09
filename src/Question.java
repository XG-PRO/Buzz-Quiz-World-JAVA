import java.util.ArrayList;

public class Question {
    /**
     * The question string
     */
    private String question;
    /**
     * The type of the question is stored in a number
     * 1 = Σωστή απάντηση,
     * 2 = Σταμάτησε το χρονόμετρο
     * 3 = Ποντάρισμα
     * 4 = Γρήγορη απάντηση
     * 5 = Θερμόμετρο
     */
    private final int type;
    /**
     * The responses are stored in a list
     */
    private String[] responses_array;
    //private String path_to_image;

    /**
     * Default constructor
     * @param type The type of the question (1= Σωστή απάντηση,2 = Σταμάτησε το χρονόμετρο, 3 = Ποντάρισμα, 4 = Γρήγορη απάντηση, 5 = Θερμόμετρο)
     * @param responses_array A String array that contains the responses,
     *                      THE FIRST RESPONSE IS THE RIGHT ONE
     */
    public Question(String question,int type, String[] responses_array) {
        if (type<1 || type>5){
            throw new IllegalArgumentException(
                    "SFALMA! Argument 'type' should be in the interval 1 <= type< question <= 5");
        }
        this.question = question;
        this.type = type;
        this.responses_array = new String[4];
        System.arraycopy(responses_array,0,this.responses_array,0,responses_array.length);

    }
    /**
     * @return An String array that contains the responses
     */
    public  String[] getResponses(){
        String[] temp = new String[responses_array.length];
        System.arraycopy(responses_array,0,temp,0,responses_array.length);
        return temp;
    }

    public String getQuestion() {
        return question;
    }

    public int getType() {
        return type;
    }

}
