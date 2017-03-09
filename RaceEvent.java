import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;


public class RaceEvent
{
    private ArrayList<Car> contestants;
    private RaceTrack track;
    //private RaceTrack track;

    public RaceEvent(String usrName, Integer numOpp)
    {
        innitialize(usrName, numOpp);
    }

    public void innitialize(String usrName, Integer numOpp)
    {
        track = new RaceTrack();
        Car userCar = new Car(track.getRoute(1), usrName);
        contestants = new ArrayList<Car>();
        contestants.add(userCar);
        for(int i = 2; i <= numOpp; i++)
        {
            contestants.add(new Car(track.getRoute(3), "Racer "+i));
        }
    }

    public boolean race()
    {
        return false;
    }

    public String generatePath()
    {
        String path = "";
        ArrayList<String> allChecks = new ArrayList<String>();
        allChecks.add("A");
        allChecks.add("B");
        allChecks.add("C");
        allChecks.add("D");
        while(allChecks.size() != 0)
        {
            int index = (new Random()).nextInt(allChecks.size());
            path += allChecks.remove(index);
        }
        return path;
    }

    public String getContestants()
    {
        String view = "Racer          Speed          Distance\n-------------------------------------------\n";
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
