import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameBoard extends JFrame {

    List<Button> buttons;
    Util u = new Util();
    MouseListener m = new MouseListener();
    boolean gameOver = false;
    String playerName = "oscar";
    String musicPath = "src/assets/sound/Bubble-Gum-Puzzler-2 (online-audio-converter.com).wav";

    JPanel parent = new JPanel(new BorderLayout());
    JPanel title = new JPanel();
    JPanel gameBoard = new JPanel(new GridLayout(4, 4));
    JPanel bottomPanel = new JPanel(new FlowLayout());

    JLabel labelTitle = new JLabel("Best Game Ever");

    JLabel gameTime = new JLabel();
    JLabel clickCounter = new JLabel("Antal klick: " + u.counter);
    JLabel playerNameLabel = new JLabel("Player: " + playerName);

    JButton newGamebutton = new JButton("New Game");
    JButton cheatButton = new JButton("Cheat");

    public GameBoard(){
        u.loadGameMusic(musicPath);

        MouseAdapter m = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameBoard.removeAll();
                shuffle();
                renderButtons(buttons);
            }
        };

        add(parent);
        parent.add(title, BorderLayout.NORTH);
        parent.add(gameBoard, BorderLayout.CENTER);
        parent.add(bottomPanel, BorderLayout.SOUTH);

        newGamebutton.addActionListener(e -> {
            newGame();
        });
        cheatButton.addActionListener(e -> {
            cheatButton();
            winScreen();
        });
        gameBoard.addMouseListener(m);

        title.add(newGamebutton);
        title.add(labelTitle);

        u.gameTimer(gameTime);
        bottomPanel.add(playerNameLabel);
        bottomPanel.add(gameTime);
        bottomPanel.add(clickCounter);
        bottomPanel.add(cheatButton);

        buttons = buttonFactory();
        shuffle(buttons);
        renderButtons(buttons);

        newGamebutton.addMouseListener(m);

        addMouseListenerOnAllGameBoardButtons();
        setLocation(600, 200);
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public List<Button> buttonFactory() {
        List<Button> buttons = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            buttons.add(new Button(new JButton("Tile " + i), i));
            System.out.println("Brick " + i + " created");
        }
        buttons.add(new Button(new JButton(""), 0));
        return buttons;
    }

    public void addMouseListenerOnAllGameBoardButtons() {
        for (var b : buttons) {
            b.getButtons().addMouseListener(m);
        }
    }

    public void renderButtons(List<Button> list) {
        list.forEach(Button -> {
            gameBoard.add(Button.getButtons());
            System.out.println("Working " + Button.getButtonID());
        });
    }

    public void shuffle(List<Button> list) {
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
        u.clickcounterReset();
        clickCounter.setText("Antal klick: " + u.counter);

        buttons = buttonFactory();
        addMouseListenerOnAllGameBoardButtons();
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
        JLabel playernameLabel = new JLabel(playerName, SwingConstants.CENTER);

        winPanel.add(playernameLabel);
        winPanel.add(winLabel);
        winPanel.add(timeLabel);
        winFrame.add(winPanel);

        winFrame.setLocation(600,200);
        winFrame.setSize(300,300);
        winFrame.setVisible(true);
    }

    public void moveButtons() {

        clickCounter.setText(u.clickCounter());
    }

    public void shuffle() {
        Collections.shuffle(buttons);
    }

    public class MouseListener implements java.awt.event.MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            moveButtons();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
