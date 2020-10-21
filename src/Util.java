import javax.swing.*;
import java.awt.event.ActionListener;

public class Util {

    int seconds;
    int minutes;

    public void loadGameMusic(){

    }

    public void buttonClickSound(){

    }

    public void gameTimer(){
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
            System.out.println("Tid: " + minutes + ':' + secondZero + seconds);
            //Ska returna en String sen som man skriver ut till en label
        };

        int timerDelay = 1000;
        Timer myTimer = new Timer(timerDelay, gameTimer);
        myTimer.start();

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
