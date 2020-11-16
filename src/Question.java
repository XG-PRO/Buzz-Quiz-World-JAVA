import java.util.ArrayList;

public class Question {
    private final String name;
    private final String type;
    private final ArrayList<String> responses_array;
    //private String path_to_image; TO BE ADDED TO THE NEXT VERSION


    /**
     * Default constructor
     * @param name A string that contains the question itself.
     * @param type A string that contains the type of the question.
     * @param responses_array An ArrayList(String) that contains the responses, THE FIRST RESPONSE IS ALWAYS THE RIGHT ONE.
     */
    public Question(String name,String type, ArrayList<String> responses_array) {
        this.name = name;
        this.type = type;
        //Defensive Copying
        this.responses_array = new ArrayList<>(responses_array.size());
        this.responses_array.addAll(responses_array);
    }


    /**
     * @return An ArrayList(String) that contains the responses, THE FIRST ONE IS THE CORRECT ONE.
     */
    public final ArrayList<String> getResponses(){
        //Defensive Copying
        ArrayList<String> temp = new ArrayList<>(responses_array.size());
        temp.addAll(this.responses_array);
        return temp;
    }


    /**
     * @return A string contain the question
     */
    public final String getQuestion() {
        return name;
    }


    /**
     *
     * @return A string that contains the type of the question.
     */
    public final String  getType() {
        return type;
    }

    /**
     * Prints all the field of the question
     */
    public final void debugShowInfo(){
        System.out.println("----------");
        System.out.println("ID        : "+this.hashCode());
        System.out.println("Name      : "+this.name);
        System.out.println("Type      : "+this.type);
        System.out.println("Responses :");
        for (String item: responses_array){
            System.out.println("          "+item);
        }
        System.out.println("----------");
    }

}
