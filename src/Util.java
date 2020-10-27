import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Util {

    Clip play;
    private int seconds;
    private int minutes;
    protected int counter = 0;
    Timer timer;

    public void loadGameMusic(String musicPath){
        try {
            File music = new File(musicPath);

            if (music.exists()) {
                AudioInputStream readMusic = AudioSystem.getAudioInputStream(music);
                play = AudioSystem.getClip();
                play.open(readMusic);
                play.start();
                play.loop(Clip.LOOP_CONTINUOUSLY);

            } else {
                JOptionPane.showMessageDialog(null, "Can't find background music file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic(){
        play.start();
        play.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopMusic(){
        play.stop();
    }

    public void addButtonClickSound(String musicPath){
        try {
            File music = new File(musicPath);
            if (music.exists()) {
                AudioInputStream readMusic = AudioSystem.getAudioInputStream(music);
                Clip play = AudioSystem.getClip();
                play.open(readMusic);
                play.start();
            } else {
                JOptionPane.showMessageDialog(null, "Can't find click sound file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public boolean isGameSolvable(List<Button> buttonList, int rowsOfTheGame) {
        if (!isEven(rowsOfTheGame)) {
            return isEven(listInversionCount(buttonList));
        } else {
            if (isEven(rowsOfTheGame) && isEven(checkWitchRowEmptyButtonIsOn(buttonList))) {
                return !isEven(listInversionCount(buttonList));
            } else if (isEven(rowsOfTheGame) && isEven(listInversionCount(buttonList))) {
                return !isEven(checkWitchRowEmptyButtonIsOn(buttonList));
            }
        }
        return false;
    }

    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    public int checkWitchRowEmptyButtonIsOn(List<Button> buttonsList) {
        int emptyButton = findEmptyButton(buttonsList, 0);
        if (emptyButton >= 0 && emptyButton <= 3) {
            return 4;
        } else if (emptyButton >= 4 && emptyButton <= 7) {
            return 3;
        } else if (emptyButton >= 8 && emptyButton <= 11) {
            return 2;
        } else if (emptyButton >= 12 && emptyButton <= 15) {
            return 1;
        } else {
            return -1;
        }
    }

    public int findEmptyButton(List<Button> buttonsList, int buttonID) {
        int emptyButtonIndex = 0;
        for (Button button : buttonsList) {
            if (button.getButtonID() == buttonID) {
                emptyButtonIndex = buttonsList.indexOf(button);
            }
        }
        return emptyButtonIndex;
    }

    public int listInversionCount(List<Button> list) {

        int emptyButtonIndex = findEmptyButton(list, 0);
        int count = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (!(i == emptyButtonIndex)) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).getButtonID() > list.get(j).getButtonID()) {
                        count += 1;
                    }
                }
            }
        }
        System.out.println(count);
        return count;
    }

    public void clickCounterReset(){
        counter = 0;
        System.out.println("Du har nollställt klickräknaren");
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
