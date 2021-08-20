package game.vehicle;

import game.utils.VehicleException;

public abstract class Vehicle{
    public static int serial_number = 0;
    protected final int id;
    private double currentSpeed = 0;
    public double getCurrentSpeed(){ return currentSpeed;}

    protected Vehicle(){
        serial_number++;
        id = serial_number;
    }

    protected final void accelerateCurrentSpeed(double i) throws VehicleException{
        if(currentSpeed + i < 0){
            throw new VehicleException("Negativ currentSpeed");
        }
        else{
            currentSpeed += i;
        }
    }

    public abstract void accelerate(double amount);
}