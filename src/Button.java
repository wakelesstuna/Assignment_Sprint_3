import javax.swing.*;

public class Button {
    private JButton button;
    private int buttonID;

    public Button(JButton button, int buttonID) {
        this.button = button;
        this.buttonID = buttonID;
    }

    public Button(JButton button, int buttonID, ImageIcon imageIcon) {

        button.setIcon(imageIcon);
        this.button = button;
        this.buttonID = buttonID;

    }

    public JButton getButton() {
        return button;
    }

    public int getButtonID() {
        return buttonID;
    }
}

