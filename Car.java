/* Sarah Higgins, Hillary Ssemakula, Rodrigo Choque Cardenas
 *
 * Car class. This class is responsible for the creation of Car objects.  The car objects are the actual
 *      moving objects within he MainPanel when it loads.  The car objects race each other with random
 *      speed values assigned to them.  The class implements the Drawable interface which allows it
 *      to be drawn within the RaceEvent.
 */


//import statements
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

//Drawable interface allows an instance of this class to draw itself
public class Car implements Drawable {

  //attributes/ instance variables
  private int x;
  private int y;
  private int newX, newY;
  private String pathString;
  private ArrayDeque<Drawable> route;
  private double speed;
  private double distance;
  private long start;
  private long time;
  private int engine;
  private int tire;
  private Color color;
  private String name;
  private BufferedImage img = null;
  private boolean user;
  
  ArrayDeque<Drawable> path;

  //Car constructor
  public Car(ArrayDeque<Drawable> path, String name, boolean user, Color usrColor) {
    this.path = path;
    this.name = name;
    this.user = user;
    setPathString(path);
    engine = 6000;
    tire = 6000;

    //Car's position is determined by the coordinates of the Checkpoint it is assigned to.
    x = ((Checkpoint)path.peek()).getXValue();
    y = ((Checkpoint)path.pop()).getYValue() + 67;
    getNextCheckpoint();

    //Car is drawn by an imported icon within the same folder as its code.
    try {
      img = ImageIO.read(new File("carclipart.png"));
      //the color of the cars will be randonm;y generated
      Color carColor = new Color((int)(Math.random() * 25.6)*10, (int)(Math.random() * 25.6)*10, (int)(Math.random() * 25)*10);
      //but for user, the color will be the one received in the constructor
      if(user){
        
        //the for loops change small areas of pixels of the cars image
        //this is helpful since we will only need one image icon for the entire game
        for(int i = 10; i <= 20; i++){
          for(int j = 5; j <= 13; j++){
            img.setRGB(i, j, usrColor.getRGB());
          }
        }
        for(int i = 30; i <= 36; i++){
          for(int j = 7; j <= 11; j++){
            img.setRGB(i, j, usrColor.getRGB());
          }
        }
         color = usrColor;
      }
      else{
        for(int i = 10; i <= 20; i++){
          for(int j = 5; j <= 13; j++){
            img.setRGB(i, j, carColor.getRGB());
          }
          
        }
        for(int i = 30; i <= 36; i++){
          for(int j = 7; j <= 11; j++){
            img.setRGB(i, j, carColor.getRGB());
          }
        }
      }
    } catch(Exception e) {
      System.out.println("Image icon not found");
    }
    
  }

  //Hillary
  //gets the next checkpoint's x and y coordinates so the car knows where to go next
  public void getNextCheckpoint(){
    newX = ((Checkpoint)path.peek()).getXValue();
    newY = ((Checkpoint)path.pop()).getYValue() + 67;
  }

  //Hillary, Rodrigo
  //move method physically moves the car within the MainPanel
  public boolean move() {
    
    int x1 = x;
    int y1 = y;
    
    //if car needs to move drom right to left, the car direction must be negative
    boolean isNegative = false;
    
    if ((newX - x) < 0) 
      isNegative = true;
    
    int displacement = setDisplacement();
    //the car will move according to linear displacement y=mx+b
    //thus we need the slope between two checkpoints and its y-intercept
    double slope;
    double yIntercept;
    
    //conditionals that will determine if the car keeps moving
    //if current coordinates of car is equeal to destination coordinates, the move method will return false
    //thus it will mean it either needs to stop or get next checkpoint coordinates
    if (x == newX && y == newY) 
    {
      if(path.isEmpty())
      {
        return false;
      }
      else
      {
        this.getNextCheckpoint();
      }
    } 
    //if we havent reached destination checkpoint yet, we mmust find slope and y-intercept to know whats our displacement
    else if (x != newX || y != newY) 
    {
      try
      {
        slope = ((((double)newY - y) / ((double)newX - x)));
        yIntercept = newY - (slope * newX);
        
        if (isNegative) x -= displacement;
        
        else x += displacement;
        
        y = (int) ((slope * x) + yIntercept);
        updateDistance(x, x1, y, y1);
      }
      catch(Exception e)
      {
        if ((newY - y) < 0)
        { 
          y -= displacement;
          updateDistance(x, x1, y, y1);
        }
        else
        {
          y += displacement;//This is in case checkpoints are in the same vertical line which can happen
          //Both x-coordinates would be the same, thus diving by 0 would be an error
          updateDistance(x, x1, y, y1);
        }
      }
      return true;
    } 
    //this will be like a stoping distamce whe car is close enought to checkpoint
    else if (Math.abs(x - newX) < 1 || Math.abs(y - newY) < 1) {
      y = newY;
      x = newX;
      updateDistance(x, x1, y, y1);
      return true;
    }
    return false;
  }
  
  /*setTime()
   * @param long s starts the timer for the Car object
   *        This method sets the timer of the Car
   */
  public void setTime(long s) {
    start = s;
    time = System.currentTimeMillis() - start;
  }

  //returns the time of the Car
  public long getTime() {
    return time;
  }

  /*
   * @param int x1 sets the car's initial x value before moving
   * @param int x2 sets the car's final x value after moving
   * @param int y1 sets the car's initial y value before moving
   * @param int y2 sets the car's final y value after moving
   *        This method updates the distance that the car has
   *        traveled.
   *        Hillary
   */
  public void updateDistance(int x1, int x2, int y1, int y2) {
    double displacement = Math.sqrt(Math.pow((double)(x2 - x1),2) + Math.pow((double)(y2 - y1),2));
    distance += displacement;
    setTime(start);
    setSpeed();
  }

  //returns the distance the car has traveled
  public String getDistance() {
    return String.format("%6.2f",distance);
  }

  /* @param ArrayDeque<Drawable> path takes in an ArrayDeque of type Drawable
   *        to handle the creation of checkpoint origin spots in the GUI For
   *        the Car objects
   * Sets the path of the car to travel from one checkpoint to another.
   * Hillary, Rodrigo
   */
  public void setPathString(ArrayDeque<Drawable> path)
  {
    pathString = "";
    for(Drawable d: path)
    {
      //we buld the string obtaining each letter ID from each checkpoint
      Checkpoint checkpoint = (Checkpoint)d;
      pathString += checkpoint.getID()+"";
    }
  }

  //returns the Car's path
  public String getPath() {
    return pathString;
  }

  //sets the engine value, and subtracts a random integer value from the engine's value.
  //Hillary
  public int setEngine() {
    int randInt = (int)(Math.random() * 2.5);
    engine -= randInt;
    return randInt;
  }

  //returns the engine's randomized value
  public int getEngine() {
    return engine;
  }

  //sets the tire value, and subtracts a random integer value from the engine's value.
  //Hillary
  public int setTire() {
    int randInt = (int)(Math.random() * 2.5);
    tire -= randInt;
    return randInt;
  }

  //returns the tire's randomized value
  public int getTire() {
    return tire;
  }

  //set displacement is simply the setEngine methods and setTire methods added together.
  //according to this values, the car will get higher or lower displacements
  //Hillary, Rodrigo
  public int setDisplacement() {
    return setEngine() + setTire();
  }

  //setSpeed method sets the speed of each car, which will be a random value for each individual car
  public void setSpeed() {
    speed = distance/(double)(time/1000);
  }

  //returns the speed of the car
  public double getSpeed() {
     return speed;
  }

  //returns/ displays the speed of the car as a String in the GUI underneath the "speed" tab
  //Hillary
  public String getSpeedString() {
    return String.format("%10.3f", speed);
  }

  //returns the name of the car
  public String getName() {
    return name;
  }

  //toString displays the statistics of the car objects
  //Hillary
  public String toString() {
    String padding = "";
    if(name.length() == 1) padding +="           ";
    else if(name.length() == 2) padding +="          ";
    else if(name.length() == 3) padding +="         ";
    else if(name.length() == 4) padding +="      ";
    else if(name.length() == 5) padding +="   ";
    else if(name.length() == 6) padding +=" ";
    return name +padding + "          " + getSpeedString() + "               " + getDistance()+"                    "+pathString;
  }

  //draw method uses Graphics2D to draw the icon of the car object within the file.
  //this method will be used by race event who will paint all cars and checkpoints
  //Hillary, Rodrigo
  @Override
  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.drawImage(img, null, x, y);    
    if(user){
     g2.setFont( new Font("dialog", Font.BOLD, 10));
      g2.drawString(name, x, y+ 30);
    }
  }
}
