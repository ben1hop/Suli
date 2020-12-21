import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class szinkep {

    class RGB{
        int red;
        int green;
        int blue;
        RGB(int r , int g, int b){
            red = r;
            green = g;
            blue = b;
        }
    }
    int line_counter = 50*50;
    RGB[] tarolo = new RGB[line_counter];

    // Input
    File input = new File("kep.txt");
    BufferedReader reader = null; // new BufferedReader(new FileReader(input))

    // Output
    File output = new File("keretes.txt");
    BufferedWriter writer = null;

    public szinkep(){
        first();
        second();
        third();
        fourth();
        fifth();
        sixth();
        seventh();
    }
    private void first() {
        try{
            reader = new BufferedReader(new FileReader(input));
            String line;
            int tarolo_counter= 0;
            while((line = reader.readLine()) != null){
                String[] input = line.split(" ");
                tarolo[tarolo_counter] = new RGB(Integer.parseInt(input[0]) , Integer.parseInt(input[1]) , Integer.parseInt(input[2]));
                tarolo_counter++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("1. feladat.\nAdatok beolvasva.");
    }

    private void second() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Kerem adjon meg egy RGB kodot.");
        RGB in = new RGB(scan.nextInt(),scan.nextInt(),scan.nextInt());

        int i = 0;
        while(i < line_counter && !compare(in, tarolo[i])){
            i++;
        }
        System.out.println("2. feladat.");
        if(i == line_counter){
            System.out.print("Nincs ilyen szin a fajlban.");
        }else{
            System.out.print("Megtalalva a "+(i+1)+"indexen");
        }
    }

    boolean compare(RGB a , RGB b){
        return (a.red == b.red && a.green == b.green && a.blue == b.blue);
    }

    private void third() {
        // 35. sor 8. elem hanyszor a 35. sorban es 8. oszlopban
        // 1758. elemet keressuk
        // 0-49 elso sor
        // 50-99 masodik stb...
        //...
        // 35x50 = 1750 -> 35. sor 1750-1799
        // az elso elem a 7. aztan +50 minden alkalommal
        RGB keresett = tarolo[1757];
        int sorban = 1;
        int oszlopban = 1;
        // Count sor
        for(int i = 1750 ; i < 1800 ; i++) {
            if(compare(keresett , tarolo[i]) && i != 1757){
                sorban++;
            }
        }
        // count oszlop
        for(int j = 7; j < line_counter ; j+=50){
            if(compare(keresett , tarolo[j])  && j != 1757){
                oszlopban++;
            }
        }
        System.out.println("3. feladat.");
        System.out.println("Sorban: "+sorban+" Oszlopban: "+oszlopban);

    }
    String max(int a , int b , int c){
        if(a > b && a > c){
            return "Red";
        }
        if(b > a && b > c){
            return "Green";
        }
        if(c > b && c > a){
            return "Blue";
        }
        return "Nincs";
    }

    private void fourth() {
        System.out.println("4. feladat.");
        int red_counter = 0;
        int green_counter = 0;
        int blue_counter = 0;

        for(int i = 0 ; i < line_counter ; i++){
            if(compare(tarolo[i] , new RGB(255, 0,0))){
                red_counter++;
            }
            if(compare(tarolo[i] , new RGB(0, 255,0))){
                green_counter++;
            }
            if(compare(tarolo[i] , new RGB(0, 0,255))){
                blue_counter++;
            }
        }
        System.out.print(max(red_counter,green_counter,blue_counter)+"\n");
    }

    private void fifth() {
        // 3px szeles fekete keret a kepmeret megtartasaval
        // elso es utolso 3 sor fekete
        // plusz soronkent az elso 3 es utolso 3 -> 0-2 && 47-49 +5*50

        // top and bottom line
        for(int i = 0; i < 2500 ; i++){
            if(i < 150 || i > 2349){
                tarolo[i] = new RGB(0,0,0);
            }
        }
        // left side
        for(int j = 0 ; j < 2500 ; j+=50){
            for(int k = 0; k < 3 ; k++){
                tarolo[j+k] = new RGB(0,0,0);
            }
        }
        // right side
        for(int j = 0 ; j < 2500 ; j+=50){
            for(int k = 47; k < 50 ; k++){
                tarolo[j+k] = new RGB(0,0,0);
            }
        }
        System.out.print("5. feladat\nA keret kesz.\n");
    }

    private void sixth() {
        try{
            writer = new BufferedWriter(new FileWriter(output));
            for(int i = 0; i < line_counter; i++){
                writer.write(tarolo[i].red+" "+tarolo[i].green+" "+tarolo[i].blue);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("6.feladat\nA kiiratas kesz.\n");
    }

    private void seventh() {
        // keres sarga teglalap
        int k_oszlop = 0;
        int k_sor = 0;

        int top_left  = 0;
        int top
        int bottom_right =0;
        boolean first = false;
        for(int i = 0; i < line_counter;i++){
            if(compare(tarolo[i], new RGB(255,255,0))) {
                if(first){
                    top_left =
                }
            }

        }
    }

}
