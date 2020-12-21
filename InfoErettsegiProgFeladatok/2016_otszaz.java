package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    public Application(){
        elso();
        masodik();
        harmadik();
        negyedik();
        otodik();
        hatodik();
        hetedik();
        nyolcadik();
    }
    // Scanner
    Scanner scanner = null;
    int input_sorszam;
    String input_termeknev;
    int input_darabszam;


    // File paths
    File input = new File("penztar.txt");
    File output = new File("");

    // FileStream
    BufferedWriter writer = null;
    BufferedReader reader = null;

    // Reading array
    String[] input_array = new String[1000];
    int line_counter = 0;



    // Converts input array into useful data type ------------------------------------------------------------

    class Data {
        int vasarolt_tetel;
        int osszeg;
        ArrayList<String> list_of_items;

        Data(){
            vasarolt_tetel = 0;
            osszeg = 0;
            list_of_items = new ArrayList<>();
        }

        void AddElement(String line){
            list_of_items.add(line);
            vasarolt_tetel++;

            if(this.vasarolt_tetel < 4){
                switch(this.vasarolt_tetel){
                    case 1:
                        osszeg += 500;
                        break;
                    case 2:
                        osszeg += 450;
                        break;
                    case 3:
                        osszeg += 400;
                        break;
                    default:
                        break;
                }
            }else{
                osszeg += 400;
            }

        }
    }

    // Useful array
    Data[] adatok = new Data[500]; // ha mindenki egy termeket vesz max ennyi vasarlo lehet
    int adat_counter = 0;

    void convert(){
        for(int i = 0; i < line_counter ;i++){
            adatok[i] = new Data();
            adat_counter++;
            int j = i+1;
            while (j < line_counter && input_array[j] != "F"){
                adatok[i].AddElement(input_array[j]);
                j++;
            }
            i = j;
        }
    }


    // TASKS ---------------------------------------------------------------------------------------------------

    void elso() {
        try{
            reader = new BufferedReader(new FileReader(input));

            String line;
            while((line = reader.readLine()) != null){
                input_array[line_counter] = line;
                line_counter++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void masodik() {
        // hanyan fizettek a penztarnal
        System.out.println("2. feldat: \nA penztarnal "+adat_counter+"-en fizettek.");
    }

    void harmadik() {
        // Elso vasarlo vasarolt tetel szama
        System.out.println("3. feldat: \nAz elso vasarlonak "+adatok[0].vasarolt_tetel+" tetel volt a kosaraban.");
    }

    void negyedik() {
        //Kérje be a felhasználótól egy vásárlás sorszámát,
        // egy árucikk nevét és egy darabszámot! A következő három feladat megoldásánál ezeket használja fel!
        System.out.println("Adja meg egy vásárlás sorszámát!");
        input_sorszam = scanner.nextInt();
        System.out.println("Adja meg egy árucikk nevét!");
        input_termeknev = scanner.nextLine();
        System.out.println("Adja meg a vásárolt darabszámot!");
        input_darabszam = scanner.nextInt();

    }

    void otodik() {
        //Határozza meg, hogy a bekért árucikkből
        // a.melyik vásárláskor vettek először, és melyiknél utoljára!
        // b.összesen hány alkalommal vásároltak!

        int elso_vasarlas=-1;
        int utolso_vasarlas=-1;
        int hany_alkalom=0;

        for(int i = 0; i < adat_counter ; i++){
            for(int j = 0 ; j < adatok[i].vasarolt_tetel ; j++){
                if(input_termeknev == adatok[i].list_of_items.get(j)){
                    if(elso_vasarlas == -1){
                        elso_vasarlas = i+1;
                    }
                    else{
                        utolso_vasarlas = i+1;
                    }
                    hany_alkalom++;
                }
            }
        }
        System.out.println("5. feladat: \nElso alkalom: "+elso_vasarlas+"\nUtolso alkalom: "+utolso_vasarlas+"\nHany alkalom: "+hany_alkalom);

    }
    void hatodik() {
        System.out.println("6. feldat: "+input_darabszam+" darab vetelekor fizetendo: "+ertek(input_darabszam));
    }

    void hetedik() {

    }

    void nyolcadik() {

    }

    int ertek(int darabszam){
        int osszeg = 0;

        int i = 0;
        while(i < darabszam){
            if(i < 4){
                switch(i){
                    case 1:
                        osszeg += 500;
                        break;
                    case 2:
                        osszeg += 450;
                        break;
                    case 3:
                        osszeg += 400;
                        break;
                    default:
                        break;
                }
            }else{
                osszeg += 400;
            }
            i++;
        }
        return osszeg;
    }
}
