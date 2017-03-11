import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by Sarah on 3/6/17.
 */
public class Car implements Drawable {
  
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
  
  public Car(ArrayDeque<Drawable> path, String name, boolean user, Color usrColor) {
    this.path = path;
    this.name = name;
    this.user = user;
    setPathString(path);
    engine = 6000;
    tire = 6000;
    
    x = ((Checkpoint)path.peek()).getXValue();
    y = ((Checkpoint)path.pop()).getYValue() + 67;
    getNextCheckpoint();
    
    try {
      img = ImageIO.read(new File("carclipart.png"));
      Color carColor = new Color((int)(Math.random() * 25.6)*10, (int)(Math.random() * 25.6)*10, (int)(Math.random() * 25)*10);
      if(user){
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
  
  public void getNextCheckpoint(){
    newX = ((Checkpoint)path.peek()).getXValue();
    newY = ((Checkpoint)path.pop()).getYValue() + 67;
  }
  
  public boolean move() {
    
    int x1 = x;
    int y1 = y;
    
    boolean isNegative = false;
    
    if ((newX - x) < 0) 
      isNegative = true;
    
    int displacement = setDisplacement();
    double slope;
    double yIntercept;
    
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
          y += displacement;//This is in case flags are in the same vertical line which can happen
          //Both x-coordinates would be the same, thus diving by 0 would be an error
          updateDistance(x, x1, y, y1);
        }
      }
      return true;
    } 
    else if (Math.abs(x - newX) < 1 || Math.abs(y - newY) < 1) {
      y = newY;
      x = newX;
      updateDistance(x, x1, y, y1);
      return true;
    }
    return false;
  }
  
  public void setTime(long s) {
    start = s;
    time = System.currentTimeMillis() - start;
  }

  public long getTime() {
    return time;
  }
  /* Hillary. Method updateDistance. @params int x1, int x2, int y1, int y2. This method takes in 4 values
   * and uses the mathematical distance formular to find the distance between the pair of coordinates
   * Distance found is between (x1, y1) and (x2, y2). The distance the car has moved is updated by increasing it
   * by the distance between the line. The speed of the car is calculated from this distance also.
   */
  public void updateDistance(int x1, int x2, int y1, int y2) {
    double displacement = Math.sqrt(Math.pow((double)(x2 - x1),2) + Math.pow((double)(y2 - y1),2));
    distance += displacement;
    setTime(start);
    setSpeed();
  }
  
  public String getDistance() {
    return String.format("%6.2f",distance);
  }
  
  
  public void setPathString(ArrayDeque<Drawable> path)
  {
    pathString = "";
    for(Drawable d: path)
    {
      Checkpoint checkpoint = (Checkpoint)d;
      pathString += checkpoint.getID()+"";
    }
  }
  
  public String getPath() {
    return pathString;
  }
  
  public int setEngine() {
    int randInt = (int)(Math.random() * 2.5);
    engine -= randInt;
    return randInt;
  }
  
  public int getEngine() {
    return engine;
  }
  
  public int setTire() {
    int randInt = (int)(Math.random() * 2.5);
    tire -= randInt;
    return randInt;
  }
  
  public int getTire() {
    return tire;
  }

  public int setDisplacement() {
    return setEngine() + setTire();
  }
  
  public void setSpeed() {
    speed = distance/(double)(time/1000);
  }
  
  public double getSpeed() {
     return speed;
  }
  
  public String getSpeedString() {
    return String.format("%10.3f", speed);
  }
  
  public String getName() {
    return name;
  }
  /* Hillary. Method @override toString(). returns the name, speed, distance and path of the car on one line.
   * depending on the length of the car's name, the string is padded with spaces.
   */
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
  
  @Override
  public void draw(Graphics2D g2) {
    g2.setColor(color);
    g2.drawImage(img, null, x, y);  
    /*Hillary: This code enables the name of the car to be drawn at it's bottom, if it is the user's car */
    if(user){
     g2.setFont( new Font("dialog", Font.BOLD, 10));
      g2.drawString(name, x, y+ 30);
    }
  }
}
