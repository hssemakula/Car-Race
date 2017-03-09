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
    public boolean move() {
        
    	System.out.println("x"+x+"y"+y);
        boolean isNegative = false;

        if ((newX - x) < 0) isNegative = true;
        int velocity = (int)(Math.random() * 5);
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
        return false;
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
        return name +padding + "          " + speed + "               " + distance+"      "+pathString;
    }


    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.drawImage(img, null, x, y);
    }
}
