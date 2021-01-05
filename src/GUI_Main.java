import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GUI_Main extends GUI{

    GUI_Main(ArrayList<String> categoriesOfQuestions) {
        super(categoriesOfQuestions);
    }

    /**
     * Shows a popup and closes the frame
     * @param k if k = 0, All Rounds have been completed.
     *          if k = 1, No more questions
     *          if k = -1, Error :(
     */
    public void exitFrame(int k){
        if (k==0)
            JOptionPane.showMessageDialog(frame,"All Rounds have been completed!\nThanks for playing!",
                    "The game has ended", JOptionPane.INFORMATION_MESSAGE);
        if (k==1)
            JOptionPane.showMessageDialog(frame,"Sadly there are no more questions in the directory. You answered them all?\nThanks for playing!",
                    "The game has ended", JOptionPane.INFORMATION_MESSAGE);
        if (k==-1)
            JOptionPane.showMessageDialog(frame,"An error has occurred",
                    "Error", JOptionPane.ERROR_MESSAGE);
        popupShowWinners();
        frame.dispose();
        System.exit(k);
    }
    public String getChosenCategory(){
        return group.getSelection().getActionCommand();
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

    public void popupShowGainedPoints(Player[] playerArr, HashMap<Player,Integer> gainedPointsHash, String correctAnswer,Responses responsesObj)
    {
        for (JLabel item: txtRes){
            if (item.getText().equals(correctAnswer))//Set the respones to green if is true
                item.setForeground(Color.green);
            else
                item.setForeground(Color.red);//Set the respones to red if is false
        }
        StringBuilder temp = new StringBuilder();
        temp.append("The correct answer was : ").append(correctAnswer).append(".\n\n\n");
        for (int i = 0; i < playerArr.length; i++) {
            Player currentPlayer = responsesObj.getPlayerAtPos(i);
            if (!gainedPointsHash.containsKey(currentPlayer)){
                temp.append(currentPlayer.getName()).append(" didn't respond.\n");
                continue;
            }
            if (responsesObj.getResponseAtPos(i).equals(correctAnswer))
                changePlayerStatusToTrue(currentPlayer);
            else
                changePlayerStatusToFalse(currentPlayer);

            if (gainedPointsHash.get(currentPlayer) == 0){
                temp.append(currentPlayer.getName()).append(" didn't get any points.\n");
            }
            else {
                if (gainedPointsHash.get(currentPlayer) >= 0){
                    temp.append(currentPlayer.getName()).append(" gained : ").append(gainedPointsHash.get(currentPlayer));
                }
                else {
                    temp.append(currentPlayer.getName()).append(" lost : ").append(gainedPointsHash.get(currentPlayer) * (-1));
                }
                temp.append(" points.\n");
            }
        }temp.append("\n\n");

        JOptionPane.showMessageDialog(this.frame, temp.toString(),
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
    public Responses showQuestionAndGetResponses(Question questionObj,boolean isTimer) {
        //Change All players color to default
        for (Player item: playersArr)
            changePlayerStatusToNormal(item);
        // Change all responses color to white
        for (JLabel item: txtRes)
            item.setForeground(Color.white);
        timerLabel.setVisible(false);
        imageLabel.setVisible(false);
        if (Questions.isQuestionImage(questionObj)){
            //System.out.println("It is a question image");
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
        if (isTimer){
            timerLabel.setVisible(true);
            actionPerformed();
        }
        while (!responsesObj.haveAllPlayersResponed()) {
            try {
                TimeUnit.MILLISECONDS.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (isTimer)
            timer.stop();
        return responsesObj;

    }
    public void popupShowWinners(){
        StringBuilder temp = new StringBuilder();
        int numberOfWinners = 0;
        for (Player item : playersArr){
            if (item.isHasWon()){

                temp.append(item.getName());
                temp.append(" won with ");
                temp.append(item.getPoints());
                temp.append(" points.");
                numberOfWinners++;
            }
            temp.append("\n");
            if (numberOfWinners>1)
                temp.append("It was a draw");
        }
        JOptionPane.showMessageDialog(frame,temp);
    }

    public void popupInfo(){
        JOptionPane.showMessageDialog(frame,"Welcome to Buzz Quiz World!\n" +
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
    }
    public void actionPerformed() {
        timerLabel.setText("5000");
        timer = new Timer(100, new ActionListener() {
            private int count = 5000;
            final boolean[] has_timer_stopped = {true};
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count <= 0) {
                    ((Timer) e.getSource()).stop();
                    has_timer_stopped[0] = true;
                } else {
                    count -= 100;
                    has_timer_stopped[0] = false;
                }
                timerLabel.setText(Integer.toString(count));

            }

        });
        timer.start();
    }

}
