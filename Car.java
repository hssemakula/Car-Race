import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Sarah on 3/6/17.
 */
public class Car implements Drawable {

    private int x;
    private int y;
    private String path;
    private Queue<String> pathQueue;
    private double speed;
    private double distance;
    private long time;
    private double engine;
    private double tire;
    private Color color;
    private String name;
    private BufferedImage img;

    double[] values = {4.7, 9.0, 3.5, 7.6, 10.0, 1.5, 2.8, 5.5, 6.0, 8.8};
    private final Random random = new Random();

    public Car(Queue<String> pathQueue) {
        this.pathQueue = pathQueue;
    }

    public void init() {
        try {
            URL url = new URL("carclipart.png");
            img = ImageIO.read(url);
        } catch (IOException e) {
        }
    }

    public boolean move(CheckPoint checkPoint) {
        int newX = checkPoint.getX();
        int newY = checkPoint.getY();
        boolean isNegative = false;

        if ((newX - x) < 0) isNegative = true;
        //increment = random decrease in tires and engine i.e increment = int(tires - (Math.random() * 3) + engine - (Math.random() * 3));
        int increment = (int) (Math.random() * 5);
        double gradient;
        double yIntercept;

        if (x == newX && y == newY) {
            return false;
        } else if (x != newX || y != newY) {
            gradient = ((newY - y) / newX - x);
            yIntercept = newY - (gradient * newX);
            if (isNegative) x -= increment;
            else x += increment;
            y = (int) ((gradient * x) + yIntercept);

            return true;
        } else if (Math.abs(x - newX) < 1 || Math.abs(y - newY) < 1) {
            y = newY;
            x = newX;
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawImage(img, x, y, null);
    }

    public void setTime(long t) {
        t = time;
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }
    
    @kyekiriwo
    /* @kyekiriwo's thoughts on the distance: The distance of the car is the distance moved from the first checkpoint to the last.
     * which is the summation of all individual movements in the move method. 
     * Example if a car started at coordnate (250, 250) and moves to (259, 310), it's distance is d = Math.sqrt((310-250)^2 + (259-250)^2)
     * which is the length of that segment. if it moves from (259, 310) and moves to (270, 350), then its the old d1 + the new calculation of d
     * D = d1 + d2. This is the true distance. But I made this suggestion because the rank of the car on the scoreboard will be dertimined by its distance covered 
     * so far in the race until the race is done. thats when the time will be consdered.
     */
    public void setDistance(double d) {
        d = distance;
        distance = values[random.nextInt(values.length)];
    }

    public double getDistance() {
        return distance;
    }
    
    @kyekiriwo
    /* @kyekiriwo. When the method receives the String, the String contains 4 letters. set the path instance variable
     * first. then split the string into an array using the .split("") method in the String Class. Then add the individual Strings
     * to the pathQueue.
     */
    public void setPath(String p) {
        // this method is throwing me for a loop, can I please ask for some help?
    }

    public String getPath() {
        return path;
    }

    public void setEngine(double e) {
        e = engine;
        engine = values[random.nextInt(values.length)];
    }

    public double getEngine() {
        return engine;
    }

    public void setTire(double t) {
        t = tire;
        tire = values[random.nextInt(values.length)];
    }

    public double getTire() {
        return tire;
    }
    
    @kyekiriwo
    //speed = distance/time
    public void setSpeed(double s) {
        s = speed;
        speed = tire + engine;
        //return distance/time;
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
        return "Car's name is " + name + ". " + name + "'s speed is " + speed + ", and " + name + "'s distance traveled is " + distance;
    }


}
