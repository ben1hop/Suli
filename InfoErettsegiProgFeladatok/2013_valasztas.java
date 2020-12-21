package Valasztasok;

import java.io.*;
import java.util.Scanner;


public class valasztas {
    class Data {
        int kerulet;
        int szavazat;
        String name;
        String part;
        Data(int kerulet , int szavazat , String name , String part){
            this.kerulet = kerulet;
            this.szavazat = szavazat;
            this.name = name;
            this.part = part;
        }
    }

    File input=null;
    File output=null;
    BufferedReader reader = null;
    BufferedWriter writer = null;

    Data[] adatok = new Data[100];
    int line_num , feladat = 0;

    int jogosult = 12345;

    valasztas(){
        elso();
        masodik();
        //harmadik();
        negyedik();
        otodik();
        hatodik();
        hetedik();
    }

    private void elso() {
        feladat++;
        cout(feladat+". feladat");
        try{
            input = new File("szavazatok.txt");
            reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine())!= null){
                String[] a = line.split(" ");
                adatok[line_num] = new Data(Integer.parseInt(a[0]), Integer.parseInt(a[1]), a[2]+" "+a[3] , a[4]);
                line_num++;
            }
        } catch(IOException err){
            System.err.print(err);
        }
    }

    private void masodik() {
        feladat++;
        cout(feladat+". feladat");
        cout("A helyhatósági választáson "+line_num+" képviselőjelölt indult. ");
    }

    private void harmadik() {
        feladat++;
        cout(feladat+". feladat");
        Scanner scan = new Scanner(System.in);
        cout("Adjon meg egy kereszt es egy vezetek nevet:");
        String bekert = scan.nextLine()+" "+scan.nextLine();;
        int i = 0;
        while( i < line_num && !adatok[i].name.equals(bekert)){
            i++;
        }
        if(i == line_num){
            cout("Ilyen  nevű  képviselőjelölt  nem  szerepel  a nyilvántartásban!");
        }
        else{
            cout(adatok[i].szavazat+"-ot kapott");
        }

    }

    private void negyedik() {
        feladat++;
        cout(feladat+". feladat");
        int ossz_szavazat = 0;
        for(int i = 0; i < line_num;i++){
            ossz_szavazat+=adatok[i].szavazat;
        }
        float arany = (float)ossz_szavazat / (float)jogosult;
        String aranyy = String.format("%.2f", arany*100);
        cout( "A választáson "+ossz_szavazat+" állampolgár, a jogosultak "+aranyy+"%-a vett részt.");
    }

    private void otodik() {
        feladat++;
        cout(feladat+". feladat");
        
    }

    private void hatodik() {
        feladat++;
        cout(feladat+". feladat");
    }

    private void hetedik() {
        feladat++;
        cout(feladat+". feladat");
    }


    void cout(String text){
        System.out.println(text);
    }
}
