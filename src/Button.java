import javax.swing.*;

public class Button {
    private JButton buttons;
    private int buttonID;

    public Button(JButton button, int buttonID) {
        this.buttons = button;
        this.buttonID = buttonID;
    }

    public JButton getButtons(){
        return buttons;
    }

    public void setButtons(JButton buttons){
        this.buttons = buttons;
    }
    public int getButtonID(){
        return buttonID;
    }
}

