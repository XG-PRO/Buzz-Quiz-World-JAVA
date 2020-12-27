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
    private HashMap<Player,Boolean> playerResponse;
    private Player[] plArr;
    private String[] respArr;
    private int numOfPlayers;
    private int current_player_pos;
    private boolean ignoreInput;

    /**
     * Default Contactor
     * @param numOfPlayers The number of players in game.
     */
    public Responses(int numOfPlayers){
        ignoreInput = false;
        this.playerResponse =new HashMap<>(numOfPlayers);
        this.plArr = new Player[numOfPlayers];
        this.respArr = new String[numOfPlayers];
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
        for (Player key : playerResponse.keySet()){
            playerResponse.put(key,false);
        }
        current_player_pos = 0;
    }

    /**
     * Adds a player response.
     * @param pl The player object
     * @param playerResponseString String. The player response.
     */
    public boolean addPlayerResponse(Player pl, String playerResponseString){
        if (playerResponse.containsKey(pl)){ //If the player has already answered the question just ignore it.
            if (playerResponse.get(pl))
                return false;
        }
        if (current_player_pos >= numOfPlayers) {
            System.out.println("ERROR! in Responses 'addPlayerResponse'. You have tried to add more responses. (number of responses >= numb of players). Try to clean the responses object every time the round has ended");
            return false;
        }
        this.plArr[current_player_pos] = pl;
        this.respArr[current_player_pos] = playerResponseString;
        playerResponse.put(pl,true);
        current_player_pos++;
        System.out.println("Added RESPONSE\n\tPlayer:"+pl.getName()+"\n\tString:"+playerResponseString);
        System.out.println("\t number of players : "+numOfPlayers);
        System.out.println("\t number of replied players : "+current_player_pos);
        return true;
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

    public void setIgnoreInput(boolean ignoreInput){
        this.ignoreInput = ignoreInput;
    }

    public boolean haveAllPlayersResponed(){
        if (current_player_pos == numOfPlayers)
            return true;
        return false;
    }
}
