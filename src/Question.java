import java.util.ArrayList;

public class Question {
    private String name;
    private String type;
    private ArrayList<String> responses_array;
    //private String path_to_image; TO BE ADDED TO THE NEXT VERSION


    /**
     * Default constructor
     * @param name A string that contains the question itself.
     * @param type A string that contains the type of the question.
     * @param responses_array An ArrayList(String) that contains the responses, THE FIRST RESPONSE IS THE RIGHT ONE.
     */
    public Question(String name,String type, ArrayList<String> responses_array) {
        this.name = name;
        this.type = type;
        //Defensive Copying
        this.responses_array = new ArrayList(responses_array.size());
        for (String item: responses_array){
            this.responses_array.add(item);
        }
    }

    /**
     * @return An ArrayList(String) that contains the responses, THE FIRST ONE IS THE CORRECT ONE.
     */
    public ArrayList<String> getResponses(){
        //Defensive Copying
        ArrayList<String> temp = new ArrayList(responses_array.size());
        for(String item: this.responses_array){
            temp.add(item);
        }
        return temp;
    }


    /**
     * @return A string that contains the question itself.
     */
    public String getQuestion() {
        return name;
    }


    /**
     *
     * @return A string that contains the type of the question.
     */
    public String getType() {
        return type;
    }
    public void debugShowInfo(){
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
