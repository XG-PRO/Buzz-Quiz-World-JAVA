import java.util.ArrayList;

public class QuestionImage extends Question{

    private final String imageName;
    /**
     * Default constructor
     *
     * @param name            A string that contains the question itself.
     * @param type            A string that contains the type of the question.
     * @param responses_array An ArrayList(String) that contains the responses, THE FIRST RESPONSE IS ALWAYS THE RIGHT ONE.
     */

    public QuestionImage(String name, String type, ArrayList<String> responses_array,String imageName) {
        super(name, type, responses_array);
        this.imageName = imageName;
    }
    public String getImageName() {
        return this.imageName;
    }

}
