/** This class represents each Player (currently only 1).
 *  It is responsible for the handling of each individual's points
 */

public class Player {
    private int points;
    //private ArrayList<String> keyboard_responses;

    public Player(){
        points = 0;
        /*

        //Defensive Copying
        this.keyboard_responses = new ArrayList<String>(keyboard_responses.size());
        this.keyboard_responses.addAll(keyboard_responses);

       */
    }

    /** The below three functions return the current points of the player,
     * increase them and decrease them by a certain amount
     * and can be used by any appropriate class.
     *
     */
    public int getPoints()
    {
        return points;
    }

    public void increasePoints(int p){
        points+=p;
    }

    public void decreasePoints(int p){
        points-=p;
    }

}

