package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class tesztverseny {
    class Data{
        String id;
        String valaszok; // A/B/C/D vagy X ha nemvalaszolt
        Data(String line){
            id = line.split(" ")[0];
            valaszok = line.split(" ")[1];
        }
    }

    String helyes_valasz;

    Data[] adatok = new Data[500];
    int line_counter = 0;

    File output = new File("pontok.txt");
    File input = new File("valaszok.txt");

    Scanner scan = new Scanner(System.in);

    String bekert_id;
    int bekert_id_index;

    tesztverseny(){
        elso_feladat();
        masodik_feladat();
        harmadik_feladat();
        negyedik_feladat();
        otodik_feladat();
        hatodik_feladat();
        hetedik_feladat();
    }


    void elso_feladat(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(input));

            helyes_valasz = br.readLine();

            String line;
            while((line = br.readLine()) != null){
                adatok[line_counter] = new Data(line);
                line_counter++;
            }
        }catch(IOException err){
            System.out.println("Input error.");
        }
    }
    void masodik_feladat(){
        // hany versenyzo vett reszt
        // versenyzo = sorszam
        System.out.println("A versenyen "+line_counter+" versenyzo vett reszt.");
    }
    void harmadik_feladat(){
        // kerjen be id-t irja ki a valaszat
        System.out.println("Kerem adjon meg egy ID-t: ");
        bekert_id = scan.nextLine();
        int i = 0;
        while(i < line_counter && !adatok[i].id.equals(bekert_id)){
            i++;
        }
        bekert_id_index = i;
        System.out.println(bekert_id+" valaszai: \n"+adatok[i].valaszok);
    }
    void negyedik_feladat(){
        System.out.println(helyes_valasz+" (helyes valasz)");
        char[] valasz = helyes_valasz.toCharArray();
        char[] versenyzo = adatok[bekert_id_index].valaszok.toCharArray();
        for(int i = 0 ; i < valasz.length;i++){
            if(valasz[i] == versenyzo[i]){
                versenyzo[i] = '+';
            }
            else{
                versenyzo[i] = ' ';
            }
        }
        System.out.println(new String(versenyzo));
    }
    void otodik_feladat(){
        // kerjen be egy feladatszamot es hanyan talaltak el + szazalek
        System.out.println("Adjon meg egy verseny szamot: ");
        int verseny_szam = scan.nextInt() - 1 ;

        int counter = 0;
        for(int i = 0 ; i < line_counter ; i++){
            // ha a pillanatnyi valasz char array bekert szamu eleme egyenlo a helyes valasz char array bekert szamu elemevel
            if(adatok[i].valaszok.toCharArray()[verseny_szam] == helyes_valasz.toCharArray()[verseny_szam]){
                counter++;
            }
        }
        // szazalek
        float szazalek = ((float) counter / line_counter) * 100f;
        String numberAsString = String.format ("%.2f", szazalek);
        System.out.println("A feladat sorszama: "+verseny_szam+"\n Erre "+counter+" helyes válasz szuletett, ami "+numberAsString+"%");
    }
    void hatodik_feladat(){
        //A  verseny  feladatai  nem  egyenlő  nehézségűek:
        // az  1-5.  feladat  3  pontot,
        // a  6-10.  feladat  4 pontot,
        // a 11-13. feladat 5 pontot,
        // míg a 14. feladat 6 pontot ér.
        // irassa ki a pontok.txt-be

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));

            // pont meghatorazas
            for(int i = 0; i < line_counter; i++){
                bw.write(adatok[i].id+ " "+ pont_kalukator(adatok[i].valaszok));
                bw.newLine();
            }
            bw.flush();
            bw.close();

        }catch(IOException err){
            System.out.println("Output error.");
        }
    }

    int pont_kalukator(String valasz){
        int pontok = 0;
        for(int i = 0 ; i < 14 ; i++){
            if(valasz.toCharArray()[i] == helyes_valasz.toCharArray()[i]){
                if(i < 5){
                    pontok += 3;
                }
                else if(i > 4 && i < 10){
                    pontok += 4;
                }
                else if(i > 9 && i < 13){
                    pontok += 5;
                }
                else if( i == 14){
                    pontok += 6;
                }
            }
        }
        return pontok;
    }

    void hetedik_feladat(){
        // 3 legnagyobb ertek
        int max = 0;
        int max_2 = 0;
        int max_3 = 0;

        for(int i = 0 ; i < line_counter; i++){
            int current = pont_kalukator(adatok[i].valaszok);
            if(current > max){
                max = current;
            }
            if(current > max_2){
                if(current != max){
                    max_2 = current;
                }
            }
            if(current > max_3){
                if(max_2 != current && max != current){
                    max_3 = current;
                }
            }
        }
        // 3 tomb a teljesitettekre
        ArrayList<String> elsok = new ArrayList<>();
        ArrayList<String> masodikok = new ArrayList<>();
        ArrayList<String> harmadikok = new ArrayList<>();

        for(int i = 0; i < line_counter; i++){

            int current = pont_kalukator(adatok[i].valaszok);

            if(current == max) {
                elsok.add(adatok[i].id);
            }
            else if(current == max_2) {
                masodikok.add(adatok[i].id);
            }
            else if(current == max_3){
                harmadikok.add(adatok[i].id);
            }
        }

        for(String current : elsok){
            System.out.println("1. díj ("+max+") :"+ elsok);
        }
        for(String current : masodikok){
            System.out.println("2. díj ("+max_2+") :"+ masodikok);
        }
        for(String current : harmadikok){
            System.out.println("3. díj ("+max_3+") :"+ harmadikok);
        }

    }

}
