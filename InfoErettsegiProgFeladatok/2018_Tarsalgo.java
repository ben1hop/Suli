package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarsalgo {

    class Data{
        int ora;
        int perc;
        int id; // 1-100
        boolean irany; // 0 be 1 ki

        int jelenlegi_bentlevok = 0;
        Data(String line){
            String[] temp = line.split(" ");
            ora = Integer.parseInt(temp[0]);
            perc = Integer.parseInt(temp[1]);
            id = Integer.parseInt(temp[2]);
            irany = temp[3].equals("be");

        }
    }

    Data[] adatok = new Data[1000];
    int line_counter = 0;

    // 3. feladathoz
    ArrayList<Integer> unique_list = new ArrayList<>();

    // 4. feladathoz
    ArrayList<Integer> bent_maradtak = new ArrayList<>();

    // 6. feladt es tovabb
    int bekert_id;
    int bent_toltott;

    Tarsalgo() throws IOException {
        elso_feladat();
        masodik_feladat();
        harmadik_feladat();
        negyedik_feladat();
        otodik_feladat();
        hatodik_feladat();
        hetedik_feladat();
        nyolcadik_feladat();
    }

    public void elso_feladat() throws IOException {
        File input = new File("ajto.txt");
        BufferedReader read = new BufferedReader(new FileReader(input));

        String line;
        while((line = read.readLine()) != null){
            adatok[line_counter] = new Data(line);
            line_counter++;
        }
    }

    public void masodik_feladat(){
        // irja ki az elso belepot es az utolso kilepot
        // mivel tudjuk h ures a terem nyitaskor igy az elso elem biztosan belepo lesz
        System.out.println("Az elso belepo: "+adatok[0].id);
        // Az utolsohoz pedig elkell indulnunk visszafele amig nemtalalunk egy kilepot
        int j = line_counter-1;
        // 0.-ot nem ellenorizzuk a fenti allitas miatt
        while(j > 0 && adatok[j].irany){
            j--;
        }
        if(j != 0){
            System.out.println("Az utolso kilepo pedig: "+adatok[j].id);
        }else{
            System.out.println("Nincs kilepo.");
        }


    }

    public void harmadik_feladat(){
        // szamoljuk meg ki hanyszor ment at az ajton
        // kell egy egyedi id tomb amihez megszamoljuk az adatokban szereplo kibe lepeseket
        // kivalogatas tetel
        for(int i = 0; i < line_counter;i++){
            if(i == 0){
                unique_list.add(adatok[i].id);
            }

            int j = 0;
            while(j < unique_list.size() && unique_list.get(j) != adatok[i].id){
                j++;
            }if(j == unique_list.size()){
                unique_list.add(adatok[i].id);
            }
        }
        // megvan a unique array most rendeznunk kell novekvo sorrendbe
        for(int i = 0; i < unique_list.size(); i++){
            for(int j = i+1; j < unique_list.size(); j++){
                if(unique_list.get(j) < unique_list.get(i)){
                    int temp = unique_list.get(j);
                    unique_list.set(j ,  unique_list.get(i));
                    unique_list.set(i , temp);
                }
            }
        }
        // mostmar csak megkell szamolni a mozgasokat es kiiratni
        File output = new File("athaladas.txt");
        try (FileWriter fw = new FileWriter(output);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            for(int i = 0; i < unique_list.size(); i++){
                int counter = 0;
                for(int j = 0 ; j < line_counter; j++){
                    if(unique_list.get(i) == adatok[j].id){
                        counter++;
                    }
                }
                bw.write(unique_list.get(i)+".id belepesei: "+counter+"\n");
            }
        }catch(IOException err){
            System.out.println("File output hiba.");
        }

    }

    public void negyedik_feladat(){
        // irjuk ki azokat a szemelyeket akik a meres vegen is bent tartozkodtak
        // ergo olyan id akiknek nincs ki parja
        // kell egy tomb amiben taroljuk
        for(int i = 0; i < line_counter ; i++){
            if(adatok[i].irany){
                int j = i+1;
                // megkeressuk a kovetkeezo elemtol az id parjat
                // mivel nemtud ketszer bemenni igy biztos kijovetel lesz a kovetkezo ezen az id-n
                while(j < line_counter && adatok[i].id != adatok[j].id){
                    j++;
                }
                if(j == line_counter){
                    // nincs parja
                    bent_maradtak.add(adatok[i].id);
                }
            }
        }
        String kiirat = "";
        int j = 0;
        while(j < bent_maradtak.size()){
            kiirat = kiirat + bent_maradtak.get(j).toString() +" ";
            j++;
        }
        System.out.println("A meres utan bent maradtak: "+kiirat);
    }

    public void otodik_feladat(){
        // mikor voltak a legtobben a tarsalgoban
        // kimentettuk minden idoponthoz a pillanatnyi bentlevoket
        // max kivalasztas
        // plusz megszamolni a jelenlevoket
        int max = 0;
        int max_ora=0;
        int max_perc=0;
        int jelenleti=0;
        for(int i =0 ; i < line_counter; i++){
            if(adatok[i].irany){
                jelenleti++;
            }
            else{
                jelenleti--;
            }
            if(max < jelenleti){
                max = jelenleti;
                max_ora = adatok[i].ora;
                max_perc = adatok[i].perc;
            }
        }
        System.out.println("A legtobben ("+max+") ekkor "+max_ora+":"+max_perc+"-kor voltak bent." );
    }

    public void hatodik_feladat(){
        // kerj be egy azonositot
        Scanner scan = new Scanner(System.in);
        System.out.println("Adjon meg egy id-t: ");
        bekert_id = scan.nextInt();

    }

    public void hetedik_feladat(){
        // bekert id meddig tartozkodott bent
        // keresd meg a parokat es irasd ki
        String[] bent_let = new String[200];
        int counter=0;

        System.out.println("7. feladat: ");
        for(int i = 0 ; i < line_counter ; i++){
            if(adatok[i].id == bekert_id){
                if(adatok[i].irany){
                    bent_let[counter] = adatok[i].ora+":"+adatok[i].perc+"-";
                }
                else{
                    bent_let[counter] = bent_let[counter] + adatok[i].ora+":"+adatok[i].perc;
                    System.out.println(bent_let[counter]);
                    counter++;
                }
            }
        }

    }

    public void nyolcadik_feladat(){

        // megkeressuk a parokat ismet
        // de most taroljuk az idopontokat perce alakitva
        // kivonogatjuk a masodikbol az elsot es hozza adjuk a szamlalohoz
        boolean bent_maradt = false;

        for(int i = 0 ; i < line_counter ; i++){
            int first = 0;
            int second = 0;
            if(adatok[i].id == bekert_id){
                if(adatok[i].irany){
                   first = (adatok[i].ora * 60) + adatok[i].perc;
                   int j = i+1;
                   while(j < line_counter && adatok[j].id != adatok[i].id){
                       j++;
                   }
                   if(j == line_counter){
                       // nincs parja igy a 15:00-bol vonjuk ki a first idot
                       bent_toltott = bent_toltott + ((15*60)- first);
                       bent_maradt = true;

                   }
                   else {
                       second = (adatok[j].ora * 60) + adatok[j].perc;
                       bent_toltott = bent_toltott + (second - first);
                   }
                }
            }
        }
        if(bent_maradt){
            System.out.println("A(z) "+bekert_id+" személy összesen "+bent_toltott+" percet volt bent, a megfigyelés végén a társalgóban volt. ");
        }else{
            System.out.println("A(z) "+bekert_id+" személy összesen "+bent_toltott+" percet volt bent, a megfigyelés végén nem volt a tarsalgoban. ");
        }
    }
}
