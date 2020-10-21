import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameBoard extends JFrame {

    List<Button> buttons;
    Util u = new Util();

    JPanel parent = new JPanel(new BorderLayout());
    JPanel title = new JPanel();
    JPanel gameBoard = new JPanel(new GridLayout(4,4));
    JPanel bottomPanel = new JPanel(new FlowLayout());

    JLabel labelTitle = new JLabel("Best Game Ever");

    JLabel gameTime = new JLabel();
    JLabel playerName = new JLabel("Player: ");


    public GameBoard(){
        add(parent);
        parent.add(title, BorderLayout.NORTH);
        parent.add(gameBoard, BorderLayout.CENTER);
        parent.add(bottomPanel, BorderLayout.SOUTH);

        title.add(labelTitle);

        u.gameTimer(gameTime);
        bottomPanel.add(playerName);
        bottomPanel.add(gameTime);

        buttons = buttonFactory();
        shuffle(buttons);
        renderButtons(buttons);
        setLocation(600,200);
        setSize(700,700);
        setVisible(true);
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

}
