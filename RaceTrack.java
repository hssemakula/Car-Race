/* Created by Rodrigo Choque Cardenas
 * 
 * The next class implements the race track where our race will take place
 *  
 * The race is in charge of creating a certain amount of checkpoints -stored on an array list. Once these are created, 
 * we use them to build 12 deques. These deques will be filled with references to the checkpoint objects, and they 
 * will be filled with random checkpoint sequences. Each deque will be assigned to each car object, 
 * so the car will have a "map" of what route it must follow.
 * 
 * The class implements the Drawable interface so it can draw the checkpoint on its array list.
 */

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class RaceTrack implements Drawable{

	//atributes
    int amtCheckpoints;
    ArrayList<Drawable> checkPoints;
    ArrayDeque<Drawable>rt1, rt2, rt3, rt4, rt5, rt6, rt7, rt8, rt9, rt10, rt11, rt12; //each deque will have different sequence of checkpoitns

    //constructor
    public RaceTrack(){

        amtCheckpoints = 4; //this could be change to include more chekpoints
        checkPoints = new ArrayList<Drawable>();

        //checkpoints are created
        for(int i = 1; i <= amtCheckpoints; i++)
            checkPoints.add(new Checkpoint((char)(64 + i)));

        //each deque is filled with a sequence of references to the checkpoints
        rt1 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt1);

        rt2 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt2);

        rt3 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt3);

        rt4 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt4);
        
        rt5 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt5);

        rt6 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt6);

        rt7 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt7);

        rt8 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt8);
        
        rt9 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt9);

        rt10 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt10);

        rt11 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt11);

        rt12 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt12);

    }

    //method the uses a Graphics2D object to draw each checkpoint in the array list
    public void draw(Graphics2D g2){

        for(Drawable cp: checkPoints)
            cp.draw(g2);
    }

    //this method generates a random sequence for each deque
    private void generateRaceSequence(ArrayDeque<Drawable> rs){

        for(int i = 1; i <= amtCheckpoints; i++){

            int aux = (int)(Math.random() * 4);

            //pushes first checkpoint into deque
            if(rs.isEmpty()){
                rs.add(checkPoints.get(aux));
            }
            else{
            	
            	//the if statement makes sure there is no repeated checkpoint in the deque
                if(rs.contains(checkPoints.get(aux))){
                    i--;
                }
                else{
                    rs.add(checkPoints.get(aux));
                }
            }
        }
    }

    //private method and calculates distance between two checkpoints
    private double calcDistanceTwoPoints(Checkpoint cp1, Checkpoint cp2){

        double distance;
        //we use pythagorean theorem
        distance = Math.sqrt((Math.pow(cp2.getYValue() - cp1.getYValue(), 2)) +
                (Math.pow(cp2.getXValue() - cp1.getXValue(), 2)));

        return distance;
    }

    //method that returns total disctance between all checkpoints sequence
    public double calcDistanceTotal(int raceTrackID){

    	//auxiliary array that will help us access easily to the elements
    	//of each deque without causing any unintentional deletion on the deque
        Drawable aux[] = new Drawable[4];

        switch(raceTrackID){

            case 1: rt1.toArray(aux);
                break;
            case 2: rt2.toArray(aux);
                break;
            case 3: rt3.toArray(aux);
                break;
            case 4: rt4.toArray(aux);
                break;
            case 5: rt5.toArray(aux);
            	break;
	        case 6: rt6.toArray(aux);
	            break;
	        case 7: rt7.toArray(aux);
	            break;
	        case 8: rt8.toArray(aux);
	            break;
	        case 9: rt9.toArray(aux);
	        	break;
		    case 10: rt10.toArray(aux);
		        break;
		    case 11: rt11.toArray(aux);
		        break;
		    case 12: rt12.toArray(aux);
		        break;
        }
        double totalDistance = 0;

        //for this game, we calculate the distance between the four checkpoints
        totalDistance = calcDistanceTwoPoints((Checkpoint)aux[0], (Checkpoint)aux[1])
                + calcDistanceTwoPoints((Checkpoint)aux[1], (Checkpoint)aux[2])
                + calcDistanceTwoPoints((Checkpoint)aux[2], (Checkpoint)aux[3])
                + calcDistanceTwoPoints((Checkpoint)aux[3], (Checkpoint)aux[0]);

        return totalDistance;
    }

    //method that returns the requested deque. will be useful when assigned car to a deque.
    public ArrayDeque<Drawable> getRoute(int n){

        switch(n){

            case 1: return rt1;
            case 2: return rt2;
            case 3: return rt3;
            case 4: return rt4;
            case 5: return rt5;
            case 6: return rt6;
            case 7: return rt7;
            case 8: return rt8;
            case 9: return rt9;
            case 10: return rt10;
            case 11: return rt11;
            case 12: return rt12;
        }
        return null;

    }
    
    //method that returns a string containing the letters of the sequence of the 
    //requested deque
    public String getSequence(int raceTrackID){

    	//auxiliary array that will help us access easily to the elements
    	//of each deque without causing any unintentional deletion on the deque
        Drawable aux[] = new Drawable[4];

        switch(raceTrackID){

            case 1: rt1.toArray(aux);
                break;
            case 2: rt2.toArray(aux);
                break;
            case 3: rt3.toArray(aux);
                break;
            case 4: rt4.toArray(aux);
                break;
            case 5: rt5.toArray(aux);
            	break;
	        case 6: rt6.toArray(aux);
	            break;
	        case 7: rt7.toArray(aux);
	            break;
	        case 8: rt8.toArray(aux);
	            break;
	        case 9: rt9.toArray(aux);
	        	break;
		    case 10: rt10.toArray(aux);
		        break;
		    case 11: rt11.toArray(aux);
		        break;
		    case 12: rt12.toArray(aux);
		        break;
        }

        String str = "";

        str = "" + ((Checkpoint)aux[0]).getID()
                + ((Checkpoint)aux[1]).getID()
                + ((Checkpoint)aux[2]).getID()
                + ((Checkpoint)aux[3]).getID();
        return str;
    }
}
