package nezoter;

import java.io.*;
import java.util.Scanner;

public class nezoter {
    class adatok {
        boolean free;
        int pricetag;
        adatok(String spot){
            if(spot.equals("x")){
                free = false;
            }
            if(spot.equals("o")){
                free = true;
            }
            pricetag = 0;
        }
    }
    adatok[][] array = new adatok[15][20];
    int i;
    //int j;

    int feladatSzam=1;

    //Scanners
    File input;
    BufferedReader reader;

    nezoter(){
        elso_feladat();
        masodik_feladat();
        harmadik_feladat();
        negyedik_feladat();
        otodik_feladat();
        hatodik_feladat();
        hetedik_feladat();
    }

    void cout(String text){
        System.out.println(text);
    }

    private void elso_feladat() {
        cout(feladatSzam+". feladat");
        feladatSzam++;
        try{
            // file 1)
            input = new File("foglaltsag.txt");
            reader = new BufferedReader(new FileReader(input));
            String line;
            i = 0;
            int seged = 0;
            while((line = reader.readLine()) != null){
                for(int j = 0; j < 20 ; j++){
                    array[i][j] = new adatok(line.substring(j,j+1));
                }
                i++;
            }
            reader.close();
            // file 2)
            input = new File("kategoria.txt");
            reader = new BufferedReader(new FileReader(input));
            i = 0;
            seged = 0;
            while((line = reader.readLine()) != null){
                for(int j = 0; j < 20 ; j++){
                    array[i][j].pricetag = Integer.parseInt(line.substring(j,j+1));
                }
                i++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void masodik_feladat() {
        cout(feladatSzam+". feladat");
        Scanner scan = new Scanner(System.in);
        cout("adjon meg egy sor es szek szamot: ");
        if(array[scan.nextInt()-1][scan.nextInt()-1].free){
            cout("A hely szabad.");
        }
        else{
            cout("A hely foglalt.");
        }
        feladatSzam++;
    }

    private void harmadik_feladat() {
        cout(feladatSzam+". feladat");
        final int befogadokepesseg = 15 * 20;
        int eladott = 0;
        for(int i = 0; i < 15 ; i++){
            for(int j = 0; j < 20 ; j++){
                if(!array[i][j].free){
                    eladott++;
                }
            }
        }
        float szazalek = ((float)eladott / (float)befogadokepesseg)*100;
        cout(eladott+" jegyet adtak el.\nEz a nézőtér "+(int)szazalek+"%-a");
        feladatSzam++;
    }

    private void negyedik_feladat() {
        cout(feladatSzam+". feladat");
        int max=0;
        int max_ind=0;
        int seged=0;
        for(int i = 0; i <5; i++){
            for(int j =0; j < 15; j++){
                for(int k =0; k < 20;k++){
                    if(array[j][k].pricetag == i+1){
                        seged++;
                    }
                }
            }
            cout(""+seged);
            if(seged>max){
                max = seged;
                max_ind = i;
            }
            seged = 0;
        }
        cout("A legtöbb jegyet a(z) "+(max_ind+1)+" arkategoriaban ertekesitettek");
        feladatSzam++;

    }

    private void otodik_feladat() {
        cout(feladatSzam+". feladat");
        int sum = 0;
        for(int i = 0; i < 15 ; i++){
            for(int j = 0; j < 20 ; j++) {
                sum+=converter(array[i][j].pricetag);
            }
        }
        cout("A bevétel "+sum);
        feladatSzam++;
    }

    private void hatodik_feladat() {
        cout(feladatSzam+". feladat");
        int nehez_hely =0;
        for(int i = 0; i < 15 ; i++){
            for(int j = 0; j < 20 ; j++) {
                if(j == 0){
                    if(array[i][j].free && !array[i][j+1].free){
                        nehez_hely++;
                    }
                }
                else if(j == 19){
                    if(array[i][j].free && !array[i][j-1].free){
                        nehez_hely++;
                    }
                }
                else if(array[i][j].free && (!array[i][j-1].free && !array[i][j+1].free)){
                    nehez_hely++;
                }
            }
        }
        cout(nehez_hely+" ilyen hely van.");
        feladatSzam++;
    }

    private void hetedik_feladat() {
        cout(feladatSzam+". feladat");
        try{
            File output = new File("szabad.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            for(int i =0; i < 15 ; i++){
                for(int j = 0; j < 20 ; j++) {
                    if (array[i][j].free) {
                        writer.write(""+array[i][j].pricetag);
                    } else {
                        writer.write("x");
                    }
                }
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        feladatSzam++;
    }

    int converter(int kat){
        switch(kat){
            case 1:
                return 5000;
            case 2:
                return 4000;
            case 3:
                return 3000;
            case 4:
                return 2000;
            case 5:
                return 1500;
            default:
                break;
        }
        return 0;
    }
}
