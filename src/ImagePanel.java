import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

    private Image backgroundImage;

    public ImagePanel(String filename) {
        try{
            backgroundImage =ImageIO.read(new File(filename));
        }catch (IOException e){
            System.out.println("Bilden hittades inte");
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(backgroundImage,0,0,this);
    }
}
