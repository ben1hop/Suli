package game.vehicle;

import java.lang.StringBuilder;
import game.utils.VehicleException;

public class Car extends Vehicle{
    private final int ar;
    private final int sebesseg;

    public Car(int ar , int seb){
        this.ar = ar;
        sebesseg = seb;
    }

    public int getAr() { return ar;}

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Id: "+id);
        sb.append("\nAr: " +ar);
        sb.append("\nMaxSebesseg: "+sebesseg);
        return sb.toString();
    }


    @Override
    public void accelerate(double amount ) {
        if(getCurrentSpeed() + amount < sebesseg){
            try{
                accelerateCurrentSpeed(amount);
            }
            catch(VehicleException e){
                e.printStackTrace();
            }
        }
    }
}