/* Hillary Ssemakula, Rodrigo Choque Cardenas
 * This class is the main controlling class for the race. Controls movement of cars with race method.
 * returns the statistics of the race, declares winners and draws cars, checkpoints and the track on a panel
 * 
 */ 
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;


public class RaceEvent
{
  private ArrayList<Car> contestants; //arrayList variable to store all cars that will participate in race
  private RaceTrack track;  //Race track variable that stores race track where race takes place.
  private long timeStart;   //Rodrigo
  private double[] speeds; //Rodrigo
  
  /* Constructor: @params String usrName, Integer numOpp, Color usrColor
   * parameters needed by innitialize method. calls the innitialize method
   */ 
  public RaceEvent(String usrName, Integer numOpp, Color usrColor)
  {
    innitialize(usrName, numOpp, usrColor);
  }
  
  /* Method innitialize. @params String usrName, Integer numOpp, Color usrColor
   * Using the gven parameters which are the number of participants, color and name of user's car
   * the user's car is created and other cars upto the number specified are created, all instance variables re innitialized
   */ 
  public void innitialize(String usrName, Integer numOpp, Color usrColor)
  {
    track = new RaceTrack(); //track object created
    Car userCar = new Car(track.getRoute(1), usrName, true, usrColor); //user's car created and given path, also marked as user's car
    contestants = new ArrayList<Car>();
    contestants.add(userCar); //user's car added to contestants arraylist
    for(int i = 2; i <= numOpp; i++)
    {
      //all other cars created, given paths and added to contestants arraylist
      contestants.add(new Car(track.getRoute(i), "Racer "+i, false, usrColor));
    }
  }
  
  /*Method race. calls the move method for each car in the contestants arraylist.
   * Creates moveCount variable that is incremented everytime a car's move method returns true
   * Returns true if moveCount is greater than 0(i.e if atleast one car moves), false if no car
   * moves i.e moveCount < 0
   */ 
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
  
  //Rodrigo
  public double[] calculateSpeeds(){
    
    speeds = new double[contestants.size()];
    int i = 0;
    for(Car c: contestants){
      
      c.setSpeed();
      speeds[i] = c.getSpeed();
      i++;
    }
    return speeds;
  }
  
  /* Method getContestants. This method sorts all cars in the contestants arraylist in descending order
   * by speed. It makes a string by concatenating all the toString methods of all cars in the arraylist
   * adds a heading to it hence creating a sorted list of all participating cars and returns that string
   * 
   */ 
  public String getContestants()
  {
    String view = "Racer                Speed                 Distance                Path"
      +"\n----------------------------------------------------------------------------\n";
    ArrayList<Car> newArrayList = new ArrayList<Car>(); //arraylist where sorted cars are placed
    //cars are sorted by speed
    while(contestants.size() > 0)
    {
      Car maxSpeedCar = contestants.get(0); //first car is marked
      int index = 0; //first index is marked
      for(int i = 0; i < contestants.size(); i++)
      {
        //if any cars speed is greater that the marked car's speed, that index is stored in index
        if(maxSpeedCar.getSpeed() < contestants.get(i).getSpeed()) index = i;
      }
      newArrayList.add(contestants.remove(index));
    }
    
    contestants = newArrayList;
    newArrayList = null;
    
    for(Car c: contestants) view += c.toString() + "\n";
    return view;
  }
  
  //Rodrigo
  public String getWinner(){ 
    
    double[] aux = calculateSpeeds();
    
    double max = aux[0];
    int indexMax = 0;
    
    for(int i = 1; i < aux.length; i++){
      if(aux[i] > max){
        max = aux[i];
        indexMax = i;
      }
    }
    Car c = contestants.get(indexMax); 
    return "Name: "+c.getName()+"\nSpeed: "+c.getSpeedString()+"\nDistance: "+c.getDistance()+"\n";
    
  }
  
  public void draw(Graphics2D g2)
  {
    track.draw(g2);
    for(Car c: contestants) c.draw(g2);
  }
  
  //Rodrigo
  public void setStartTime(long start){
    
    timeStart = start;
  }
}
