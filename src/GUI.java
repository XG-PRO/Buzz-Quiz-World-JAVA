import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {
    //Panels
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
    private JLabel[] txtPlayersPoints;

    //private ImageIcon icon;

    private Font font_global;

    public GUI() {

        font_global = new Font("Comic Sans", Font.BOLD, 24);
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        txtPlayersPoints = new JLabel[2];
        txtPlayersPoints[0] = new JLabel("PL1: 100");
        txtPlayersPoints[1] = new JLabel("PL2: 200");

        // Init the KEY Responses Array
        txtResKeys = new JLabel[4];
        txtResKeys[0] = new JLabel("Q 1");
        txtResKeys[1] = new JLabel("W 2");
        txtResKeys[2] = new JLabel("E 3");
        txtResKeys[3] = new JLabel("R 4");
        // Init the Responses Array
        txtRes = new JLabel[4];
        txtRes[0] = new JLabel("Response 1");
        txtRes[1] = new JLabel("Response 2");
        txtRes[2] = new JLabel("Response 3");
        txtRes[3] = new JLabel("Response 4");


        // Make the frame
        JFrame frame = new JFrame();
        frame.setTitle("Buzz Quiz World 2020");
        frame.setSize(900, 600);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        JPanel mainPanel = new JPanel();
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
        String iconPath = "splash/icon.png";


        URL imageURL = getClass().getResource(iconPath);
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }

        String splashPath = "splash/splash.jpg";
        imageURL = getClass().getResource(splashPath);
        if (imageURL != null) {
            picture = new JLabel();
            picture.setIcon(new ImageIcon(imageURL));
        }

        message = new JLabel("Welcome to Buzz Quiz World",SwingConstants.CENTER);
        message.setFont(new Font("Verdana", Font.PLAIN, 26));
        message.setForeground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        imgPanel.add(message);
        imgPanel.add(picture);
        frame.setIconImage(icon.getImage());

        JPanel buttonsPanel = new JPanel(new FlowLayout());

        buttonsPanel.add(new JButton("t1"));
        buttonsPanel.add(new JButton("t2"));
        mainPanel.add(imgPanel);
        mainPanel.add(buttonsPanel);
        frame.add(mainPanel);
        frame.pack();

        frame.setVisible(true);

         */

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
        belowQsPanel.setLayout(new BoxLayout(belowQsPanel,BoxLayout.X_AXIS));

        // Init the responsesPanel
        responsesPanel = new JPanel(new GridBagLayout());
        // Init grid bag for responsesPanel
        GridBagConstraints c = new GridBagConstraints();
        responsesPanel.setOpaque(false);

        txtQuestionName = new JTextArea("Which organ could grow back if you donated part of it?");
        txtQuestionName.setFont(font_global);
        txtQuestionName.setOpaque(true);
        txtQuestionName.setLineWrap(true);
        txtQuestionName.setWrapStyleWord(true);
        txtQuestionName.setPreferredSize(new Dimension((int) (frame.getWidth()*0.90), 110));
        txtQuestionName.setEnabled(false);
        txtQuestionName.setDisabledTextColor(new Color(61,72,85));
        questionPanel.setMinimumSize(new Dimension(frame.getWidth(), 110));
        questionPanel.setMaximumSize(new Dimension(frame.getWidth(), 110));
        questionPanel.setPreferredSize(new Dimension(frame.getWidth(), 110));
        questionPanel.add(txtQuestionName);

        txtTypeQs = new JLabel("Stop the Timer");
        txtTypeQs.setFont(font_global);
        txtTypeQs.setForeground(Color.white);
        txtTypeRound = new JLabel("Technology");
        txtTypeRound.setForeground(Color.white);
        txtTypeRound.setFont(font_global);

        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        typePanel.add(txtTypeRound);
        typePanel.add(Box.createHorizontalGlue());
        typePanel.add(txtTypeQs);
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));

        scorePanel = new JPanel();
        scorePanel.setBackground(Color.BLACK);
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        JLabel txt_round_count = new JLabel("ROUND 1");
        txt_round_count.setForeground(Color.WHITE);
        txt_round_count.setFont(font_global);
        scorePanel.add(txt_round_count);
        scorePanel.add(Box.createHorizontalGlue());

        for (int i = 0; i < 2; i++) {
            txtPlayersPoints[i].setFont(font_global);
            txtPlayersPoints[i].setForeground(Color.WHITE);
            scorePanel.add(txtPlayersPoints[i]);
            scorePanel.add(Box.createHorizontalGlue());
        }
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));




        c.ipadx = 50;
        for (int i = 0; i < 4; i++) {
            c.gridx = 0;
            c.gridy = i;
            c.fill = GridBagConstraints.HORIZONTAL;
            txtResKeys[i].setFont(font_global);
            txtResKeys[i].setForeground(Color.WHITE);
            txtResKeys[i].setHorizontalAlignment(JLabel.CENTER);
            responsesPanel.add(txtResKeys[i], c);

            c.gridx += 1;
            txtRes[i].setFont(font_global);
            txtRes[i].setForeground(Color.WHITE);
            txtRes[i].setHorizontalAlignment(JLabel.LEFT);
            responsesPanel.add(txtRes[i], c);
        }
        /*
        txt_res_key_1 = new JLabel("   Q    1");
        txt_res_key_1.setFont(font_global);
        txt_res_key_1.setHorizontalAlignment(JLabel.CENTER);
        txt_res_key_1.setVerticalAlignment(JLabel.CENTER);
        c.fill = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        responesPanel.add(txt_res_key_1,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        txt_res_1 = new JButton("Checkers");
        txt_res_1.setFont(font_global);
        txt_res_1.setHorizontalAlignment(JLabel.CENTER);
        txt_res_1.setVerticalAlignment(JLabel.CENTER);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 0;
        responesPanel.add(txt_res_1,c);

        txt_res_key_2 = new JLabel("W    2");
        txt_res_key_2.setFont(font_global);
        txt_res_key_2.setHorizontalAlignment(JLabel.CENTER);
        txt_res_key_2.setVerticalAlignment(JLabel.CENTER);
        c.gridx = 0;
        c.gridy = 1;
        responesPanel.add(txt_res_key_2,c);

        txt_res_2 = new JButton("Chess");
        txt_res_2.setFont(font_global);
        txt_res_2.setHorizontalAlignment(JLabel.CENTER);
        txt_res_2.setVerticalAlignment(JLabel.CENTER);
        responesPanel.add(txt_res_2,c);

        txt_res_key_3 = new JLabel("E    3");
        txt_res_key_3.setFont(font_global);
        txt_res_key_3.setHorizontalAlignment(JLabel.CENTER);
        txt_res_key_3.setVerticalAlignment(JLabel.CENTER);
        responesPanel.add(txt_res_key_3,c);

        txt_res_3 = new JButton("Monopoly");
        txt_res_3.setFont(font_global);
        txt_res_3.setHorizontalAlignment(JLabel.CENTER);
        txt_res_3.setVerticalAlignment(JLabel.CENTER);
        responesPanel.add(txt_res_3,c);

        txt_res_key_4 = new JLabel("R    4");
        txt_res_key_4.setFont(font_global);
        txt_res_key_4.setHorizontalAlignment(JLabel.CENTER);
        txt_res_key_4.setVerticalAlignment(JLabel.CENTER);
        responesPanel.add(txt_res_key_4,c);

        txt_res_4 = new JButton("Snakes and Ladders");
        txt_res_4.setFont(font_global);
        txt_res_4.setHorizontalAlignment(JLabel.CENTER);
        txt_res_4.setVerticalAlignment(JLabel.CENTER);
        responesPanel.add(txt_res_4,c);



         */

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        //centerPanel.add(Box.createVerticalGlue());

        centerPanel.add(questionPanel);

        //centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        //centerPanel.add(Box.createGlue());

        belowQsPanel.add(responsesPanel);
        //below_qs_panel.add(Box.createGlue());
        BufferedImage myPicture = null;
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
        temp_image_placeholder.setPreferredSize(new Dimension(350,350));


        belowQsPanel.add(temp_image_placeholder);
        //below_qs_panel.add(Box.createGlue());
        belowQsPanel.add(Box.createHorizontalGlue());
        belowQsPanel.add(Box.createRigidArea(new Dimension(100, 50)));
        belowQsPanel.add(Box.createHorizontalGlue());

        centerPanel.add(belowQsPanel);
        centerPanel.setBackground(new Color(61,72,85));
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
                System.out.println("KEY PRESSES "+evt.getKeyChar());
                if (evt.getKeyChar() == 'A'){
                    JOptionPane.showMessageDialog(null, "Just a message");
                }
            }
        });
        //frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        GUI frame = new GUI();

    }
}



