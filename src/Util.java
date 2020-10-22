import javax.swing.*;
import java.awt.event.ActionListener;

public class Util {

    int seconds;
    int minutes;
    Timer timer;
    int counter = 0;

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
        timer = new Timer(timerDelay, gameTimer);
        timer.start();
    }

    public void gameTimerStop(){
        timer.stop();
    }
    public void gameTimerReset(){
        timer.restart();
    }

    public void addToScoreBoard(){

    }

    // databas? prata med julia!
    public void saveScoreBoard(){

    }

    public Timer getTimer() {
        return timer;
    }

    public String clickCounter(){
        counter++;
        System.out.println("counter value" + counter);
        return "Antal klick: " + counter;

    }

    public void clickcounterReset(){
        counter = 0;
        System.out.println("Du har nollställt klickräknaren");
    }

    public int getCounter() {
        return counter;
    }
}
