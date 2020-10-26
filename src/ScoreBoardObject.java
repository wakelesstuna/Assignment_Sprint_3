import java.util.Comparator;

public class ScoreBoardObject{

    protected String name;
    protected String time;
    protected int buttonPresses;
    protected int timeToCompare; // to compare and set highScore

    public ScoreBoardObject(String name, String time, int buttonPresses) {
        this.name = name;
        this.time = time;
        this.buttonPresses = buttonPresses;
        this.timeToCompare = setTimeInSeconds(time);
    }

    public int setTimeInSeconds(String time){
        String t = time.substring(0,2) + time.substring(3);
        int seconds = Integer.parseInt(t.trim());
        System.out.println(seconds);
        return seconds;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getButtonPresses() {
        return buttonPresses;
    }

    public int getTimeToCompare() {
        return timeToCompare;
    }
}
