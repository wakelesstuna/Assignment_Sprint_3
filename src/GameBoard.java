import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameBoard extends JFrame {

    List<Button> buttons;

    JPanel parent = new JPanel(new BorderLayout());
    JPanel title = new JPanel();
    JPanel gameBoard = new JPanel(new GridLayout(4,4));
    JPanel bottomPanel = new JPanel(new FlowLayout());

    JLabel labelTitle = new JLabel("Best Game Ever");

    JLabel gameTime = new JLabel("Time: ");
    JLabel playerName = new JLabel("Player: ");

    JButton newGamebutton = new JButton("New Game");

    public GameBoard(){
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

        title.add(newGamebutton);
        title.add(labelTitle);


        bottomPanel.add(playerName);
        bottomPanel.add(gameTime);

        buttons = buttonFactory();
        renderButtons(buttons);




        newGamebutton.addMouseListener(m);

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

    public void shuffle(){
        Collections.shuffle(buttons);
    }

    public void reset (){
        List <Button> tempList = new ArrayList<>();
        for (var button : buttons) {
            int temp = button.getButtonID();
            tempList.add(temp, button);
        }
        buttons = tempList;
    }
}
