import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    private JLabel txtTypeQs;
    private JLabel txtTypeRound;
    private JTextArea txtQuestionName;
    private JLabel[] txtResKeys;
    private JLabel[] txtRes;
    private HashMap<Player, JLabel> playersPointsHash;
    private Font font_global;
    private Font font_global_20;
    private int numberOfPlayers;

    private final int numberOfResponses = 4;
    public GUI() {


        font_global = new Font("Arial Black", Font.BOLD, 26);
        font_global_20 = new Font("Arial Black", Font.PLAIN, 20);
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        // Init the KEY Responses Array
        txtResKeys = new JLabel[numberOfResponses];
        txtRes = new JLabel[numberOfResponses];
        for (int i = 0; i < numberOfResponses; i++) {
            txtResKeys[i] = new JLabel("P H");
            txtRes[i] = new JLabel("Donec et urna in arcu ultricies");
        }


        // Make the frame
        frame = new JFrame();
        frame.setTitle("Buzz Quiz World 2020");
        frame.setSize(1250, 640);
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

        txtTypeQs = new JLabel("Stop the Timer");
        txtTypeQs.setFont(font_global);
        txtTypeQs.setForeground(Color.white);
        txtTypeRound = new JLabel("Technology");
        txtTypeRound.setForeground(Color.white);
        txtTypeRound.setFont(font_global);

        typePanel.add(Box.createRigidArea(new Dimension(100, 0)));
        typePanel.add(Box.createHorizontalGlue());
        typePanel.add(txtTypeRound);
        typePanel.add(Box.createHorizontalGlue());
        typePanel.add(txtTypeQs);
        typePanel.add(Box.createRigidArea(new Dimension((int) (frame.getWidth() * 0.15), 0)));


        scorePanel = new JPanel();
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        JLabel txt_round_count = new JLabel("ROUND 1");
        txt_round_count.setForeground(Color.WHITE);
        txt_round_count.setFont(font_global);
        scorePanel.add(txt_round_count);
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
                if (evt.getKeyChar() == 'A') {
                    JOptionPane.showMessageDialog(null, "Just a message");
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

    public int popupAskNumberOfPlayer() {
        numberOfPlayers = popupInput("Number of players?", new String[]{"1 Player", "2 Players"}) + 1;
        return numberOfPlayers;

    }

    public void drawPlayersInfoToGUI(Player[] playersArr) {
        playersPointsHash = new HashMap<>(playersArr.length);
        for (int i = 0; i < playersArr.length; i++) {
            playersPointsHash.put(playersArr[i], new JLabel(playersArr[i].getName() + ":" + playersArr[i].getPoints()));
            playersPointsHash.get(playersArr[i]).setFont(font_global);
            playersPointsHash.get(playersArr[i]).setForeground(Color.WHITE);
            scorePanel.add(playersPointsHash.get(playersArr[i]));
            scorePanel.revalidate();
            scorePanel.add(Box.createHorizontalGlue());
            scorePanel.revalidate();
            System.out.println("i :" + i + " name : " + playersArr[i].getName());
        }

        for (int i = 0; i < numberOfResponses; i++) {
            String temp = "";
            for (int j = 0; j < numberOfPlayers; j++) {
                temp += playersArr[j].getKeyboard_responses()[i] + " ";
            }
            txtResKeys[i].setText(temp);
        }

    }
}



