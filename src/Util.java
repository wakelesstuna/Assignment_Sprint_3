import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class Util {

    int seconds;
    int minutes;
    Timer timer;

    public void loadGameMusic(String musicPath){
        try {
            File music = new File(musicPath);

            if (music.exists()) {
                AudioInputStream readMusic = AudioSystem.getAudioInputStream(music);
                Clip play = AudioSystem.getClip();
                play.open(readMusic);
                play.start();
                play.loop(Clip.LOOP_CONTINUOUSLY);

            } else {
                JOptionPane.showMessageDialog(null, "Can't find background music file");
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public void clickCounter(){

    }

    public Timer getTimer() {
        return timer;
    }
}
