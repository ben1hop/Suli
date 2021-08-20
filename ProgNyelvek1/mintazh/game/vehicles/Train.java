package game.vehicle;

import game.utils.VehicleException;

public class Train extends Vehicle{
    @Override
    public void accelerate(double amount){
        if(amount < 0){
            try{
                accelerateCurrentSpeed(amount/10);
            }
            catch(VehicleException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                accelerateCurrentSpeed(amount);
            }
            catch(VehicleException e){
                e.printStackTrace();
            }
        }
    }
}