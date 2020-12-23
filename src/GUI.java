import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

public class GUI {
    //Panels
    private JPanel mainPanel;
    private JPanel questionPanel;
    private JPanel typePanel;
    private JPanel responesPanel;
    private JPanel centerPanel;
    private JPanel below_qs_panel;
    private JPanel scorepanel;


    private JLabel txt_type_qs;
    private JLabel txt_type_round;
    private JTextArea txt_question_name;

    private JLabel[] txt_res_keys;
    private JLabel[] txt_res;

    private JLabel[] txt_players_points;


    //private ImageIcon icon;

    private Font font_global;

    public GUI() {


        ImageIcon icon;
        String iconPath = "splash/button.png";
        URL imageURL = getClass().getResource(iconPath);
        icon = new ImageIcon(imageURL);
        txt_players_points = new JLabel[2];
        txt_players_points[0] = new JLabel("PL1 100");
        txt_players_points[1] = new JLabel("PL2 200");

        txt_res_keys = new JLabel[4];
        txt_res_keys[0] = new JLabel("Q 1");

        txt_res_keys[1] = new JLabel("W 2");
        txt_res_keys[2] = new JLabel("E 3");
        txt_res_keys[3] = new JLabel("R 4");

        txt_res = new JLabel[4];
        txt_res[0] = new JLabel("Response 1");

        txt_res[1] = new JLabel("Response 2");
        txt_res[2] = new JLabel("Response 3");
        txt_res[3] = new JLabel("Response 4");


        font_global = new Font("Arial Black", Font.BOLD, 22);

        JFrame frame = new JFrame();
        frame.setTitle("Buzz Quiz World 2020");
        frame.setSize(800, 490);
        frame.setResizable(false);
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
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        mainPanel = new JPanel(new BorderLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        typePanel = new JPanel();
        typePanel.setBackground(Color.BLACK);
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.LINE_AXIS));
        questionPanel = new JPanel();
        questionPanel.setBackground(Color.white);
        questionPanel.setOpaque(true);


        below_qs_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        responesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        responesPanel.setBackground(Color.LIGHT_GRAY);
        //responesPanel.setLayout(new GridLayout(4,1));
        //responesPanel.setLayout(new BoxLayout(responesPanel,BoxLayout.Y_AXIS));

        //responesPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        String questionString = "Which planet is covered in thick poisonous clouds, trapping all the heat it absorbs from the Sun?";
        //questionString = questionString.substring(0,53) + "-" + questionString.substring(53);
        txt_question_name = new JTextArea(questionString);
        txt_question_name.setFont(font_global);

        txt_question_name.setOpaque(true);
        txt_question_name.setLineWrap(true);
        txt_question_name.setWrapStyleWord(true);
        txt_question_name.setPreferredSize(new Dimension(700, 90));
        txt_question_name.setEnabled(false);
        txt_question_name.setDisabledTextColor(Color.BLACK);
        questionPanel.setMaximumSize(new Dimension(800, 100));

        txt_type_qs = new JLabel("Stop the Timer");
        txt_type_qs.setFont(font_global);
        txt_type_qs.setForeground(Color.white);
        txt_type_round = new JLabel("Technology");
        txt_type_round.setForeground(Color.white);
        txt_type_round.setFont(font_global);

        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));
        typePanel.add(txt_type_round);
        typePanel.add(Box.createHorizontalGlue());
        typePanel.add(txt_type_qs);
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));


        scorepanel = new JPanel();
        scorepanel.setBackground(Color.BLACK);
        scorepanel.setLayout(new BoxLayout(scorepanel, BoxLayout.X_AXIS));
        scorepanel.add(Box.createRigidArea(new Dimension(40, 0)));
        JLabel txt_round_count = new JLabel("ROUND 1");
        txt_round_count.setForeground(Color.WHITE);
        txt_round_count.setFont(font_global);
        scorepanel.add(txt_round_count);
        scorepanel.add(Box.createHorizontalGlue());

        for (int i = 0; i < 2; i++) {
            txt_players_points[i].setFont(font_global);
            txt_players_points[i].setForeground(Color.WHITE);
            scorepanel.add(txt_players_points[i]);
            scorepanel.add(Box.createHorizontalGlue());
        }
        typePanel.add(Box.createRigidArea(new Dimension(40, 0)));


        questionPanel.add(txt_question_name);

        c.ipadx = 50;
        for (int i = 0; i < 4; i++) {
            c.gridx = 0;
            c.gridy = i;
            c.fill = GridBagConstraints.HORIZONTAL;
            txt_res_keys[i].setFont(font_global);

            txt_res_keys[i].setHorizontalAlignment(JLabel.CENTER);
            responesPanel.add(txt_res_keys[i], c);

            c.gridx += 1;
            txt_res[i].setFont(font_global);
            txt_res[i].setHorizontalAlignment(JLabel.LEFT);
            responesPanel.add(txt_res[i], c);
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

        centerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        //centerPanel.add(Box.createVerticalGlue());

        centerPanel.add(questionPanel);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(Box.createGlue());

        below_qs_panel.add(responesPanel);
        below_qs_panel.add(new JButton("Image Placeholder"));

        centerPanel.add(below_qs_panel);
        centerPanel.add(Box.createHorizontalGlue());

        //centerPanel.add(Box.createVerticalGlue());

        centerPanel.add(Box.createRigidArea(new Dimension(40, 100)));
        mainPanel.add(typePanel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(scorepanel, BorderLayout.PAGE_END);

        frame.add(mainPanel);
        //frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        GUI frame = new GUI();
    }
}



