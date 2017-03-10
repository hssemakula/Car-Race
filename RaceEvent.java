import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;


public class RaceEvent
{
    private ArrayList<Car> contestants;
    private RaceTrack track;
    private long timeStart;
    private double[] speeds;

    public RaceEvent(String usrName, Integer numOpp, Color usrColor)
    {
        innitialize(usrName, numOpp, usrColor);
    }

    public void innitialize(String usrName, Integer numOpp, Color usrColor)
    {
        track = new RaceTrack();
        Car userCar = new Car(track.getRoute(1), usrName, true, usrColor);
        contestants = new ArrayList<Car>();
        contestants.add(userCar);
        for(int i = 2; i <= numOpp; i++)
        {
            contestants.add(new Car(track.getRoute(i), "Racer "+i, false, usrColor));
        }
    }

    public boolean race()
    {
      int moveCount = 0;
      for(Car c: contestants) 
      {
        if(c.move()) {
         moveCount++;
         c.setTime(timeStart);
        }
      }  
        return moveCount > 0;
    }
    
    public double[] calculateSpeeds(){
     
     speeds = new double[contestants.size()];
     int i = 0;
     for(Car c: contestants){
      
      c.setAverageSpeed();
      speeds[i] = c.getAverageSpeed();
      System.out.println(c.getName() + ": " + c.getAverageSpeed() + "");
      i++;
     }
     return speeds;
    }

    public String getContestants()
    {
        String view = "Racer          Speed          Distance          Path\n-------------------------------------------------------------\n";
        for(Car c: contestants) view += c.toString() + "\n";
        return view;
    }

    public Car getWinner(){ 
     
     double[] aux = calculateSpeeds();
     
     double max = aux[0];
     int indexMax = 0;
     
     for(int i = 1; i < aux.length; i++){
      if(aux[i] > max){
       max = aux[i];
       indexMax = i;
      }
     }
     return contestants.get(indexMax); 
     
    }

    public void draw(Graphics2D g2)
    {
        track.draw(g2);
        for(Car c: contestants) c.draw(g2);
    }
    
    public void setStartTime(long start){
     
     timeStart = start;
    }
}
