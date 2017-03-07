//Hillary Ssemakula
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;


public class RaceEvent
{
  private ArrayList<Car> contestants;
  private RaceTrack track;
  
  public RaceEvent(String usrName, Integer numOpp)
  {
    innitialize(usrName, numOpp);
    
  } 
  
  public void innitialize(String usrName, Integer numOpp)
  {
    Car userCar = new Car(generatePath(), usrName);
    contestants = new ArrayList<Car>();
    contestants.add(userCar);
    for(int i = 2; i <= numOpp; i++)
    {
      contestants.add(new Car(generatePath(), "Racer "+i));
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
    String view = "";
    for(Car c: contestants) view += c.toString() + "\n";
    return view;
  }
  
  public void draw(Graphics g)
  {
    track.draw(g);
    for(Car c: contestants) c.draw(g);
  }  
}  
