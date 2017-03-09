import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Checkpoint implements Drawable{

    private int xValue, yValue;
    private char iD;
    private boolean passed;
    private BufferedImage icon = null;

    public Checkpoint(char iD){

        this.iD = iD;
        passed = false;

        xValue = (int)(Math.random()* 550) + 200;
        yValue = (int)(Math.random()* 330) + 70;

        try{
            icon = ImageIO.read(new File("iconCheckpoint.png"));
        }
        catch(Exception e){
            System.out.println("Image icon not found");
        }
    }

    public void draw(Graphics2D g2){

        g2.drawImage(icon, null, xValue, yValue);
        g2.drawString(iD + "", xValue + 22, yValue + 35);
    }

    public int getXValue(){
        return xValue;
    }

    public int getYValue(){
        return yValue;
    }

    public char getID(){
        return iD;
    }

    public boolean isPassed(){
        return passed;
    }

    public void checkpointPassed(){
        passed = true;
    }
}
