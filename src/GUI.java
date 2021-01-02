import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GUI {
    private final int numberOfResponses = 4;
    private final Color colorGrayBackground;
    //Panels
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel questionPanel;
    private JPanel typePanel;
    private JPanel responsesPanel;
    private JPanel centerPanel;
    private JPanel belowQsPanel;

    private JLabel imageLabel;
    private ImageIcon imageIcon;
    private JLabel txtTypeQuestion;
    private JLabel txtRoundType;

    private JTextArea txtQuestionName;
    private JLabel[] txtResKeys;
    private JLabel[] txtRes;
    private JLabel txtRoundCount;

    private HashMap<Player, JLabel> playerToJLabel_HashMap; // Hash Map from Player to JLabel Points (bottom panel)
    private HashMap<Character, JLabel> characterToJLable_HashMap; // Hash Map from Character (Respond Key) to JLabel Question respond
    private HashMap<Character, Player> characterToPlayer_HashMap; // Hash Map (Respond Key) to Player
    private Font font_global;
    private Font font_global_20;
    private int numberOfPlayers;
    private Player[] playersArr;
    private Responses responsesObj;
    private JPanel scorePanel;
    private JMenu menu;
    private JMenuBar menubar;




    /**
     * Default Constructor building the UI using JAVA SWING Library
     */
    public GUI() {
        //Set OptionPane font to
        font_global = new Font("Arial Black", Font.BOLD, 26);
        font_global_20 = new Font("Arial Black", Font.PLAIN, 20);
        colorGrayBackground = new Color(61, 72, 85);

        Color colorForOptionPanel = new Color(18,26,40);
        UIManager.put("OptionPane.messageFont", new Font("Arial Black", Font.BOLD, 24));
        UIManager.put("OptionPane.buttonFont", new Font("Arial Black", Font.PLAIN, 20));
        UIManager.put("TextField.font", new Font("Arial Black", Font.PLAIN, 20));
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.background", colorForOptionPanel);
        UIManager.put("OptionPane.messagebackground", colorForOptionPanel);
        UIManager.put("Panel.background", colorForOptionPanel);


        // Make the frame
        frame = new JFrame();
        frame.setTitle("Buzz Quiz World 2020");
        frame.setSize(1250, 640);
        frame.setMinimumSize(new Dimension(1214, 592));
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the icon to the window frame
        URL imageURL = getClass().getResource("splash/icon.png");
        if (imageURL != null)
            frame.setIconImage(new ImageIcon(imageURL).getImage());

        // Init the mainPanel
        mainPanel = new JPanel(new BorderLayout());
        // Init the centerPanel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Init the HashMap's
        characterToJLable_HashMap = new HashMap<>(numberOfPlayers * numberOfResponses);
        characterToPlayer_HashMap = new HashMap<>(numberOfPlayers * numberOfResponses);

        initMenu();

        initTypePanel();

        initQuestionPanel();


        initBelowQsPanel();
        initResponsesPanelAndImage();
        belowQsPanel.add(responsesPanel);
        belowQsPanel.add(Box.createHorizontalGlue());
        belowQsPanel.add(imageLabel);
        belowQsPanel.add(Box.createHorizontalGlue());
        belowQsPanel.add(Box.createRigidArea(new Dimension((int) (frame.getWidth() * 0.15), 1)));

        initScorePanelAndRoundCount();
        scorePanel.add(txtRoundCount);
        scorePanel.add(Box.createHorizontalGlue());


        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(questionPanel);


        centerPanel.add(belowQsPanel);
        centerPanel.setBackground(colorGrayBackground);
        centerPanel.add(Box.createHorizontalGlue());

        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(typePanel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(scorePanel, BorderLayout.PAGE_END);
        frame.setFocusable(true); //Make it focusable to be able the KeyListener to lister for key presses
        frame.add(mainPanel);

        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                //System.out.println("KEY PRESSED " + evt.getKeyChar());
                char key = Character.toUpperCase(evt.getKeyChar());
                if (responsesObj != null && characterToPlayer_HashMap.containsKey(key)) {
                    Player pl = characterToPlayer_HashMap.get(key);
                    responsesObj.addPlayerResponse(pl, characterToJLable_HashMap.get(key).getText());
                    changePlayerStatusToReplied(pl);
                    //    System.out.printf("The status of Responses is "+responsesObj.haveAllPlayersResponed());
                    //    System.out.printf("ID OF OBJECT : "+responsesObj.hashCode());
                }
            }
        });
        //frame.pack();

        frame.setVisible(true);
    }


    /**
     * This private method initializes the JMenu
     */
    private void initMenu() {

        menubar = new JMenuBar();
        menu = new JMenu();
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(getClass().getResource("/splash/settings-32.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu.setIcon(new ImageIcon(myPicture));
        JMenuItem menuItem = new JMenuItem("Change Number of Players");
        menuItem.setForeground(Color.RED);
        //menuItem.setFont(font_global);
        menubar.setBackground(Color.black);
        //menu.setFont(font_global);
        menu.setForeground(Color.white);
        menu.add(menuItem);
        menubar.add(menu);
    }


    /**
     * This private method initializes the JPanel "typePanel", the JLabel "txtRoundType"
     */
    private void initTypePanel() {
        //UIManager.put("MenuItem.font", font_global_20);


        txtRoundType = new JLabel();
        txtRoundType.setToolTipText("If you need any help go to MENU->HELP");
        txtRoundType.setForeground(Color.white);
        txtRoundType.setFont(font_global);

        typePanel = new JPanel();
        typePanel.setBackground(Color.BLACK);
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.LINE_AXIS));
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        typePanel.add(menubar);
        typePanel.add(Box.createGlue());
        typePanel.add(txtRoundType);
        typePanel.add(Box.createGlue());
        //typePanel.add(txtTypeQuestion);
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));
    }


    /**
     * This private method initializes the JPanel "questionPanel", the JLabel's "txtquestionName", "txtTypeQuestion"
     */
    private void initQuestionPanel() {
        // Init the questionPanel
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(new Color(245, 245, 245));
        questionPanel.setOpaque(true);

        txtTypeQuestion = new JLabel();
        txtTypeQuestion.setFont(font_global);
        txtTypeQuestion.setForeground(new Color(63, 124, 172));
        txtQuestionName = new JTextArea("Welcome to Buzz Quiz World");
        txtQuestionName.setFont(font_global);
        txtQuestionName.setOpaque(true);
        txtQuestionName.setLineWrap(true);
        txtQuestionName.setWrapStyleWord(true);
        txtQuestionName.setOpaque(false);
        //txtQuestionName.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.78), 110));
        txtQuestionName.setEnabled(false);
        txtQuestionName.setDisabledTextColor(new Color(26, 30, 35));
        Dimension questionPanelDimension = new Dimension((int) (frame.getWidth() * 0.80), 115);
        questionPanel.setMinimumSize(questionPanelDimension);
        questionPanel.setMaximumSize(questionPanelDimension);
        questionPanel.setPreferredSize(questionPanelDimension);
        questionPanel.add(txtQuestionName);
        questionPanel.add(txtTypeQuestion);
    }


    /**
     * This private method initializes the JPanel "belowQsPanel"
     */
    private void initBelowQsPanel() {
        belowQsPanel = new JPanel();
        belowQsPanel.setLayout(new BoxLayout(belowQsPanel, BoxLayout.X_AXIS));
    }

    /**
     * This private method initializes the JPanel "responsesPanel", and JLabel "imagePlaceholder"
     */
    private void initResponsesPanelAndImage() {
        // Init the KEY Responses Array
        txtResKeys = new JLabel[numberOfResponses];
        txtRes = new JLabel[numberOfResponses];
        for (int i = 0; i < numberOfResponses; i++) {
            txtResKeys[i] = new JLabel();
            txtRes[i] = new JLabel();
        }
        responsesPanel = new JPanel(new GridBagLayout());
        // Init grid bag for responsesPanel
        GridBagConstraints c = new GridBagConstraints();
        responsesPanel.setOpaque(false);

        c.ipadx = 50;
        for (int i = 0; i < 4; i++) {
            c.gridx = 0;
            c.gridy = i;
            c.fill = GridBagConstraints.HORIZONTAL;
            txtResKeys[i].setFont(font_global_20);
            txtResKeys[i].setForeground(new Color(157, 193, 189));
            txtResKeys[i].setHorizontalAlignment(JLabel.CENTER);
            responsesPanel.add(txtResKeys[i], c);

            c.gridx += 1;
            txtRes[i].setFont(font_global);
            txtRes[i].setForeground(Color.WHITE);
            txtRes[i].setPreferredSize(new Dimension(670, 80));
            txtRes[i].setHorizontalAlignment(JLabel.LEFT);
            responsesPanel.add(txtRes[i], c);
        }

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(getClass().getResource("/splash/splash.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        belowQsPanel.setOpaque(false);
        this.imageIcon = new ImageIcon(myPicture);
        imageLabel = new JLabel(this.imageIcon);
        imageLabel.setOpaque(true);
        imageLabel.setPreferredSize(new Dimension(350, 350));
    }

    /**
     * This private method initializes the JPanel "scorePanel", and JLabel "txtRoundCount"
     */
    private void initScorePanelAndRoundCount() {
        scorePanel = new JPanel();
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        txtRoundCount = new JLabel();
        txtRoundCount.setForeground(Color.WHITE);
        txtRoundCount.setFont(font_global);
    }


    /**
     * This method opens a PopUp windows and ask the user to choose from the response array.
     *
     * @param question  A String    .The Question that the use will have to answer.
     * @param responses A String[](Array) .The Available options (buttons0 )the user can press.
     * @return
     */
    public int popupInput(String question, String[] responses) {
        int n;
        /*
        JPanel insidePanel = new JPanel(new GridLayout(responses.length,1));
        for (int i = 0; i<responses.length;i++)
            insidePanel.add(new JButton(responses[i]));
         */
        n = JOptionPane.showOptionDialog(frame,
                question,
                null,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                responses,
                null);
        while (n == -1) { // While the use closes the popup ask again and again and again....
            JOptionPane.showMessageDialog(frame, "You Have To Select A Option To Continue\n", "Error", JOptionPane.ERROR_MESSAGE);
            n = JOptionPane.showOptionDialog(frame,
                    question,
                    null,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    responses,
                    null);
        }
        return n;
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
                characterToJLable_HashMap.put(playerObj.getKeyboard_responses()[i], txtRes[i]);
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
     * @return
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
        this.playersArr = playersArr;
        this.responsesObj = responsesObj;
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

    private void changePlayerStatusToReplied(Player playerObj){
        playerToJLabel_HashMap.get(playerObj).setForeground(new Color(109, 116, 116));
    }
    private void changePlayerStatusToNormal(Player playerObj){
        playerToJLabel_HashMap.get(playerObj).setForeground(Color.white);
    }
    private void loadImage(String fileName){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(getClass().getResource("/packageQuestions/images/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.imageIcon.setImage(myPicture);
    }

    public String showMenu(String[] question_types,String old_question_type){
        String current_question_type = old_question_type;
        int resp = popupInput("Menu",new String[]{"Start Round","Change Question Category","Help"});
        while (resp != 0){
            if (resp==1)
                current_question_type = question_types[popupInput("Choose question category",question_types)];
            else if (resp == 2)
                JOptionPane.showMessageDialog(null,"Info\nInfo in second line",
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            resp = popupInput("Menu",new String[]{"Change Question Category","Start Round","Help"});

        }
        return current_question_type;
    }
}
