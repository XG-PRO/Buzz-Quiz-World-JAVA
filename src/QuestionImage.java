import java.util.ArrayList;

public class QuestionImage extends Question {
    final private String image;

    /**
     * Default constructor
     *
     * @param name            A string that contains the question itself.
     * @param type            A string that contains the type of the question.
     * @param responses_array An ArrayList(String) that contains the responses, THE FIRST RESPONSE IS ALWAYS THE RIGHT ONE.
     * @param image           contains the image src
     */
    public QuestionImage(String name, String type, ArrayList<String> responses_array, String image) {
        super(name, type, responses_array);
        this.image = image;
    }

    public final void debugShowInfo() {
        System.out.println("---------- Question Image");
        System.out.println("ID        : " + this.hashCode());
        System.out.println("Name      : " + this.name);
        System.out.println("Type      : " + this.type);
        System.out.println("Image     : " + this.image);
        System.out.println("Responses :");
        for (String item : responses_array) {
            System.out.println("          " + item);
        }
        System.out.println("----------");

    }

}
