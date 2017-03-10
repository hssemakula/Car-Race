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

    ArrayDeque<Drawable> path;

    public Car(ArrayDeque<Drawable> path, String name) {
        this.path = path;
        this.name = name;
        setPathString(path);

        x = ((Checkpoint)path.peek()).getXValue();
        y = ((Checkpoint)path.pop()).getYValue() + 67;
        getNextCheckpoint();

        try {
            img = ImageIO.read(new File("carclipart.png"));
            Color carColor = new Color((int)(Math.random() * 25.6)*10, (int)(Math.random() * 25.6)*10, (int)(Math.random() * 25.6)*10);
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
        } catch(Exception e) {
            System.out.println("Image icon not found");
        }

        long startTime = System.currentTimeMillis();
    }

    public void getNextCheckpoint(){
        newX = ((Checkpoint)path.peek()).getXValue();
        newY = ((Checkpoint)path.pop()).getYValue() + 67;
    }

      public boolean move(){//(Checkpoint checkpoint) {

        int x1 = x;
        int y1 = y;

        boolean isNegative = false;

        if ((newX - x) < 0) 
            isNegative = true;
        
        int displacement = (int) (Math.random() * 5);
        double slope;
        double yIntercept;
        
        if (x == newX && y == newY) {
         
         if(path.isEmpty())
          return false;
         else{
          this.getNextCheckpoint();
         }
         
            
        } else if (x != newX || y != newY) {
            
            try{
             slope = ((((double)newY - y) / ((double)newX - x)));
            }
            catch(Exception e){
             slope = ((((double)newY - y) / 0.00000001));//This is in case flags are in the same vertical line which can happen
                                                            //Both x-coordinates would be the same, thus diving by 0 would be an error
            }
            
            yIntercept = newY - (slope * newX);
            
            if (isNegative) x -= displacement;
            
            else x += displacement;
            
            y = (int) ((slope * x) + yIntercept);
            updateDistance(x, x1, y, y1);
            return true;
        } else if (Math.abs(x - newX) < 1 || Math.abs(y - newY) < 1) {
            y = newY;
            x = newX;
            updateDistance(x, x1, y, y1);
            return true;
        }
        //if ((newX == (Checkpoint)path.getLast()) && (newY == (Checkpoint)path.getLast())) {
          //  getTime();
            //return false; //This method ONLY returns false when the new x and new y are equal to the final x and final y so
            //this false should have a condition.
        //}
        return false;
    }

    //This method will give you false time because you choose what time to start and stop. however it can be used as a "helper method"
    //for the one below
    public void setTime(long t, long s) {
        s = start;
        t = time;
        start = System.currentTimeMillis();
        time = System.currentTimeMillis() - start;
    }

    //At any time the time since this car started moving is System.currentTimeMillis() - start; which is what you have above
    //but your start variable should be innitialized in the constructor i.e when the car "REALY" starts moving.
    //so if you call the above method with in this method like settime(System.currentTimeMillis(), start(one innitialized in constructor))
    //it should produce the right time, any time.  .....This method should also be called when the car reaches it's final checkpoint
    public long getTime() {
        //setTime(System.currentTimeMillis());
        return time;
    }

    /* So this method should take in the old coordinates and the ones just calculated and using that formula that you have
     * in the else if  it should then calculate a certain value and should add it to the current value of distance.
     * so in the move method, the first thing you need to store are original values of x and y. then EXACTLY after you
     * calculate the new x and y before the return, call this method with the four coordinates. this will be very efficiaent I promise.
     */
    public void updateDistance(int x1, int x2, int y1, int y2) 
    {
      distance += Math.sqrt(((x2 - x1) ^ 2) + ((y2 - y1) ^ 2));
    }

    public String getDistance() {
        return String.format("%4.2g",distance);
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

    public void setSpeed() {
        speed = distance/time;
    }

    public double getSpeed() {
        return speed;
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
        return name +padding + "          " + speed + "               " + getDistance()+"                    "+pathString;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.drawImage(img, null, x, y);
    }
}
