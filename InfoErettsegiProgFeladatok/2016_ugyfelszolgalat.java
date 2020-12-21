package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ugyfelszolgalat {
    public int mpbe ( int ora , int perc , int masodperc ){
        return (ora * 3600) + (perc * 60) + masodperc;
    }
    public int mpbe_2 ( Time ido ){
        return (ido.ora * 3600) + (ido.perc * 60) + ido.masodperc;
    }
    public class Time{
        int ora;
        int perc;
        int masodperc;
        Time(int ora , int perc , int masodperc){
            this.ora = ora;
            this.perc = perc;
            this.masodperc = masodperc;
        }
    }
    public class Hivo{
        Time hivott;
        Time letett;
        Hivo(String line){
            String[] temp = line.split(" ");
            hivott = new Time(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            letett = new Time(Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));
        }
        Hivo(){

        }
    }
    public class Fogadott {
        int sorszam;
        Hivo hivo;
        Fogadott(Hivo hivo , int sorszam){
            this.hivo = hivo;
            this.sorszam = sorszam;
        }
    }

    Hivo[] adatok = new Hivo[1000];
    int line_counter=0;

    ArrayList<Fogadott> fogadott = new ArrayList<>();

    Time nyitas = new Time(8,0,0);
    Time zaras = new Time(12,0,0);

    Scanner scan = new Scanner(System.in);
    File input = new File("hivas.txt");
    File output = new File("sikeres.txt");
    BufferedReader reader = null;
    BufferedWriter writer = null;

    ugyfelszolgalat(){
        elso_feladat();
        masodik_feladat();
        harmadik_feladat();
        negyedik_feladat();
        otodik_feladat();
        hatodik_feladat();
        hetedik_feladat();
    }

    void elso_feladat(){
        // converter
    }
    void masodik_feladat(){
        try{
            reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine()) != null){
                adatok[line_counter] = new Hivo(line);
                line_counter++;
            }
            reader.close();
        }catch (IOException err){
            System.out.println("Input error.");
        }
    }
    void harmadik_feladat(){
        // orankent hany hivas

        int current_ora = adatok[0].hivott.ora;
        for(int i = 0; i < line_counter; i++){
            int j = i;
            while(j < line_counter && current_ora == adatok[j].hivott.ora){
                j++;
            }
            System.out.println(current_ora+"-kor "+(j-i)+" hivas erkezett.");
            i = j;
        }
    }
    void negyedik_feladat(){
        // leghoszabb hivas
        int max = 0;
        int index = 0;
        for(int i = 0; i < line_counter; i++) {
            if(mpbe_2(adatok[i].letett) - mpbe_2(adatok[i].hivott) > max) {
                max = mpbe_2(adatok[i].letett) - mpbe_2(adatok[i].hivott);
                index = i+1;
            }
        }
        System.out.println("A leghoszabb hivas "+max+" volt , a "+index+" helyen.");
    }
    void otodik_feladat(){
        // adjon meg egy idopontot es hany varakozo es fogadott volt
        System.out.println("Adjon meg egy idopontot: ");


        int varakozok = 0;
        int hanyadik_hivo = 0;
        Time bekert = new Time(scan.nextInt() , scan.nextInt() , scan.nextInt());
        for(int i = 0 ; i < line_counter ; i++) {
            // az a varakozo aki elobb hivott es meg nemtette le az idopontig
            if(mpbe_2(adatok[i].hivott) < mpbe_2(bekert) && mpbe_2(adatok[i].letett) > mpbe_2(bekert)){
                hanyadik_hivo = i+1;
                varakozok++;
            }
        }
        if((hanyadik_hivo + varakozok) == 0){
            System.out.println("NIncs hivo.");
        }else{
            System.out.println("A varakozok szama: "+varakozok+" a beszelo a "+hanyadik_hivo+" hivo.");

        }

    }
    void hatodik_feladat(){
        // mikor fogad hivast

        int hanyadik_hivo = 0;
        Time current_letett = nyitas;
        Time current_hivott = zaras;
        for(int i = 0 ; i < line_counter ; i++) {


            // ha a 8.00 utan teszi le elsonek-> o az elso fogadott
            // utana ez lesz a merce aminel kesobb kell valakinek letenni -> kovi fogadott
            // ha 12.00 utan hiv azt mar nem nezzuk
            if(mpbe_2(adatok[i].letett) >= mpbe_2(current_letett) && mpbe_2(zaras) > mpbe_2(adatok[i].hivott)){
                hanyadik_hivo = i+1;
                current_letett = adatok[i].letett;
                current_hivott = adatok[i].hivott;
                fogadott.add(new Fogadott(adatok[i] , i+1));
            }
        }

        int miota_vart = mpbe_2(current_letett) - mpbe_2(current_hivott);
        System.out.println("Az utolso telefonalo adatai a(z) "+hanyadik_hivo+" sorban vannak, "+miota_vart+" masodpercig vart.");
    }
    void hetedik_feladat(){
        // mikor fogad hivast
        try{
            writer = new BufferedWriter(new FileWriter(output));
            for (Fogadott value : fogadott) {
                writer.write(value.sorszam + " " + value.hivo.hivott.ora +" "+ value.hivo.hivott.perc +" "+value.hivo.hivott.masodperc+" " + value.hivo.letett.ora + " " + value.hivo.letett.perc+ " " + value.hivo.letett.masodperc);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        }catch (IOException err){
            System.out.println("Output error.");
        }
    }
}
