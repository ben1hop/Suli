import game.vehicle.*;
import java.io.*;

import game.vehicle.Train;
import game.vehicle.Car;
import game.Player;

class Main{

    public static Player loadPlayerFromFile(String playerName){
        File input = new File("users/" + playerName + ".txt");
        String[] data = new String[2];
        try (BufferedReader bf = new BufferedReader(new FileReader(input))){
            String line = bf.readLine();
            data = line.split(" ");
            return new Player(playerName, data[0], Integer.parseInt(data[1]));
        } catch (IOException e) {
            System.out.println("IO error occured: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args){
        Player Daniel = loadPlayerFromFile("Daniel");
        Player Peter = loadPlayerFromFile("peter");
        Player Richard = loadPlayerFromFile("Richard");
        Player Tamas = loadPlayerFromFile("Tamas");
        Player Zorror = loadPlayerFromFile("Zorror");

        Car auto1 = new Car(500 , 120);
        Car auto2 = new Car(300 , 150);
        Car auto3 = new Car(400 , 120);
        Car auto4 = new Car(1000 , 190);
        Car auto5 = new Car(800, 175);
    }
}