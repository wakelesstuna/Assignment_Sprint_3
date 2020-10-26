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
    JButton newNumberGame = new JButton("New Number Game");
    JLabel gapLabel4 = new JLabel("                                      ");
    JButton newPictureGame = new JButton("New Picture Game");

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

        newNumberGame.setSize(new Dimension(150,50));
        newNumberGame.setFont(new Font("TimesNewRoman", Font.BOLD, 28));
        newNumberGame.addActionListener(e -> {
            GameBoard g = new GameBoard(nameTextField.getText(),true);
            setVisible(false);
        });

        gapLabel5.setFont(new Font("TimesNewRoman", Font.BOLD, 35));

        newPictureGame.setSize(new Dimension(150,50));
        newPictureGame.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
        newPictureGame.addActionListener(e ->{
            GameBoard g = new GameBoard(nameTextField.getText(),false);
            setVisible(false);
        });


        bottom.add(gapLabel1);
        bottom.add(titleLabel);
        bottom.add(gapLabel2);
        bottom.add(addNameLabel);
        bottom.add(gapLabel3);
        bottom.add(nameTextField);
        bottom.add(gapLabel4);
        bottom.add(newNumberGame);
        bottom.add(gapLabel5);
        bottom.add(newPictureGame);
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
