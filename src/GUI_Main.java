import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GUI_Main extends GUI{

    GUI_Main() {
        super();
    }

    /**
     * It changes the round counter (bottom - left) JLabel
     *
     * @param roundCount The round type
     */


    public void changeRoundCount(int roundCount) {
        txtRoundCount.setText("ROUND " + roundCount);
    }

    public void changeRoundType(String roundType) {
        txtRoundType.setText(roundType);
    }

    /**
     * Ask from the user the number of players, and returns the result.
     * @param maxNumberOfPlayers The maximum number of players.
     * @return Returns the number of players.
     */
    public int popupAskNumberOfPlayer(int maxNumberOfPlayers) {
        String [] t = new String[maxNumberOfPlayers];
        t[0] = "1 Player";
        for (int i = 1; i < maxNumberOfPlayers; i++) {
            t[i] = (i+1) + " Players";
        }
        numberOfPlayers = popupInput("Number of Players?", t) + 1;
        this.responsesObj = new Responses(numberOfPlayers);
        return numberOfPlayers;
    }

    public String popupGetPlayerName(int i) {

        String temp = JOptionPane.showInputDialog("Enter Name of Player " + i + " :\n","Player "+i);
        /*
        while (temp == null || temp.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Player Name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            temp = JOptionPane.showInputDialog("Enter Name of Player " + i + " :\n","Player "+i);
        }
        */
        if (temp == null || temp.isBlank()){ // If the user closed the window set the default name to be Player + i
            temp = "Player "+i;
        }
        return temp;

    }

    public void popupShowGainedPoints(Player[] player_arr, HashMap<Player,Integer> gainedPointsHash, String correctAnswer)
    {
        StringBuilder temp = new StringBuilder();
        temp.append("The correct answer was : ").append(correctAnswer).append(".\n\n\n");
        for (int i = 0; i < player_arr.length; i++) {
            if (!gainedPointsHash.containsKey(player_arr[i])){
                temp.append(player_arr[i].getName()).append(" didn't responded.\n");
                continue;
            }
            if (gainedPointsHash.get(player_arr[i]) == 0){
                temp.append(player_arr[i].getName()).append(" didn't get any points.\n");
            }
            else {
                if (gainedPointsHash.get(player_arr[i]) >= 0)

                    temp.append(player_arr[i].getName()).append(" gained : ").append(gainedPointsHash.get(player_arr[i]));

                else

                    temp.append(player_arr[i].getName()).append(" lost : ").append(gainedPointsHash.get(player_arr[i]) * (-1));
                temp.append(" points.\n");
            }
        }temp.append("\n\n");

        JOptionPane.showMessageDialog(null, temp.toString(),
                "Results", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method adds the players in the bottom panel.
     * Also this method adds the respond keys.
     * <b>This method adds the playerArr to the private fields of GUI</b>
     * @param playersArr A Array containing Player objects.
     */
    public void drawPlayersInfoToGUI(Player[] playersArr) {
        this.playersArr = playersArr;
        playerToJLabel_HashMap = new HashMap<>();
        for (int i = 0; i < playersArr.length; i++) {
            playerToJLabel_HashMap.put(playersArr[i], new JLabel(playersArr[i].getName() + ":" + playersArr[i].getPoints()));
            playerToJLabel_HashMap.get(playersArr[i]).setFont(font_global);
            playerToJLabel_HashMap.get(playersArr[i]).setForeground(Color.WHITE);
            scorePanel.add(playerToJLabel_HashMap.get(playersArr[i]));
            scorePanel.revalidate();
            scorePanel.add(Box.createHorizontalGlue());
            scorePanel.revalidate();
            //System.out.println("i :" + i + " name : " + playersArr[i].getName());
        }

        for (int i = 0; i < numberOfResponses; i++) {
            String temp = "";
            for (int j = 0; j < numberOfPlayers; j++) {
                temp += playersArr[j].getKeyboard_responses()[i] + " ";
            }
            txtResKeys[i].setText(temp);

        }

        for (int i = 0; i < numberOfResponses; i++) {
            for (Player playerObj : playersArr) {
                characterToJLabel_HashMap.put(playerObj.getKeyboard_responses()[i], txtRes[i]);
                characterToPlayer_HashMap.put(playerObj.getKeyboard_responses()[i], playerObj);
            }
        }
    }

    /**
     * This method updates the score JLabel in the bottom panel for every player.
     *
     * @param playersArr A Array containing Players objects.
     */
    public void updatePlayersPoints(Player[] playersArr) {
        for (Player item : playersArr) {
            JLabel label_p = playerToJLabel_HashMap.get(item);
            String temp = label_p.getText().substring(0, label_p.getText().indexOf(':') + 1);
            temp += item.getPoints();
            label_p.setText(temp);
        }

    }

    /**
     * This method draws the the question,responses and category of question on the GUI.
     * Also it resets the Player color to default.
     * @param questionObj The question object
     * @return responsesObj the player's responses
     */
    public Responses showQuestionAndGetResponses(Question questionObj) {
        //Change All players color to default
        for (Player item: playersArr)
            changePlayerStatusToNormal(item);
        imageLabel.setVisible(false);
        if (Questions.isQuestionImage(questionObj)){
            System.out.println("It is a question image");
            loadImage(((QuestionImage)questionObj).getImageName());
            imageLabel.setVisible(true);
        }
        this.responsesObj.clearReset();
        txtQuestionName.setText(questionObj.getQuestion());
        txtTypeQuestion.setText(questionObj.getType());
        ArrayList<String> respArr = new ArrayList<>(questionObj.getResponses());
        Collections.shuffle(respArr);
        for (int i = 0; i < txtRes.length; i++) {
            txtRes[i].setText(respArr.get(i));
        }
        while (!responsesObj.haveAllPlayersResponed()) {
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return responsesObj;

    }
    public String showMenu(String[] question_types,String old_question_type){
        String current_question_type = old_question_type;
        int resp = popupInput("Menu",new String[]{"Start Round","Change Question Category","Help"});
        while (resp != 0){
            if (resp==1)
                current_question_type = question_types[popupInput("Choose Question Category",question_types)];
            else if (resp == 2)
                JOptionPane.showMessageDialog(null,"Welcome to Buzz Quiz World!\n" +
                                "Choose a number of players and answer questions with your corresponding keys!\n" +
                                "At the start of each round, you will be able to change the question category, which by default is random.\n" +
                                "There are 5 types of rounds selected at random. Answer correctly and win!\n" +
                                "Right Answer gives 1000 points for a right answer and 0 to a wrong one.\n" +
                                "Bet allows you to bet points and gain double the amount if answered correctly.\n" +
                                "Quick Answer gives double points to the faster player to answer correctly.\n" +
                                "Stop The Counter gives as many points to the players who answer correctly as the time remaining on the counter.\n" +
                                "Thermometer does some shit.\n" +
                                "The highest scores will be recorded in a leaderboard. Have fun!",
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            resp = popupInput("Menu",new String[]{"Start Round","Change Question Category","Help"});

        }
        return current_question_type;
    }
}
