import javax.swing.*;
import java.awt.*;

public class GUI {
    //Panels
    private JPanel mainPanel;
    private JPanel textPanel;
    private JPanel typePanel;
    private JPanel responesPanel;
    private JPanel centerPanel;
    private JLabel txt_type_qs;
    private JLabel txt_type_round;
    private JTextArea txt_question_name;
    private ImageIcon icon;

    private Font font_global;

    public GUI() {

        font_global = new Font("Arial Black", Font.BOLD, 22);

        JFrame frame = new JFrame("Test");
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
        mainPanel = new JPanel(new BorderLayout());

        centerPanel = new JPanel(new GridBagLayout());
        //centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        typePanel = new JPanel();
        typePanel.setBackground(Color.BLACK);
        typePanel.setLayout(new BoxLayout(typePanel,BoxLayout.LINE_AXIS));
        textPanel = new JPanel();
        textPanel.setBackground(Color.white);

        responesPanel = new JPanel();
        responesPanel.setLayout(new GridLayout(4,2));
        //responesPanel.setPreferredSize(new Dimension(100,100));


        String questionString = "The computer Deep Blue is known for beating a grandmaster in which game?";
        //questionString = questionString.substring(0,53) + "-" + questionString.substring(53);
        txt_question_name = new JTextArea(questionString);
        txt_question_name.setFont(font_global);
        txt_question_name.setOpaque(false);
        txt_question_name.setLineWrap(true);
        txt_question_name.setWrapStyleWord(true);
        txt_question_name.setPreferredSize(new Dimension(700, 90));
        txt_question_name.setEnabled(false);
        txt_question_name.setDisabledTextColor(Color.BLACK);


        txt_type_qs = new JLabel("Stop the Timer");
        txt_type_qs.setFont(font_global);
        txt_type_qs.setForeground(Color.white);
        txt_type_round = new JLabel("Technology");
        txt_type_round.setForeground(Color.white);
        txt_type_round.setFont(font_global);
        typePanel.add(Box.createRigidArea(new Dimension(40,0)));
        typePanel.add(txt_type_round);
        typePanel.add(Box.createHorizontalGlue());
        typePanel.add(txt_type_qs);
        typePanel.add(Box.createRigidArea(new Dimension(40,0)));

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        centerPanel.add(textPanel,c);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 1;
        centerPanel.add(responesPanel,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        textPanel.add(txt_question_name);
        responesPanel.add(new JLabel("Q/1"));
        responesPanel.add(new JButton("Checkers"));
        responesPanel.add(new JLabel("W/2"));
        responesPanel.add(new JButton("Chess"));
        responesPanel.add(new JLabel("E/3"));
        responesPanel.add(new JButton("Monopoly"));
        responesPanel.add(new JLabel("R/4"));
        responesPanel.add(new JButton("Snakes and Ladders"));
        mainPanel.add(typePanel, BorderLayout.PAGE_START);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        //frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        GUI frame = new GUI();
    }
}



