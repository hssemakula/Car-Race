import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;


public class RaceEvent
{
    private ArrayList<Car> contestants;
    private RaceTrack track;

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
        if(c.move()) moveCount++;
      }  
        return moveCount > 0;
    }

    public String getContestants()
    {
        String view = "Racer          Speed          Distance          Path\n-------------------------------------------------------------\n";
        for(Car c: contestants) view += c.toString() + "\n";
        return view;
    }

    public String getWinner(){ return""; }

    public void draw(Graphics2D g2)
    {
        track.draw(g2);
        for(Car c: contestants) c.draw(g2);
    }
}
