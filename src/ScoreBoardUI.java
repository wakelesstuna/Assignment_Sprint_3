import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ScoreBoardUI extends JFrame {

    int width = 450;
    int height = 600;
    String bgImage = "src/assets/image/BackMain.jpg";
    String testList = "1.    Oscar  03:30   324";


    ImagePanel parent = new ImagePanel(bgImage);
    JPanel panel = new JPanel(new GridLayout(11,4));
    JLabel title = new JLabel("-----HighScore-----", SwingConstants.CENTER);
    JLabel title0 = new JLabel("       ", SwingConstants.LEFT);
    JLabel titleName = new JLabel("Name: ", SwingConstants.LEFT);
    JLabel titleTime = new JLabel("Time: ", SwingConstants.LEFT);
    JLabel titleClicks = new JLabel("Clicks: ", SwingConstants.LEFT);
    JLabel score = new JLabel(testList, SwingConstants.LEFT);
    Font font = new Font("Microsoft JhengHei", Font.BOLD, 24);
    Font titleFont = new Font("Microsoft JhengHei", Font.BOLD, 35);

    public ScoreBoardUI(List<ScoreBoardObject> highScoreList) {
        title.setFont(titleFont);
        title0.setFont(font);
        titleName.setFont(font);
        titleTime.setFont(font);
        titleClicks.setFont(font);

        title.setForeground(Color.WHITE);
        title0.setForeground(Color.WHITE);
        titleName.setForeground(Color.WHITE);
        titleTime.setForeground(Color.WHITE);
        titleClicks.setForeground(Color.WHITE);

        parent.setLayout(new BorderLayout(15,15));
        parent.add(title, BorderLayout.NORTH);
        panel.add(title0);
        panel.add(titleName);
        panel.add(titleTime);
        panel.add(titleClicks);

        panel.setOpaque(false);
        panel.setSize(width,height);
        parent.add(addToHighScore(panel, makeStringOfScoreBoardObject(highScoreList)), BorderLayout.CENTER);
        add(parent);

        setLocation(700,200);
        setSize(width, height);
        setResizable(false);
        setVisible(true);
    }

    public JPanel addToHighScore(JPanel panel, List<String> list){
        JLabel label;
        for (String s : list) {
            label = new JLabel();
            label.setFont(new Font("Microsoft JhengHei", Font.BOLD, 24));
            label.setForeground(Color.WHITE);
            label.setText(s);
            panel.add(label);
        }
        return panel;
    }


    public void showHighScore(List<ScoreBoardObject> highScoreList){
        ScoreBoardUI h = new ScoreBoardUI(highScoreList);

    }
    public List<ScoreBoardObject> makeTestList(){
        List<ScoreBoardObject> testList = new ArrayList<>();
        int j = 234;

        for (int i = 0; i < 10; i++) {
            testList.add(new ScoreBoardObject("oscar", "03:00", j));
            System.out.println(testList.get(i).getTime());
        }
        testList.add(4,new ScoreBoardObject("kalle", "00:13", 234));
        return testList;
    }

    public List<ScoreBoardObject> ifListSmallerThenTenAddIndex(List<ScoreBoardObject> list){
        while (list.size() < 10){
            list.add(new ScoreBoardObject("", "Time: 00:00", 0));
        }
        return list;
    }
    public List<ScoreBoardObject> ifListBiggerThenTenRemoveLastIndex(List<ScoreBoardObject> list){
        while (list.size() > 10){
            list.remove(list.size()-1);
        }
        return list;
    }

    public List<String> makeStringOfScoreBoardObject(List<ScoreBoardObject> list){
        List<ScoreBoardObject> highScoreList;
        highScoreList = ifListSmallerThenTenAddIndex(list);
        highScoreList = ifListBiggerThenTenRemoveLastIndex(list);
        // TODO: sigrun förklara vad denna gör?
        highScoreList.sort(Comparator.comparing(ScoreBoardObject::getTimeToCompare));
        List<String> listOfStrings = new ArrayList<>();
        int j = 1;
        for (ScoreBoardObject i :highScoreList) {
            listOfStrings.add("         " + j);
            listOfStrings.add(i.getName());
            listOfStrings.add(i.getTime());
            listOfStrings.add("" + i.getButtonPresses());
            j++;
        }
        return listOfStrings;
    }
}
