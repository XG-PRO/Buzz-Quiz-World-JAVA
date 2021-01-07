/** This class represents each Player (currently only 1).
 *  It is responsible for the handling of each individual's points
 */

public class Player {
    private final String name;
    private int points;
    private final char[] keyboard_responses;
    private boolean hasWon = false;

    /** Each player has his own name, keys and points
     * @param name the name of the player
     * @param keys his corresponding response keys
     */
    public Player(String name, char[] keys){
        this.points = 0;
        this.name = name;
        keyboard_responses = new char[keys.length];
        System.arraycopy(keys, 0, keyboard_responses, 0, keys.length);
    }


    /** The below three functions return the current points of the player,
     * increase them and decrease them by a certain amount
     * and can be used by any appropriate class.
     * They can also return a player's corresponding response keys,
     * his name and whether he has won or not (which can also be set publicly)
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
    public boolean getHasWon() {
        return hasWon;
    }



}
