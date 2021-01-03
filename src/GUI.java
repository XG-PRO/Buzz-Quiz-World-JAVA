import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class GUI {
    protected final int numberOfResponses = 4;
    protected final Color colorGrayBackground;
    //Panels
    protected final JFrame frame;
    private JPanel questionPanel;
    private JPanel typePanel;
    private JPanel responsesPanel;
    private JPanel belowQsPanel;

    protected JLabel imageLabel;
    private ImageIcon imageIcon;
    protected JLabel txtTypeQuestion;
    protected JLabel txtRoundType;

    protected JTextArea txtQuestionName;
    protected JLabel[] txtResKeys;
    protected JLabel[] txtRes;
    protected JLabel txtRoundCount;

    protected HashMap<Player, JLabel> playerToJLabel_HashMap; // Hash Map from Player to JLabel Points (bottom panel)
    protected HashMap<Character, JLabel> characterToJLabel_HashMap; // Hash Map from Character (Respond Key) to JLabel Question respond
    protected HashMap<Character, Player> characterToPlayer_HashMap; // Hash Map (Respond Key) to Player
    protected Font font_global;
    protected Font font_global_20;
    protected int numberOfPlayers;
    protected Player[] playersArr;
    protected Responses responsesObj;
    protected JPanel scorePanel;
    private JMenuBar menubar;




    /**
     * Default Constructor building the UI using JAVA SWING Library
     */
    public GUI() {
        //Disable scaling this
        //System.setProperty("sun.java2d.uiScale", "1.5");

        //Set OptionPane font to
        font_global = new Font("Arial Black", Font.BOLD, 26);
        font_global_20 = new Font("Arial Black", Font.PLAIN, 20);
        colorGrayBackground = new Color(61, 72, 85);

        Color colorForOptionPanel = new Color(18,26,40);
        UIManager.put("OptionPane.border",BorderFactory.createLineBorder(colorForOptionPanel,18));
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 16));
        UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 18));
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
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Init the centerPanel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Init the HashMap's
        characterToJLabel_HashMap = new HashMap<>(numberOfPlayers * numberOfResponses);
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
                    responsesObj.addPlayerResponse(pl, characterToJLabel_HashMap.get(key).getText());
                    changePlayerStatusToReplied(pl);
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
        JMenu menu = new JMenu();
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
     * @return n, which corresponds to the player's option
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
            JOptionPane.showMessageDialog(null, "You Have To Select A Option To Continue\n", "Error", JOptionPane.ERROR_MESSAGE);
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




    protected void changePlayerStatusToReplied(Player playerObj){
        playerToJLabel_HashMap.get(playerObj).setForeground(new Color(109, 116, 116));
    }
    protected void changePlayerStatusToNormal(Player playerObj){
        playerToJLabel_HashMap.get(playerObj).setForeground(Color.white);
    }
    protected void changePlayerStatusToFalse(Player playerObj){
        playerToJLabel_HashMap.get(playerObj).setForeground(Color.RED);
    }
    protected void changePlayerStatusToTrue(Player playerObj){
        playerToJLabel_HashMap.get(playerObj).setForeground(Color.GREEN);
    }
    protected void loadImage(String fileName){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(getClass().getResource("/packageQuestions/images/"+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.imageIcon.setImage(myPicture);
    }

}
