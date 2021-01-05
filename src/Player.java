/** This class represents each Player (currently only 1).
 *  It is responsible for the handling of each individual's points
 */

public class Player {
    private final String name;
    private int points;
    private final char[] keyboard_responses;



    private boolean hasWon = false;
    public Player(String name, char[] keys){
        points = 0;
        this.name = name;
        keyboard_responses = new char[keys.length];
        for (int i =0;i<keys.length; i++)
            keyboard_responses[i] = keys[i];
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

    public String getName(){
        return name;
    }

    public char[] getKeyboard_responses(){
        return keyboard_responses;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
    public boolean isHasWon() {
        return hasWon;
    }


}

