package game;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import game.vehicle.Car;

public class Player{
    public String name;
    public String ip;
    public int money;
    public ArrayList<Car> autok;

    public Player(String name, String ip , int money){
        if(name == null || ip == null || ip.length() > 0 || ip.trim().isEmpty() || money < 0){
            throw new IllegalArgumentException("Hibas player argumentumok.");
        }else{
            this.name = name;
            this.ip = ip;
            this.money = money;
            autok = new ArrayList<>();
        }
    }
}