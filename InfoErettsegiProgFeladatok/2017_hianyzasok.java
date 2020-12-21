package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class hianyzasok {
    static class Data{
        static class Diak{
            String vezetek;
            String kereszt;
            char[] jelenleti = new char[7];  // 7 karakter  O:jelen N:nincs ora X:igazolt I:igazolatlan
            Diak(String line){
                String[] temp = line.split(" ");
                vezetek = temp[0];
                kereszt = temp[1];
                jelenleti = temp[2].toCharArray();
            }
        }

        int honap;
        int nap;
        Diak[] diakok = new Diak[50]; // max 50 diak lehet
        int diak_counter;

        Data(String line){
            String[] temp = line.split(" ");
            honap = Integer.parseInt(temp[1]);
            nap = Integer.parseInt(temp[2]);
            diak_counter = 0;
        }
    }

    // Tarolo tomb
    // Max 600 sor - ha minden nap a legrovidebb ( 3 sor ) akkor 600 / 3 = 200 elemu lehet a tomb;
    Data[] adatok = new Data[200];
    String[] beolvasott_adat = new String[600];
    int adatok_counter=0;
    int bejegyzes_counter=0;
    int line_counter=0;


    // 5. feladatól
    Scanner scan = new Scanner(System.in);
    int honap;
    int nap;

    hianyzasok() throws IOException{
        elso_feladat();
        masodik_feladat();
        harmadik_feladat();
        negyedik_feladat();
        otodik_feladat();
        hatodik_feladat();
        hetedik_feladat();
    }

    void convert(){
        // atalakitja a beolvasott stringeket a sajat adat strukturankba
        for(int i = 0; i < line_counter; i++){
            if(beolvasott_adat[i].charAt(0) == '#'){
                String first = beolvasott_adat[i];
                adatok[adatok_counter] = new Data(first);
                String second = "";
                int j = i+1;
                while(j < line_counter && beolvasott_adat[j].charAt(0) != '#'){
                    second = beolvasott_adat[j];
                    adatok[adatok_counter].diakok[adatok[adatok_counter].diak_counter++] = new Data.Diak(second);
                    //adatok[adatok_counter].diak_counter++;
                    j++;
                }
                adatok_counter++;
            }
        }
    }

    void elso_feladat() throws IOException {
        // beolvasas
        File file = new File("naplo.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while((line = reader.readLine()) != null){
            beolvasott_adat[line_counter] = line;
            line_counter++;
            if(line.charAt(0) != '#'){
                bejegyzes_counter++;
            }

        }
        // atalakitja a beolvasott sorokat
        convert();
        /*
        for(int i =0 ;i < adatok_counter; i++){
            System.out.println(adatok[i].honap);
            System.out.println(adatok[i].nap);
            for(int j =0; j < adatok[i].diak_counter ; j++){
                System.out.println(adatok[i].diakok[j].vezetek);
                System.out.println(adatok[i].diakok[j].kereszt);
                System.out.println(adatok[i].diakok[j].jelenleti);
            }
        }*/


    }
    void masodik_feladat(){
        System.out.println("A sorok száma: "+bejegyzes_counter);
    }
    void harmadik_feladat(){
        // szamolja meg hany igazolatlan ora volt
        int igazolatlan=0;
        for(int i = 0; i < adatok_counter;i++){
            for(int j = 0; j < adatok[i].diak_counter;j++){
                for(int k = 0; k < 7 ; k++){
                    if(adatok[i].diakok[j].jelenleti[k] == 'I'){
                        igazolatlan++;
                    }
                }
            }
        }
        System.out.println("Ennyi igazolatlan ora van: "+igazolatlan);
    }
    void negyedik_feladat(){
        // a hetnapja fuggveny
    }
    void otodik_feladat(){

        System.out.println("Kerem adjon meg egy honapot: ");
        honap = scan.nextInt();
        System.out.println("Kerem adjon meg egy napot: ");
        nap = scan.nextInt();

        System.out.println("Ezen a napon "+hetnapja(honap , nap)+" volt");

    }
    void hatodik_feladat(){
        //
        System.out.println("Kerem adjon meg egy nap nevet: ");
        String nap_nev = scan.next();
        System.out.println("Kerem adjon meg egy oraszamot: ");
        int oraszam = scan.nextInt();

        // vegig megyunk a tombon
        int hianyzasok =0;
        for(int i =0 ; i<adatok_counter; i++){
            // megnezzuk hogy a het a jelenlegi idopont napja az egyenlo-e a beadottal
            if( nap_nev.equals( hetnapja( adatok[i].honap , adatok[i].nap ))){
                // ha igen vegigmegyunk a diaktombon es ellenorizzuk a bekert oraszam elemen a jelenleti erteket
                for(int j = 0; j < adatok[i].diak_counter;j++){
                    if( adatok[i].diakok[j].jelenleti[oraszam-1] == 'I' || adatok[i].diakok[j].jelenleti[oraszam-1] == 'X'){
                        hianyzasok++;
                    }
                }
            }
        }
        System.out.println("Napnev: "+nap_nev);
        System.out.println("Oraszam: "+oraszam);
        System.out.println("Hianyzasok aznap: "+hianyzasok);

    }
    void hetedik_feladat(){

    }


    String hetnapja(int honap , int nap){
        String[] napok = {"VASARNAP", "HETFO", "KEDD", "SZERDA", "CSUTORTOK", "PENTEK", "SZOMBAT"};
        int[] napszam = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 335 };
        int napsorszam = (napszam[honap-1]+nap) % 7;
        return napok[napsorszam];
    }


}
