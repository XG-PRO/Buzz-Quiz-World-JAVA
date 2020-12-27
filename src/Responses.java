/**
 * The class keeps the order in which the players answered
 * For example if the "Player 2" has answered before "Player 1"
 * he will be added first in the array.
 *
 * How it works: When a player has responded,
 * the method addPlayerResponse should be called.
 */
public class Responses {
    Player[] plArr;
    Boolean[] respArr;
    int numOfPlayers;
    int current_player_pos;

    /**
     * Default Contactor
     * @param numOfPlayers The number of players in game.
     */
    public Responses(int numOfPlayers){
        this.plArr = new Player[numOfPlayers];
        this.respArr = new Boolean[numOfPlayers];
        this.numOfPlayers = numOfPlayers;
        current_player_pos = 0;
    };

    /**
     * Clear all the players responses (sets to null)
     */
    public void clear_responses(){
        for (int i =0 ; i < numOfPlayers; i++){
            plArr[i] = null;
            respArr[i] = null;
        }
        current_player_pos = 0;
    }

    /**
     * Adds a player response.
     * @param pl The player object
     * @param hasPlayerAnswerCorrectly Boolean. If the player responses was correct.
     */
    public void addPlayerResponse(Player pl, Boolean hasPlayerAnswerCorrectly){
        if (current_player_pos >= numOfPlayers)
            System.out.println("ERROR! in Responses addPlayerResponse. You have tried to add more responses. (number of responses >= numb of players). Try to clean the responses object every time the round has ended");
        this.plArr[current_player_pos] = pl;
        this.respArr[current_player_pos] = hasPlayerAnswerCorrectly;
        current_player_pos++;
    }

    /**
     * Returns the player who answered at "pos" position
     * @param pos The player pos.
     * @return the player who answered at "pos" position
     */

    public Player getPlayerAtPos(int pos){
        return plArr[pos];
    }

    /**
     * Returns if the player who answered at "pos" position was correct.
     * @param pos The player pos.
     * @return if the player who answered at "pos" position was correct
     */
    public Boolean getResponseAtPos(int pos){
        return respArr[pos];
    }
}
