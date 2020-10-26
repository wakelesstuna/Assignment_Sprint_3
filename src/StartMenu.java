import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class StartMenu extends JFrame {

    ScoreBoardUI highScorePanel;
    List<ScoreBoardObject> highScoreList = new ArrayList<>(); // deserizalize
    ImagePanel bottom = new ImagePanel("src/assets/image/BackMain.jpg");
    JLabel gapLabel1 = new JLabel("                  ");
    JLabel titleLabel = new JLabel("BEST GAME EVER", SwingConstants.CENTER);
    JLabel gapLabel2 = new JLabel("                  ");
    JLabel addNameLabel = new JLabel("Enter Your Name: ", SwingConstants.CENTER);
    JLabel gapLabel3 = new JLabel("               ");
    JTextField nameTextField = new JTextField(15);
    JLabel gapLabel5 = new JLabel("                                      ");
    JButton enterButton = new JButton("Enter");
    JLabel gapLabel4 = new JLabel("                                      ");
    JButton checkHighScore = new JButton("High Score");

    public StartMenu() {

        gapLabel1.setFont(new Font("TimesNewRoman", Font.BOLD, 40));

        titleLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setLocation(100,100);

        gapLabel2.setFont(new Font("TimesNewRoman", Font.BOLD, 24));

        addNameLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 40));
        addNameLabel.setForeground(Color.WHITE);

        gapLabel3.setFont(new Font("TimesNewRoman", Font.BOLD, 40));

        nameTextField.setFont(new Font("TimesNewRoman", Font.BOLD, 24));

        gapLabel4.setFont(new Font("TimesNewRoman", Font.BOLD, 35));

        enterButton.setSize(new Dimension(150,50));
        enterButton.setFont(new Font("TimesNewRoman", Font.BOLD, 28));
        enterButton.addActionListener(e -> {
            GameBoard g = new GameBoard(nameTextField.getText());
            setVisible(false);
        });

        gapLabel5.setFont(new Font("TimesNewRoman", Font.BOLD, 35));

        checkHighScore.setPreferredSize(new Dimension(150,50));
        checkHighScore.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        checkHighScore.addActionListener(e ->{
            highScorePanel = new ScoreBoardUI(highScoreList);
        });


        bottom.add(gapLabel1);
        bottom.add(titleLabel);
        bottom.add(gapLabel2);
        bottom.add(addNameLabel);
        bottom.add(gapLabel3);
        bottom.add(nameTextField);
        bottom.add(gapLabel4);
        bottom.add(enterButton);
        bottom.add(gapLabel5);
        bottom.add(checkHighScore);
        add(bottom);


        setLocation(700,200);
        setSize(450,600);
        setResizable(false);
        setVisible(true);

    }

    public ScoreBoardUI getHighScorePanel() {
        return highScorePanel;
    }

    public static void main(String[] args) {
        StartMenu s = new StartMenu();
    }
}
