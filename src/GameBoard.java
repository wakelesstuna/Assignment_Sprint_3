import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameBoard extends JFrame {

    List<Button> winCondition = buttonFactory();
    List<Button> buttonsList = buttonFactory();
    Util u = new Util();

    boolean gameOver = false;
    String playerName;
    String musicPath = "src/assets/sound/Bubble-Gum-Puzzler-2 (online-audio-converter.com).wav";
    String clickPath = "src/assets/sound/Lamp-Switch_Off (online-audio-converter.com).wav";

    ImagePanel parent = new ImagePanel("src/assets/image/BackMain.jpg");
    JPanel title = new JPanel();
    JPanel gameBoard = new JPanel(new GridLayout(4, 4));
    JPanel bottomPanel = new JPanel(new FlowLayout());

    JLabel labelTitle = new JLabel("----------Best Game Ever----------");

    JLabel gameTime = new JLabel();
    JLabel clickCounter = new JLabel("Antal klick: " + u.counter);
    JLabel playerNameLabel;

    JButton newGameButton = new JButton("New Game");
    JButton cheatButton = new JButton("Cheat");

    public GameBoard(String playerName) {
        splitImageInto16pieces();
        //u.loadGameMusic(musicPath); // comment out to kill music
        parent.setLayout(new FlowLayout());

        add(parent);
        parent.add(title, BorderLayout.NORTH);
        parent.add(gameBoard, BorderLayout.CENTER);
        parent.add(bottomPanel, BorderLayout.SOUTH);

        title.setBackground(Color.GREEN);
        title.add(labelTitle);
        gameBoard.setPreferredSize(new Dimension(500, 500));
        labelTitle.setFont(new Font("Georgia", Font.BOLD, 32));

        newGameButton.addActionListener(e -> newGame());
        cheatButton.addActionListener(e -> cheatButton());

        bottomPanel.add(playerNameLabel = new JLabel("Player: " + playerName));
        bottomPanel.add(gameTime);
        bottomPanel.add(clickCounter);
        bottomPanel.add(cheatButton);
        bottomPanel.add(newGameButton);

        shuffle(buttonsList);
        renderButtons(buttonsList);


        u.gameTimer(gameTime);
        addActionListenerToButtons();
        setLocation(600, 200);
        setSize(700, 700);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public List<Button> buttonFactory() {
        List<Button> buttons = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            JButton button = new JButton(""+i);
            button.setFont(new Font("TimesNewRoman", Font.BOLD, 25));
            buttons.add(new Button(button, i));
            System.out.println("Brick " + i + " created");
        }


        JButton blankButton = new JButton("");
        blankButton.setOpaque(false);
        blankButton.setContentAreaFilled(false);
        blankButton.setBorderPainted(false);
        Button b = new Button(blankButton, 0);


        buttons.add(b);
        return buttons;
    }

    public List<Button> buttonFactoryImage() {
        List<Button> buttons = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            ImageIcon image = new ImageIcon("Pictures\\SplitImage\\smiley" + i + ".JPG");
            buttons.add(new Button(new JButton("" + i), i, image));
            System.out.println("Brick " + i + " created");
        }


        JButton blankButton = new JButton("");
        blankButton.setBackground(Color.black);

        Button b = new Button(blankButton, 0);


        buttons.add(b);
        return buttons;
    }

    public void renderButtons(List<Button> list) {
        list.forEach(Button -> {
            gameBoard.add(Button.getButton());
            System.out.println("Working " + Button.getButtonID());
        });
    }

    public void shuffle(List<Button> list) {
        Collections.shuffle(list);
    }

    public void cheatButton() {
        u.gameTimerStop();
        gameBoard.removeAll();
        buttonsList.clear();
        renderButtons(winCondition);
        gameBoard.updateUI();
    }

    public void newGame() {

        gameOver = false;
        gameBoard.removeAll();
        buttonsList.clear();
        u.setSeconds(0);
        u.setMinutes(0);
        u.clickCounterReset();
        clickCounter.setText("Antal klick: " + u.counter);

        buttonsList = buttonFactory();
        addActionListenerToButtons();
        shuffle(buttonsList);
        renderButtons(buttonsList);
        u.gameTimerStop();
        u.gameTimer(gameTime);
    }

    public void winScreen() {

        u.gameTimerStop();
        String gameTimeComplete = gameTime.getText();
        JTextArea test = new JTextArea("were");
        JFrame winFrame = new JFrame();
        JPanel winPanel = new JPanel(new GridLayout(3, 1));
        JLabel winLabel = new JLabel("WINNER WINNER", SwingConstants.CENTER);
        JLabel timeLabel = new JLabel(gameTimeComplete, SwingConstants.CENTER);
        JLabel playerNameLabel = new JLabel(playerName, SwingConstants.CENTER);

        winPanel.add(playerNameLabel);
        winPanel.add(winLabel);
        winPanel.add(timeLabel);
        winFrame.add(test);

        winFrame.setLocation(600, 200);
        winFrame.setSize(300, 300);
        winFrame.setVisible(true);
    }

    public void addActionListenerToButtons() {
        for (Button button : buttonsList) {
            if (button != null) {
                button.getButton().addActionListener(l -> {
                    u.addButtonClickSound(clickPath);
                    checkIfClickedButtonIsNextToEmptyButton(button);
                });
            }
        }
    }

    public int findEmptyButton() {
        int emptyButtonIndex = 0;
        for (Button button : buttonsList) {
            if (button.getButtonID() == 0) {
                emptyButtonIndex = buttonsList.indexOf(button);
            }
        }
        return emptyButtonIndex;
    }

    public void turnAllButtonsGreenWhenWin() {
        for (Button button : buttonsList) {
            button.getButton().setBackground(Color.GREEN);
        }
    }

    public boolean isWinCondition() {
        for (int i = 0; i < winCondition.size(); i++) {
            int tempWin = winCondition.get(i).getButtonID();
            int tempActual = buttonsList.get(i).getButtonID();
            if (tempWin != tempActual)
                return false;
        }
        return true;
    }

    public void moveButton(int index) {
        if (!gameOver) {
            Collections.swap(buttonsList, index, findEmptyButton());
            renderButtons(buttonsList);
            clickCounter.setText("Antal klick: " + u.counter++);
            gameBoard.updateUI();
            if (gameOver = isWinCondition()) {
                turnAllButtonsGreenWhenWin();
                winScreen();
            }
        }
    }

    // TODO: 2020-10-23 Build algorithm for check if game is winnable
    // public void checkIfGameIsWinnable(){}

    public void checkIfClickedButtonIsNextToEmptyButton(Button clickedButton) {
        switch (buttonsList.indexOf(clickedButton)) {
            case 0 -> {
                if (buttonsList.get(1).equals(buttonsList.get(findEmptyButton())))
                    moveButton(0);
                else if (buttonsList.get(4).equals(buttonsList.get(findEmptyButton())))
                    moveButton(0);
            }
            case 1 -> {
                if (buttonsList.get(0).equals(buttonsList.get(findEmptyButton())))
                    moveButton(1);
                else if (buttonsList.get(2).equals(buttonsList.get(findEmptyButton())))
                    moveButton(1);
                else if (buttonsList.get(5).equals(buttonsList.get(findEmptyButton())))
                    moveButton(1);
            }
            case 2 -> {
                if (buttonsList.get(1).equals(buttonsList.get(findEmptyButton())))
                    moveButton(2);
                else if (buttonsList.get(3).equals(buttonsList.get(findEmptyButton())))
                    moveButton(2);
                else if (buttonsList.get(6).equals(buttonsList.get(findEmptyButton())))
                    moveButton(2);
            }
            case 3 -> {
                if (buttonsList.get(2).equals(buttonsList.get(findEmptyButton())))
                    moveButton(3);
                else if (buttonsList.get(7).equals(buttonsList.get(findEmptyButton())))
                    moveButton(3);
            }
            case 4 -> {
                if (buttonsList.get(0).equals(buttonsList.get(findEmptyButton())))
                    moveButton(4);
                else if (buttonsList.get(5).equals(buttonsList.get(findEmptyButton())))
                    moveButton(4);
                else if (buttonsList.get(8).equals(buttonsList.get(findEmptyButton())))
                    moveButton(4);
            }
            case 5 -> {
                if (buttonsList.get(1).equals(buttonsList.get(findEmptyButton())))
                    moveButton(5);
                else if (buttonsList.get(6).equals(buttonsList.get(findEmptyButton())))
                    moveButton(5);
                else if (buttonsList.get(4).equals(buttonsList.get(findEmptyButton())))
                    moveButton(5);
                else if (buttonsList.get(9).equals(buttonsList.get(findEmptyButton())))
                    moveButton(5);
            }
            case 6 -> {
                if (buttonsList.get(2).equals(buttonsList.get(findEmptyButton())))
                    moveButton(6);
                else if (buttonsList.get(7).equals(buttonsList.get(findEmptyButton())))
                    moveButton(6);
                else if (buttonsList.get(10).equals(buttonsList.get(findEmptyButton())))
                    moveButton(6);
                else if (buttonsList.get(5).equals(buttonsList.get(findEmptyButton())))
                    moveButton(6);
            }
            case 7 -> {
                if (buttonsList.get(3).equals(buttonsList.get(findEmptyButton())))
                    moveButton(7);
                else if (buttonsList.get(6).equals(buttonsList.get(findEmptyButton())))
                    moveButton(7);
                else if (buttonsList.get(11).equals(buttonsList.get(findEmptyButton())))
                    moveButton(7);
            }
            case 8 -> {
                if (buttonsList.get(12).equals(buttonsList.get(findEmptyButton())))
                    moveButton(8);
                else if (buttonsList.get(4).equals(buttonsList.get(findEmptyButton())))
                    moveButton(8);
                else if (buttonsList.get(9).equals(buttonsList.get(findEmptyButton())))
                    moveButton(8);
            }
            case 9 -> {
                if (buttonsList.get(5).equals(buttonsList.get(findEmptyButton())))
                    moveButton(9);
                else if (buttonsList.get(10).equals(buttonsList.get(findEmptyButton())))
                    moveButton(9);
                else if (buttonsList.get(8).equals(buttonsList.get(findEmptyButton())))
                    moveButton(9);
                else if (buttonsList.get(13).equals(buttonsList.get(findEmptyButton())))
                    moveButton(9);
            }
            case 10 -> {
                if (buttonsList.get(6).equals(buttonsList.get(findEmptyButton())))
                    moveButton(10);
                else if (buttonsList.get(11).equals(buttonsList.get(findEmptyButton())))
                    moveButton(10);
                else if (buttonsList.get(9).equals(buttonsList.get(findEmptyButton())))
                    moveButton(10);
                else if (buttonsList.get(14).equals(buttonsList.get(findEmptyButton())))
                    moveButton(10);
            }
            case 11 -> {
                if (buttonsList.get(7).equals(buttonsList.get(findEmptyButton())))
                    moveButton(11);
                else if (buttonsList.get(10).equals(buttonsList.get(findEmptyButton())))
                    moveButton(11);
                else if (buttonsList.get(15).equals(buttonsList.get(findEmptyButton())))
                    moveButton(11);
            }
            case 12 -> {
                if (buttonsList.get(8).equals(buttonsList.get(findEmptyButton())))
                    moveButton(12);
                else if (buttonsList.get(13).equals(buttonsList.get(findEmptyButton())))
                    moveButton(12);
            }
            case 13 -> {
                if (buttonsList.get(12).equals(buttonsList.get(findEmptyButton())))
                    moveButton(13);
                else if (buttonsList.get(9).equals(buttonsList.get(findEmptyButton())))
                    moveButton(13);
                else if (buttonsList.get(14).equals(buttonsList.get(findEmptyButton())))
                    moveButton(13);
            }
            case 14 -> {
                if (buttonsList.get(13).equals(buttonsList.get(findEmptyButton())))
                    moveButton(14);
                else if (buttonsList.get(10).equals(buttonsList.get(findEmptyButton())))
                    moveButton(14);
                else if (buttonsList.get(15).equals(buttonsList.get(findEmptyButton())))
                    moveButton(14);
            }
            case 15 -> {
                if (buttonsList.get(11).equals(buttonsList.get(findEmptyButton())))
                    moveButton(15);
                else if (buttonsList.get(14).equals(buttonsList.get(findEmptyButton())))
                    moveButton(15);
            }
        }
    }

    public void splitImageInto16pieces() {
        int row = 4;
        int column = 4;
        try {
            BufferedImage originalPicture = ImageIO.read(new File("Pictures\\smiley.jpg"));

            int pictureWidth = originalPicture.getWidth();
            int pictureHeight = originalPicture.getHeight();

            int buttonWidth = pictureWidth / column;
            int buttonHeight = pictureHeight / row;

            int x = 0;
            int y = 0;
            int counter = 1;

            for (int i = 0; i < 16; i++) {
                y = 0;
                for (int j = 0; j < column; j++) {
                    try {
                        BufferedImage SubImgage = originalPicture.getSubimage(y, x, buttonWidth, buttonHeight);
                        File outputFile = new File("Pictures\\SplitImage\\smiley" + counter + ".JPG");
                        ImageIO.write(SubImgage, "jpg", outputFile);
                        counter++;
                        y += buttonWidth;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                x += buttonHeight;
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
