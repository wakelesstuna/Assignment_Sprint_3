import javax.swing.*;
import java.awt.event.ActionListener;

public class Util {

    int seconds;
    int minutes;

    public void loadGameMusic(){

    }

    public void buttonClickSound(){

    }

    public void gameTimer(JLabel label){
        ActionListener gameTimer = e -> {
            String secondZero = "";
            if (seconds < 59) {
                seconds++;
                if (seconds < 10)
                    secondZero = "0";
            } else {
                secondZero = "0";
                seconds = 0;
                minutes++;
            }
            label.setText("Tid: " + minutes + ':' + secondZero + seconds);
            System.out.println("Tid: " + minutes + ':' + secondZero + seconds);
            //Ska returna en String sen som man skriver ut till en label
        };

        int timerDelay = 1000;
        Timer timer = new Timer(timerDelay, gameTimer);
        timer.start();
    }

    public void gameTimerStop(){

    }

    public void addToScoreBoard(){

    }

    // databas? prata med julia!
    public void saveScoreBoard(){

    }

    public void clickCounter(){

    }

}
