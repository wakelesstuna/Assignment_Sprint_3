import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameBoard extends JFrame {

    List<Button> buttons;
    Util u = new Util();
    boolean gameOver = false;
    String playerName = "oscar";
    String musicPath = "src/assets/sound/Bubble-Gum-Puzzler-2 (online-audio-converter.com).wav";

    JPanel parent = new JPanel(new BorderLayout());
    JPanel title = new JPanel();
    JPanel gameBoard = new JPanel(new GridLayout(4,4));
    JPanel bottomPanel = new JPanel(new FlowLayout());

    JLabel labelTitle = new JLabel("Best Game Ever");

    JLabel gameTime = new JLabel();
    JLabel playerNameLabel = new JLabel("Player: " + playerName);

    JButton newGamebutton = new JButton("New Game");
    JButton cheatButton = new JButton("Cheat");

    public GameBoard(){
        u.loadGameMusic(musicPath);
        add(parent);
        parent.add(title, BorderLayout.NORTH);
        parent.add(gameBoard, BorderLayout.CENTER);
        parent.add(bottomPanel, BorderLayout.SOUTH);

        newGamebutton.addActionListener(e ->{newGame();});
        cheatButton.addActionListener(e -> {
            cheatButton();
            winScreen();
            });

        title.add(newGamebutton);
        title.add(labelTitle);

        u.gameTimer(gameTime);
        bottomPanel.add(playerNameLabel);
        bottomPanel.add(gameTime);
        bottomPanel.add(cheatButton);

        buttons = buttonFactory();
        shuffle(buttons);
        renderButtons(buttons);
        setLocation(600,200);
        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public List<Button> buttonFactory(){
        List<Button> buttons = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            buttons.add(new Button(new JButton("Tile " + i), i));
            System.out.println("Brick " + i + " created");
        }
        buttons.add(new Button(new JButton(""), 0));
        return buttons;
    }

    public void renderButtons(List<Button> list){
        list.forEach(Button -> {
            gameBoard.add(Button.getButtons());
            System.out.println("Working " + Button.getButtonID());
        });
    }
    public void shuffle (List<Button> list){
        Collections.shuffle(list);
    }

    public void cheatButton(){
        u.gameTimerStop();
        gameBoard.removeAll();
        buttons.clear();
        buttons = buttonFactory();
        renderButtons(buttons);
    }

    public void newGame(){
        gameBoard.removeAll();
        buttons.clear();
        // TODO: 2020-10-21 fixa till private ocg getter och setters
        u.seconds = 0;
        u.minutes = 0;

        buttons = buttonFactory();
        shuffle(buttons);
        renderButtons(buttons);
        u.gameTimerStop();
        u.gameTimer(gameTime);
    }

    public void winScreen(){

        u.gameTimerStop();
        String gameTimeComplete = gameTime.getText();
        JFrame winFrame = new JFrame();
        JPanel winPanel = new JPanel(new GridLayout(3,1));
        JLabel winLabel = new JLabel("WINNER WINNER", SwingConstants.CENTER);
        JLabel timeLabel = new JLabel(gameTimeComplete, SwingConstants.CENTER);
        JLabel playername = new JLabel(playerName, SwingConstants.CENTER);

        winPanel.add(playername);
        winPanel.add(winLabel);
        winPanel.add(timeLabel);
        winFrame.add(winPanel);

        winFrame.setLocation(600,200);
        winFrame.setSize(300,300);
        winFrame.setVisible(true);
    }

}
