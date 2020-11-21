import java.util.ArrayList;

public class Player {
    private int points;
    private ArrayList<String> keyboard_responses;

    void Player(ArrayList<String> keyboard_responses){
        points = 0;
        //Defensive Copying
        this.keyboard_responses = new ArrayList<String>(keyboard_responses.size());
        this.keyboard_responses.addAll(keyboard_responses);



    }
}
