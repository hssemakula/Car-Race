/* Created by Rodrigo Choque Cardenas
 * 
 * The next class is a blueprint of each checkpoint on the race track.
 *  
 * The checkpoint attributes are its position within the game frame, its ID, whether it was passed or not, and its image icon.
 * 
 * Since we create checkpoints randomly positioned on the window frame, its x and y coordinates are determined randomly
 * within the limits of the game frame.
 * 
 * We used a buffered image to load the icon of each checkpoint, and in order to be graphically displayed we used the
 * interface Drawable to allow each checkpoint to "draw itself" on the game window.
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

//Drawable interface allows an instance of this class to draw itself
public class Checkpoint implements Drawable{ 

	//attributes
    private int xValue, yValue;
    private char iD;
    private boolean passed;
    private BufferedImage icon = null;

    //constructor
    public Checkpoint(char iD){

        this.iD = iD;
        passed = false;

        //position of checkpoint is randomly generated
        xValue = (int)(Math.random()* 550) + 200;
        yValue = (int)(Math.random()* 330) + 70;

        
        //loading icon of checkpoint, catching any exception if generated
        try{
            icon = ImageIO.read(new File("iconCheckpoint.png"));
        }
        catch(Exception e){
            System.out.println("Image icon not found");
        }
    }

    //This method uses a Graohics2D to draw the icon of this checkpoint, and it also draws the 
    //ID of such checkpoint
    public void draw(Graphics2D g2){

        g2.drawImage(icon, null, xValue, yValue);
        g2.drawString(iD + "", xValue + 22, yValue + 35);
    }

    //Accessor method to get x value
    public int getXValue(){
        return xValue;
    }

    //Accessor method to get y atribute
    public int getYValue(){
        return yValue;
    }

    //Accessor method to get ID value
    public char getID(){
        return iD;
    }

    //Accessor method to get passed atribute
    public boolean isPassed(){
        return passed;
    }

    //Mutator method to change "passed" state
    public void checkpointPassed(){
        passed = true;
    }
}
