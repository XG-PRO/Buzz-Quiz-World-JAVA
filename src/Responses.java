import java.util.HashMap;

/**
 * The class keeps the order in which the players answered
 * For example if the "Player 2" has answered before "Player 1"
 * he will be added first in the array.
 *
 * How it works: When a player has responded,
 * the method addPlayerResponse should be called.
 */
public class Responses {
    private final HashMap<Player,Boolean> playerResponse;
    private final Player[] plArr;
    private final String[] respArr;
    private final int numOfPlayers;
    private int current_player_pos;

    private final int[] time;
    /**
     * Default Contactor
     * @param numOfPlayers The number of players in game.
     */
    public Responses(int numOfPlayers){

        this.playerResponse =new HashMap<>(numOfPlayers);
        this.plArr = new Player[numOfPlayers];
        this.respArr = new String[numOfPlayers];
        this.time = new int[numOfPlayers];
        this.numOfPlayers = numOfPlayers;
        current_player_pos = 0;
    }

    /**
     * Clear all the players responses (sets to null)
     */
    public void clearReset(){
        for (int i =0 ; i < numOfPlayers; i++){
            plArr[i] = null;
            respArr[i] = null;
            time[i] = 0;
        }
        playerResponse.replaceAll((k, v) -> false);
        current_player_pos = 0;

    }

    /**
     * Adds a player response.
     * @param pl The player object
     * @param playerResponseString String. The player response.
     */
    public void addPlayerResponse(Player pl, String playerResponseString, int time){
        if (playerResponse.containsKey(pl)){ //If the player has already answered the question just ignore it.
            if (playerResponse.get(pl))
                return;
        }
        if (current_player_pos >= numOfPlayers) {
            System.out.println("ERROR! in Responses 'addPlayerResponse'. You have tried to add more responses. (number of responses >= numb of players). Try to clean the responses object every time the round has ended");
            return;
        }
        this.plArr[current_player_pos] = pl;
        this.respArr[current_player_pos] = playerResponseString;
        playerResponse.put(pl,true);
        this.time[current_player_pos] = time;
        current_player_pos++;
    }

    /**
     * Returns the player who answered at "pos" position.
     * @param pos The player pos.
     * @return the player who answered at "pos" position.
     */

    public Player getPlayerAtPos(int pos){
        return plArr[pos];
    }

    /**
     * Returns response of the player who answered as pos position.
     * @param pos The player pos.
     * @return The player who answered at "pos" position.
     */
    public String getResponseAtPos(int pos){
        return respArr[pos];
    }
    public int getTimeAtPos(int pos){
        return time[pos];
    }

    public boolean atLeastOnePlayerHaveNOTRespond(){
        return current_player_pos != numOfPlayers;
    }
}
