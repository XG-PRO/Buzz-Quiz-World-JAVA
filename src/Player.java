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

    /**
     *
     * @param p increase player points
     */
    public void increasePoints(int p){
        points+=p;
    }

    /**
     *
     * @param p decrease player points
     */
    public void decreasePoints(int p){
        points-=p;
    }

    public String getName(){
        return name;
    }

    /**
     *
     * @return a player's corresponding response keys,
     */
    public char[] getKeyboard_responses(){
        return keyboard_responses;
    }

    /**
     *
     * @param hasWon set whether player has won or not
     */
    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
    /**
     *
     * @return if player has won or not
     */
    public boolean getHasWon() {
        return hasWon;
    }



}
