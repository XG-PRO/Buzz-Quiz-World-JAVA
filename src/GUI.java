import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GUI {
    private final int numberOfResponses = 4;
    private final Color colorGrayBackground;
    //Panels
    JFrame frame;
    JPanel mainPanel;
    JPanel questionPanel;
    JPanel typePanel;
    JPanel responsesPanel;
    JPanel centerPanel;
    JPanel belowQsPanel;
    JLabel imagePlaceHolder;
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
        colorGrayBackground = new Color(61, 72, 85);
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

        font_global = new Font("Arial Black", Font.BOLD, 26);
        font_global_20 = new Font("Arial Black", Font.PLAIN, 20);

        initMenu();

        initTypePanel();

        initQuestionPanel();


        initBelowQsPanel();
        initResponsesPanelAndImage();
        belowQsPanel.add(responsesPanel);
        belowQsPanel.add(Box.createHorizontalGlue());
        belowQsPanel.add(imagePlaceHolder);
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


        txtRoundType = new JLabel("Stop the Timer");
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
        // Init the KEY Responses Array
        txtResKeys = new JLabel[numberOfResponses];
        txtRes = new JLabel[numberOfResponses];
        for (int i = 0; i < numberOfResponses; i++) {
            txtResKeys[i] = new JLabel("P H");
            txtRes[i] = new JLabel("Etiam orci felis, bibendum ac congue a metus.");
        }
        // Init the questionPanel
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBackground(Color.white);
        questionPanel.setOpaque(true);

        txtTypeQuestion = new JLabel("Tech");
        txtTypeQuestion.setFont(font_global);
        txtTypeQuestion.setForeground(new Color(15, 186, 247));
        txtQuestionName = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut et libero in lorem pulvinar aliquam sit.");
        txtQuestionName.setFont(font_global);
        txtQuestionName.setOpaque(true);
        txtQuestionName.setLineWrap(true);
        txtQuestionName.setWrapStyleWord(true);
        //txtQuestionName.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.78), 110));
        txtQuestionName.setEnabled(false);
        txtQuestionName.setDisabledTextColor(colorGrayBackground);
        Dimension questionPanelDimension = new Dimension((int) (frame.getWidth() * 0.80), 110);
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
            myPicture = ImageIO.read(getClass().getResource("/splash/placeholder.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        belowQsPanel.setOpaque(false);
        imagePlaceHolder = new JLabel(new ImageIcon(myPicture));
        imagePlaceHolder.setOpaque(true);
        imagePlaceHolder.setPreferredSize(new Dimension(350, 350));
    }

    /**
     * This private method initializes the JPanel "scorePanel", and JLabel "txtRoundCount"
     */
    private void initScorePanelAndRoundCount() {
        scorePanel = new JPanel();
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        txtRoundCount = new JLabel("ROUND 1");
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
        int n = -1;
        JPanel insidePanel = new JPanel(new GridLayout(responses.length,1));
        for (int i = 0; i<responses.length;i++)
            insidePanel.add(new JLabel(responses[i]));
        while (n == -1) { // While the use closes the popup ask again and again and again....
            n = JOptionPane.showOptionDialog(frame,
                    question,
                    null,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    responses,
                    null);
        }
        return n;
    }

    /**
     * Ask from the user the number of players, and returns the result.
     *
     * @return Returns the number of player
     */
    public int popupAskNumberOfPlayer() {
        numberOfPlayers = popupInput("Number of players?", new String[]{"1 Player", "2 Players"}) + 1;
        return numberOfPlayers;
    }

    public String popupGetPlayerName(int i) {
        String temp = "";
        while (temp == "" || temp == null || temp.length()<1)
            temp =JOptionPane.showInputDialog("Enter Name of player " + i + " : ");

        return temp;

    }


    public void popupShowGainedPoints(Player[] player_arr, int[] gainedPoints)
    {
        String temp = "";
        for (int i = 0; i < player_arr.length; i++) {
            if (gainedPoints[i] >= 0)

                 temp+=player_arr[i].getName() + " gained : " + gainedPoints[i] + " points \n";

            else

                 temp+=player_arr[i].getName() + " lost : " + gainedPoints[i]*(-1) + " points \n";
        }

        JOptionPane.showMessageDialog(null, temp,
                "Results", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * This method adds the players in the bottom panel.
     * Also this method adds the respond keys.
     *
     * @param playersArr A Array containing Player objects.
     */
    public void drawPlayersInfoToGUI(Player[] playersArr) {
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

    public void showQuestionAndGetResponses(Question questionObj, Player[] playersArr, Responses responsesObj) {

        responsesObj.setIgnoreInput(false);
        this.playersArr = playersArr;
        this.responsesObj = responsesObj;
        txtQuestionName.setText(questionObj.getQuestion());
        txtTypeQuestion.setText(questionObj.getType());
        ArrayList<String> respArr = new ArrayList<>(questionObj.getResponses());
        Collections.shuffle(respArr);
        for (int i = 0; i < txtRes.length; i++) {
            txtRes[i].setText(respArr.get(i));
        }
    }

    /**
     * It changes the round counter (bottom - left) JLabel
     *
     * @param roundCount The round type
     */
    public void changeRoundCount(int roundCount) {
        String temp = txtRoundCount.getText().substring(0, 5);
        txtRoundCount.setText(temp + " " + roundCount);
    }

    public void changeRoundType(String roundType) {
        txtRoundType.setText(roundType);
    }


}






