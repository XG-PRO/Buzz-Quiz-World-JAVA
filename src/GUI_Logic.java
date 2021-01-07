import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class GUI_Logic extends GUI{
    GUI_Logic(ArrayList<String> categoriesOfQuestions){
        super(categoriesOfQuestions);
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
     * This method shows the question, the responses and the category of questions on the GUI.
     * Also it resets the Player color to default.
     * @param questionObj The question object
     * @return responsesObj the player's responses
     */
    public Responses showQuestionAndGetResponses(Question questionObj,boolean isTimer) {
        // Change All players color to default
        for (Player item: playersArr)
            changePlayerStatusToNormal(item);
        // Change all responses color to white
        for (JLabel item: txtRes)
            item.setForeground(Color.white);

        timerLabel.setVisible(false);
        imageLabel.setVisible(false);

        //If Question Type is QuestionImage the show the image
        if (Questions.isQuestionImage(questionObj)){
            loadImage(((QuestionImage)questionObj).getImageName());
            imageLabel.setVisible(true);
        }

        this.responsesObj.clearReset(); // Reset the responsesObj

        txtQuestionName.setText(questionObj.getQuestion()); // Sets the question
        txtTypeQuestion.setText(questionObj.getType()); // Sets the question category.

        //Creates a copy of the responses and randomizes them.
        ArrayList<String> respArr = new ArrayList<>(questionObj.getResponses());
        Collections.shuffle(respArr);

        // Put the responses to each JLabel Response
        for (int i = 0; i < txtRes.length; i++) {
            txtRes[i].setText(respArr.get(i));
        }

        //If the round type is stop the timer
        if (isTimer){

            timerLabel.setVisible(true);    // Shows the timer JLabel
            actionPerformed();  // Start the timer

            // While (all players have not  respond) and timer is still running don't return the responses yet
            while (responsesObj.atLeastOnePlayerHaveNOTRespond() && timer.isRunning()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100); // We had to add this for some reason.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            timer.stop();
        }

        //If the round type is NOT stop the timer
        else{
            // While (all players have not  respond) don't return the responses yet
            while (responsesObj.atLeastOnePlayerHaveNOTRespond()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(250); // We had to add this for some reason.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return responsesObj;
    }



    /**
     * Starts a timer of 5000 ms and decreases it by 100 ms every 100 ms
     */
    private void actionPerformed() {
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
