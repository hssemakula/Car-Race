import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import javax.swing.JPanel;

public class RaceTrack implements Drawable{

    int amtCheckpoints;
    ArrayList<Drawable> checkPoints;
    ArrayDeque<Drawable>rt1, rt2, rt3, rt4;

    public RaceTrack(){

        amtCheckpoints = 4;
        checkPoints = new ArrayList<Drawable>();

        for(int i = 1; i <= amtCheckpoints; i++)
            checkPoints.add(new Checkpoint((char)(64 + i)));

        rt1 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt1);

        rt2 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt2);

        rt3 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt3);

        rt4 = new ArrayDeque<Drawable>();
        generateRaceSequence(rt4);

    }

    public void draw(Graphics2D g2){

        for(Drawable cp: checkPoints)
            cp.draw(g2);
    }

    private void generateRaceSequence(ArrayDeque<Drawable> rs){

        for(int i = 1; i <= amtCheckpoints; i++){

            int aux = (int)(Math.random() * 4);

            if(rs.isEmpty()){
                rs.add(checkPoints.get(aux));
            }
            else{
                if(rs.contains(checkPoints.get(aux))){
                    i--;
                }
                else{
                    rs.add(checkPoints.get(aux));
                }
            }
        }
		/*
		System.out.print(((Checkpoint)(rs.pop())).getID());
		System.out.print(((Checkpoint)(rs.pop())).getID());
		System.out.print(((Checkpoint)(rs.pop())).getID());
		System.out.println(((Checkpoint)(rs.pop())).getID());*/
    }

    private double calcDistanceTwoPoints(Checkpoint cp1, Checkpoint cp2){

        double distance;
        distance = Math.sqrt((Math.pow(cp2.getYValue() - cp1.getYValue(), 2)) +
                (Math.pow(cp2.getXValue() - cp1.getXValue(), 2)));

        return distance;
    }

    public double calcDistanceTotal(int raceTrackID){

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
        }
        double totalDistance = 0;

        totalDistance = calcDistanceTwoPoints((Checkpoint)aux[0], (Checkpoint)aux[1])
                + calcDistanceTwoPoints((Checkpoint)aux[1], (Checkpoint)aux[2])
                + calcDistanceTwoPoints((Checkpoint)aux[2], (Checkpoint)aux[3])
                + calcDistanceTwoPoints((Checkpoint)aux[3], (Checkpoint)aux[0]);

        return totalDistance;
    }

    public ArrayDeque<Drawable> getRoute(int n){

        switch(n){

            case 1: return rt1;
            case 2: return rt2;
            case 3: return rt3;
            case 4: return rt4;
        }
        return null;

    }
    public String getSequence(int raceTrackID){

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
        }

        String str = "";

        str = "" + ((Checkpoint)aux[0]).getID()
                + ((Checkpoint)aux[1]).getID()
                + ((Checkpoint)aux[2]).getID()
                + ((Checkpoint)aux[3]).getID();
        return str;
    }
}
