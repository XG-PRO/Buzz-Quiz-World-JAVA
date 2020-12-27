import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GUI {
    private final int maxNumberOfPlayers = 2;
    //Panels
    JFrame frame;
    private JPanel mainPanel;
    private JPanel questionPanel;
    private JPanel typePanel;
    private JPanel responsesPanel;
    private JPanel centerPanel;
    private JPanel belowQsPanel;
    private JPanel scorePanel;


    private JLabel txtTypeQuestion;
    private JLabel txtRoundType;
    private JTextArea txtQuestionName;
    private JLabel[] txtResKeys;
    private JLabel[] txtRes;
    JLabel txtRoundCount;


    private HashMap<Player, JLabel> playerToJLabel_HashMap; // Hash Map from Player to JLabel Points (bottom panel)
    private HashMap<Character, JLabel> characterToJLable_HashMap; // Hash Map from Character (Respond Key) to JLabel Question respond
    private HashMap<Character, Player> characterToPlayer_HashMap; // Hash Map (Respond Key) to Player
    private Font font_global;
    private Font font_global_20;
    private int numberOfPlayers;


    private Player[] playersArr;
    private Responses responsesObj;


    private final int numberOfResponses = 4;
    private boolean atLeastOneInput;
    /**
     * Default Constactor building the UI using JAVA SWING Library
     */
    public GUI() {

        characterToJLable_HashMap = new HashMap<>(numberOfPlayers*numberOfResponses);
        characterToPlayer_HashMap = new HashMap<>(numberOfPlayers*numberOfResponses);
        font_global = new Font("Arial Black", Font.BOLD, 26);
        font_global_20 = new Font("Arial Black", Font.PLAIN, 20);
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        // Init the KEY Responses Array
        txtResKeys = new JLabel[numberOfResponses];
        txtRes = new JLabel[numberOfResponses];
        for (int i = 0; i < numberOfResponses; i++) {
            txtResKeys[i] = new JLabel("P H");
            txtRes[i] = new JLabel("Etiam orci felis, bibendum ac congue a metus.");
        }


        // Make the frame
        frame = new JFrame();
        frame.setTitle("Buzz Quiz World 2020");
        frame.setSize(1250, 640);
        frame.setMinimumSize(new Dimension(1214, 592));
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL imageURL = getClass().getResource("splash/icon.png");
        if (imageURL != null)
            frame.setIconImage(new ImageIcon(imageURL).getImage());


        // Init the mainPanel
        mainPanel = new JPanel(new BorderLayout());
        // Init the centerPanel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        typePanel = new JPanel();
        typePanel.setBackground(Color.BLACK);
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.LINE_AXIS));
        questionPanel = new JPanel();
        questionPanel.setBackground(Color.white);
        questionPanel.setOpaque(true);

        // Init the below_qs_panel
        belowQsPanel = new JPanel();
        belowQsPanel.setLayout(new BoxLayout(belowQsPanel, BoxLayout.X_AXIS));

        // Init the responsesPanel
        responsesPanel = new JPanel(new GridBagLayout());
        // Init grid bag for responsesPanel
        GridBagConstraints c = new GridBagConstraints();
        responsesPanel.setOpaque(false);

        //UIManager.put("MenuItem.font", font_global_20);
        JMenu menu;
        JMenuBar menubar;
        BufferedImage myPicture = null;
        try {
            File f = new File(getClass().getResource("splash/settings-32.png").getFile());
            myPicture = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        menubar = new JMenuBar();
        menu = new JMenu();
        menu.setIcon(new ImageIcon(myPicture));
        JMenuItem menuItem = new JMenuItem("Change Number of Players");
        menuItem.setForeground(Color.RED);
        //menuItem.setFont(font_global);
        menubar.setBackground(Color.black);
        //menu.setFont(font_global);
        menu.setForeground(Color.white);
        menu.add(menuItem);
        menubar.add(menu);
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        typePanel.add(menubar);


        txtQuestionName = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut et libero in lorem pulvinar aliquam sit.");
        txtQuestionName.setFont(font_global);
        txtQuestionName.setOpaque(true);
        txtQuestionName.setLineWrap(true);
        txtQuestionName.setWrapStyleWord(true);
        txtQuestionName.setPreferredSize(new Dimension((int) (frame.getWidth() * 0.78), 110));
        txtQuestionName.setEnabled(false);
        txtQuestionName.setDisabledTextColor(new Color(61, 72, 85));
        Dimension questionPanelDimension = new Dimension((int) (frame.getWidth() * 0.80), 110);
        questionPanel.setMinimumSize(questionPanelDimension);
        questionPanel.setMaximumSize(questionPanelDimension);
        questionPanel.setPreferredSize(questionPanelDimension);
        questionPanel.add(txtQuestionName);

        txtTypeQuestion = new JLabel("Stop the Timer");
        txtTypeQuestion.setFont(font_global);
        txtTypeQuestion.setForeground(Color.white);
        txtRoundType = new JLabel("Technology");
        txtRoundType.setForeground(Color.white);
        txtRoundType.setFont(font_global);

        //typePanel.add(Box.createRigidArea(new Dimension(100, 0)));
        typePanel.add(Box.createGlue());
        typePanel.add(txtRoundType);
        typePanel.add(Box.createGlue());
        typePanel.add(txtTypeQuestion);
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));


        scorePanel = new JPanel();
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        txtRoundCount = new JLabel("ROUND 1");
        txtRoundCount.setForeground(Color.WHITE);
        txtRoundCount.setFont(font_global);
        scorePanel.add(txtRoundCount);
        scorePanel.add(Box.createHorizontalGlue());

/*
        txtPlayersPoints = new JLabel[maxNumberOfPlayers];
        for (int i = 0; i < maxNumberOfPlayers; i++) {
            txtPlayersPoints[i] = new JLabel("PL"+(i+1)+": 0");
            txtPlayersPoints[i].setFont(font_global);
            txtPlayersPoints[i].setForeground(Color.WHITE);
            scorePanel.add(txtPlayersPoints[i]);
            scorePanel.add(Box.createHorizontalGlue());
            txtPlayersPoints[i].setVisible(false);
        }



 */
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
            txtRes[i].setPreferredSize(new Dimension(670,80));
            txtRes[i].setHorizontalAlignment(JLabel.LEFT);
            responsesPanel.add(txtRes[i], c);
        }
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        //centerPanel.add(Box.createVerticalGlue());

        centerPanel.add(questionPanel);

        //centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        //centerPanel.add(Box.createGlue());

        belowQsPanel.add(responsesPanel);
        //below_qs_panel.add(Box.createGlue());
        myPicture = null;
        try {
            File f = new File(getClass().getResource("splash/placeholder.png").getFile());
            myPicture = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //below_qs_panel.setBackground(Color.BLACK);
        belowQsPanel.setOpaque(false);
        JLabel temp_image_placeholder = new JLabel(new ImageIcon(myPicture));
        temp_image_placeholder.setOpaque(true);
        //temp_image_placeholder.setMinimumSize(new Dimension(350,350));
        //temp_image_placeholder.setMaximumSize(new Dimension(350,350));
        temp_image_placeholder.setPreferredSize(new Dimension(350, 350));

        belowQsPanel.add(Box.createHorizontalGlue());
        belowQsPanel.add(temp_image_placeholder);
        //below_qs_panel.add(Box.createGlue());
        belowQsPanel.add(Box.createHorizontalGlue());

        belowQsPanel.add(Box.createRigidArea(new Dimension((int) (frame.getWidth() * 0.15), 1)));
        //belowQsPanel.add(Box.createHorizontalGlue());

        centerPanel.add(belowQsPanel);
        centerPanel.setBackground(new Color(61, 72, 85));
        centerPanel.add(Box.createHorizontalGlue());

        //centerPanel.add(Box.createVerticalGlue());

        //centerPanel.add(Box.createRigidArea(new Dimension(40, 100)));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(typePanel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(scorePanel, BorderLayout.PAGE_END);
        frame.setFocusable(true); //Make it focusable to be able the KeyListener to lister for key presses
        frame.add(mainPanel);

        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                System.out.println("KEY PRESSES " + evt.getKeyChar());
                char key = Character.toUpperCase(evt.getKeyChar());
                if (responsesObj!=null && characterToPlayer_HashMap.containsKey(key)){
                    Player pl = characterToPlayer_HashMap.get(key);
                    responsesObj.addPlayerResponse(pl,characterToJLable_HashMap.get(key).getText());
                }

            }
        });
        //frame.pack();

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.popupAskNumberOfPlayer();
    }

    /**
     * This method opens a PopUp windows and ask the user to choose from the response array.
     * @param question A String    .The Question that the use will have to answer.
     * @param responses A String[](Array) .The Available options (buttons0 )the user can press.
     * @return
     */
    public int popupInput(String question, String[] responses) {
        int n = -1;
        while (n == -1) { // While the use closes the popup ask again and again and again....
            n = JOptionPane.showOptionDialog(frame,
                    question,
                    question,
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
     * @return Returns the number of player
     */
    public int popupAskNumberOfPlayer() {
        numberOfPlayers = popupInput("Number of players?", new String[]{"1 Player", "2 Players"}) + 1;
        return numberOfPlayers;

    }

    /**
     * This method adds the players in the bottom panel.
     * Also this method adds the respond keys.
     * @param playersArr A Array containing Player objects.
     */
    public void drawPlayersInfoToGUI(Player[] playersArr) {
        playerToJLabel_HashMap = new HashMap<>(playersArr.length);
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
            for (Player playerObj:playersArr){
                characterToJLable_HashMap.put(playerObj.getKeyboard_responses()[i], txtRes[i]);
                characterToPlayer_HashMap.put(playerObj.getKeyboard_responses()[i],playerObj);
            }
        }
        System.out.println();
        /*
        for (Character key: resKeysHash.keySet()){
            System.out.println(key+" : "+resKeysHash.get(key).getText());
        }
         */
    }

    /**
     * This method updates the score JLabel in the bottom panel for every player.
     * @param playersArr A Array containing Players objects.
     */
    public void updatePlayersPoints(Player[] playersArr){
        for (Player item: playersArr){
            JLabel label_p = playerToJLabel_HashMap.get(item);
            String temp = label_p.getText().substring(0,label_p.getText().indexOf(':'));
            temp+= item.getPoints();
            label_p.setText(temp);
        }

    }

    public void showQuestionAndGetResponses(Question questionObj, Player[] playersArr, Responses responsesObj){
        atLeastOneInput = false;
        responsesObj.setIgnoreInput(false);
        this.playersArr = playersArr;
        this.responsesObj = responsesObj;
        txtQuestionName.setText(questionObj.getQuestion());
        txtTypeQuestion.setText(questionObj.getType());
        ArrayList<String> respArr = new ArrayList<>(questionObj.getResponses());
        Collections.shuffle(respArr);
        for (int i = 0; i<txtRes.length; i++){
            txtRes[i].setText(respArr.get(i));
        }
        while (!responsesObj.haveAllPlayersResponed()){}
        //responsesObj.setIgnoreInput(true);
        respArr.clear();
        atLeastOneInput = false;
    }

    /**
     * It changes the
     * @param roundCount
     */
    public void changeRoundCount(int roundCount){
        String temp = txtRoundCount.getText().substring(0,4);
        txtRoundCount.setText(temp+" "+roundCount);
    }

    public void changeRoundType(String roundType){
        txtRoundType.setText(roundType);
    }
}






