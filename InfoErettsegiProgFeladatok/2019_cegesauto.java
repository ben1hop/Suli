package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static class Data{
        int nap;
        String ora_perc;
        String rendszam;
        int szemelyi;
        int km_szamlalo;
        int ki_be_hajtas;

        Data(String line){
            String[] line_cut = line.split(" ");

            nap = Integer.parseInt(line_cut[0]);
            ora_perc = line_cut[1];
            rendszam = line_cut[2];
            szemelyi = Integer.parseInt(line_cut[3]);
            km_szamlalo = Integer.parseInt(line_cut[4]);
            ki_be_hajtas = Integer.parseInt(line_cut[5]);

        }
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        //marosvari.karolyne@freemail.hu
        // max 500 sor lehet

        // 1. kerdes - Beolvasas es tarolas
        Data[] adatok = new Data[500];

        File input = new File("A:\\ben1hop\\CodeProjects\\IntellijIdea\\erettsegi_2019_tavasz\\src\\com\\company\\autok.txt");
        BufferedReader read = new BufferedReader(new FileReader(input));


        // Beolvasva , tarolva es sorok megszamolva
        int line_counter = 0;
        String line;
        while((line = read.readLine()) != null){
            adatok[line_counter] = new Data(line);
            line_counter++;
        }



        // 2. kerdes
        // Melyik autot vittek el utoljára // lista utolsó 0 ki_be_hajtas
        String utolso_rendszam = "nincs";
        for(int i = 0; i < line_counter ; i++){
            if(adatok[i].ki_be_hajtas == 0){
                utolso_rendszam = adatok[i].rendszam;
            }
        }
        System.out.println("Utolso kivitt autó: "+utolso_rendszam);




        // 3. kerdes
        // Kerjen be egy napot es irassa ki az osszes kivett es visszahozott autot
        System.out.println("Adjon meg egy napot: ");
        Scanner scanner = new Scanner(System.in);
        int bekert_nap = scanner.nextInt();
        System.out.println("Aznapi osszes ki/be vitt autó: ");

        String[] unique_auto = new String[10];
        int unqiue=0;

        int counter;

        for(int i = 0; i < line_counter ; i++){
            if(adatok[i].nap == bekert_nap){
                counter = 0;
                // rendezett halmazok unioja .. a szamlalo feltetel es elore ami hianyzott
                // stringet string.equals()-al hasonlitunk ossze
                while(counter < unique_auto.length && !adatok[i].rendszam.equals(unique_auto[counter])){
                    counter++;
                }
                if(unique_auto.length == counter){
                    unique_auto[unqiue] = adatok[i].rendszam;
                    System.out.println(unique_auto[unqiue]);
                    unqiue++;
                }
            }
        }




        // 4. feladat
        // Hany auto nemvolt bent a honap vegen
        String[] vissz_nem_vitt = new String[line_counter];
        int vissz= 0;

        for(int i = 0; i < line_counter ; i++){
            if(adatok[i].ki_be_hajtas == 0){
                int j = i+1;
                while(j < line_counter && adatok[i].szemelyi != adatok[j].szemelyi && !adatok[i].rendszam.equals(adatok[j].rendszam) ){
                    j++;
                }
                if(j == line_counter){
                    vissz_nem_vitt[vissz] = adatok[i].rendszam;
                    vissz++;
                }
            }
        }
        System.out.println("Ennyi auto nemvolt bent a honap vegen: "+vissz);




        //5. feladat
        // Statisztika , minden auto megtett kilometere ..  ha meg kint van akkor az utolso behozas
        class Autok{
            int megtett_km;
            String rendszam;
            Autok(String rend , int km){
                megtett_km = km;
                rendszam = rend;
            }
            Autok(){
                megtett_km = 0;
                rendszam = "";
            }
        }

        Autok[] autok = { new Autok("CEG300", 0) , new Autok("KEF210" , 0), new Autok("RAK748" , 0) , new Autok("IPA801",0) , new Autok("REK120" , 0) , new Autok("CEG308",0)};
        //Autok[] autok = new Autok[6];
        int autok_counter = 6;

        for(int i = 0; i < line_counter; i++){
            // csak kihajtásra keresünk párt
            if(adatok[i].ki_be_hajtas== 0){
                int j = i+1;
                // megkeressuk a kovi ilyen rendszamut i+1tol
                // a tobbszori kocsikivitel nem problema mert eloszor mindenkepp egy behajtast kell talalnunk
                while(j < line_counter && !(adatok[i].rendszam.equals(adatok[j].rendszam)) ){
                    j++;
                }
                if(j == line_counter){
                    // nincs behajtása akkor nincs vissza hozva ergo nemkell nekunk
                }else{
                    // megtaltala a j-edik elemen a kovetkezo ilyen rendszamut ami biztos hogy a behajtas kell legyen
                    // megnezzuk ilyen rendszam van-e az autokban
                    int k = 0;
                    while(k < autok.length && !(adatok[i].rendszam.equals(autok[k].rendszam)) ){
                        k++;
                    }
                    if(k == autok.length){
                        // ha nincs hozza adjuk
                        autok[autok_counter].rendszam = autok[i].rendszam;
                        autok[autok_counter].megtett_km = adatok[j].km_szamlalo - adatok[i].km_szamlalo;
                        autok_counter++;
                    }else{
                        // ha van akkor az indexen levo megtett km-et noveljuk
                        autok[k].megtett_km = autok[k].megtett_km + (adatok[j].km_szamlalo - adatok[i].km_szamlalo);
                    }
                }

            }
        }
        System.out.println("Az osszes auto megtett km-ekkel: ");
        for(int i = 0; i < autok_counter; i++){
            System.out.println(autok[i].rendszam + " "+ autok[i].megtett_km+"km");
        }




        // 6. feladat
        int max = 0;
        int szemely = 0;
        for(int i= 0; i < line_counter; i++){
            if(adatok[i].ki_be_hajtas== 0){
                int j = i+1;
                while(j < line_counter && adatok[i].szemelyi != adatok[j].szemelyi){
                    j++;
                }
                // nem talalt part
                if(j == line_counter){
                    // nemhozta vissza
                }
                else{
                    // talalt part
                    if(max < (adatok[j].km_szamlalo - adatok[i].km_szamlalo)){
                        max = adatok[j].km_szamlalo - adatok[i].km_szamlalo;
                        szemely = adatok[j].szemelyi;
                    }
                }
                
            }
        }
        System.out.println("A leghosszabb út: "+max+". Szemely: "+szemely);




        // 7. feladat
        // file keszites input stringbol a hozza tartozo adatok alapjan
        // 1. szemelyi , 2. kivitel , 3. km_mero , 4. bevitel  , 5. km_mero      mind tabulatorral
        Scanner scan = new Scanner(System.in);
        String rendszam = scan.nextLine();
        String pathname = "A:\\ben1hop\\CodeProjects\\IntellijIdea\\erettsegi_2019_tavasz\\src\\com\\company\\"+rendszam+"_menetlevel.txt";

        class Menetlevel{
            int szemelyi=0;
            String kivitel=""; // nap+ora
            int km_mero_ki=0;
            String bevitel=""; // nap+ora
            int km_mero_be=0;
        }

        ArrayList<Menetlevel> menetlevel = new ArrayList<>();

        int counterr = 0; // megszamoljuk alkalommal vittek ki es hoztak be egy kocsit
        for(int i = 0; i < line_counter ; i++){
            // megkeressuk az input
            if(rendszam.equals(adatok[i].rendszam)){
                // ha a behajtas
                if(adatok[i].ki_be_hajtas == 0){
                    menetlevel.add(new Menetlevel());
                    menetlevel.get(counterr).szemelyi = adatok[i].szemelyi;
                    menetlevel.get(counterr).kivitel = adatok[i].nap+". "+adatok[i].ora_perc;
                    menetlevel.get(counterr).km_mero_ki = adatok[i].km_szamlalo;
                }
                //  ha kihajtas
                else{
                    menetlevel.get(counterr).bevitel = adatok[i].nap+". "+adatok[i].ora_perc;
                    menetlevel.get(counterr).km_mero_be = adatok[i].km_szamlalo;
                    counterr++;
                }

            }
        }

        try (FileWriter writer = new FileWriter(pathname);
             BufferedWriter bw = new BufferedWriter(writer)) {
            int i = 0;
            while(i < counterr){
                bw.write(menetlevel.get(i).szemelyi+"\t"+menetlevel.get(i).kivitel+"\t"+menetlevel.get(i).km_mero_ki+"\t"+menetlevel.get(i).bevitel+"\t"+menetlevel.get(i).km_mero_be+"\t");
                bw.newLine();
                i++;
            }


        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        System.out.println("A file elkészült.");






    }
}
