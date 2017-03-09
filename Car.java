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
    private double engine;
    private double tire;
    private Color color;
    private String name;
    private BufferedImage img = null;

    //double[] values = {4.7, 9.0, 3.5, 7.6, 10.0, 1.5, 2.8, 5.5, 6.0, 8.8};
    //private final Random random = new Random();
    
    ArrayDeque<Drawable> path;

    public Car(ArrayDeque<Drawable> path, String name) {
        this.path = path;
        this.name = name;
        setPathString(path)

        x = ((Checkpoint)path.peek()).getXValue();
        y = ((Checkpoint)path.pop()).getYValue();
        getNextCheckpoint();
        try {

            img = ImageIO.read(new File("carclipart.png"));
        } catch(Exception e) {
            System.out.println("Image icon not found");
        }
    }

    /*
    public void init() {
        try {
            URL url = new URL("carclipart.png");
            img = ImageIO.read(url);
        } catch (IOException e) {
        }
    } */

    public void getNextCheckpoint(){
    	newX = ((Checkpoint)path.peek()).getXValue();
        newY = ((Checkpoint)path.pop()).getYValue();
    }
    
    /* Look at the distance as a varable that starts out as 0. and every time you move a little bit you add to that distance variable
     * forexample if I start at (0,0), my distance is 0. when I move to (-9, 3). I'll get my old x and y and new x and y, use then to calculate
     * the actual distance i moved like so d = d + Math.sqrt((Oldx-newx)^2 + (Oldy-newy)^2); this the distance formula(pseudo code) of calculus
     * This is done every time you find a new x and y.
     */
     public boolean move() {
        
    	System.out.println("x"+x+"y"+y);
        boolean isNegative = false;

        if ((newX - x) < 0) isNegative = true;
        int velocity = (int)(Math.random() * 5); //This increment is not velocity, because velocity is speed with direction, yet this 
                                                 //changes the actual x coordinate of the car, it is more like displacement.
        double slope;
        double yIntercept;

        if (x == newX && y == newY) {
            return false;
        } else if (x != newX || y != newY) {
            slope = ((newY - y) / (newX - x));
            yIntercept = newY - (slope * newX);
            if (isNegative) x -= velocity;
            else x += velocity;
            y = (int) ((slope * x) + yIntercept);
            return true;
        } else if (Math.abs(x - newX) < 1 || Math.abs(y - newY) < 1) {
            y = newY;
            x = newX;
            return true;
        }
        return false; //This method ONLY AND ONLY returns false when the new x and new y are equal to the final x and final y so 
                      //this false should have a condition.
    }

    public void setTime(long t, long s) {
        s = start;
        t = time;
        start = System.currentTimeMillis();
        time = System.currentTimeMillis() - start;
    }

    public long getTime() {
        return time;
    }

    /* So this method should take in the old coordinates and the ones just calculated and using that formular that you have
     * in the else if  it should then calcualte a certain value and should add it to the current value of distance.
     * so in the move method, the first thing you need to store are original values of x and y. then EXACTLY after you 
     * calculate the new x and y before the return, call this method with the four coordinates. this will be very efficiaent I promise.
     */
    public void setDistance(double d, Checkpoint checkpoint) {
        int x2 = checkpoint.getXValue();
        int y2 = checkpoint.getYValue();
        d = distance;
        distance = 0.0;

        if (x == x2 && y == y2) {
            distance = 0.0;
        } else if (x != x2 || y != y2) {
            distance = Math.sqrt((y2 - y) ^ 2 + (x2 - x) ^ 2);
        }
    }

    public double getDistance() {
        return distance;
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

    public void setEngine(double e) {
        e = engine;
        engine = (int)(Math.random() * 5);
    }

    public double getEngine() {
        return engine;
    }

    public void setTire(double t) {
        t = tire;
        tire = (int)(Math.random() * 5);
    }

    public double getTire() {
        return tire;
    }

    public void setSpeed(double s) {
        s = speed;
        speed = distance/time;
    }

    public double getSpeed() {
        return speed;
    }

    public void setName(String n) {
        n = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String padding = "";
             if(name.length() == 1) padding +="           ";
        else if(name.length() == 2) padding +="          ";
        else if(name.length() == 3) padding +="         ";
        else if(name.length() == 4) padding +="      ";
        else if(name.length() == 5) padding +="   ";
        else if(name.length() == 6) padding +=" ";
        return name +padding + "          " + speed + "               " + distance+"                    "+pathString;
    }


    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.drawImage(img, null, x, y);
    }
}
