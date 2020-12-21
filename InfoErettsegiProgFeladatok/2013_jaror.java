import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class jaror {
    class Data{
        int ora;
        int perc;
        int mp;
        String rendszam;
        Data(String a , String b ,String c, String d){
            ora = Integer.parseInt(a);
            perc = Integer.parseInt(b);
            mp = Integer.parseInt(c);
            rendszam = d;
        }
    }

    int line_count = 0;
    Data[] adatok = new Data[1000];
    int task = 0;

    void cout(String text){
        System.out.print(text+"\n");
    }

    jaror(){
        elso();
        masodik();
        harmadik();
        negyedik();
        otodik();
        //hatodik();
        hetedik();
    }

    private void elso() {
        task++;
        cout(task+". feladat");

        try{
            File input = new File("jarmu.txt");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;

            while((line = reader.readLine())!=null){
                String[] build = line.split(" ");
                adatok[line_count] = new Data( build[0], build[1], build[2], build[3]);
                line_count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void masodik() {
        task++;
        cout(task+". feladat");
        cout((adatok[line_count-1].ora+1 - adatok[0].ora)+" orat dolgoztak.");
    }

    private void harmadik() {
        task++;
        cout(task+". feladat: ");
        int current_h = 0;
        for(int i = 0; i < line_count;i++){
            if(current_h != adatok[i].ora){
                current_h = adatok[i].ora;
                cout(current_h+". óra: "+adatok[i].rendszam);
            }
        }
    }

    private void negyedik() {
        task++;
        cout(task+". feladat");
        // B K M else
        int[] type = { 0, 0, 0, 0};
        for(int i = 0; i < line_count;i++){
            char ch = adatok[i].rendszam.charAt(0);
            switch(ch){
                case 'B':
                    type[0]++;
                    break;
                case 'K':
                    type[1]++;
                    break;
                case 'M':
                    type[2]++;
                    break;
                default:
                    type[3]++;
                break;
            }
        }
        cout("Autobusz: "+type[0]+"\nKamion: "+type[1]+"\nMotor: "+type[2]+"\nSzemelygepkocsi: "+type[3]);
    }

    int time_in_value(int h , int m , int sc){
        return (h*3600)+(m*60)+sc;
    }

    private void otodik() {
        task++;
        cout(task+". feladat");
        int max = 0;

        Data first = null , second = null;
        for(int i = 1; i < line_count;i++){
            int curr = time_in_value(adatok[i].ora, adatok[i].perc , adatok[i].mp);
            int next = time_in_value(adatok[i-1].ora, adatok[i-1].perc , adatok[i-1].mp);
            if((curr - next) > max){
                max = curr - next;
                first = adatok[i-1];
                second = adatok[i];
            }
        }
        cout(first.ora+":"+first.perc+":"+first.mp+" - "+second.ora+":"+second.perc+":"+second.mp);
    }

    private void hatodik() {
        task++;
        cout(task+". feladat");
        cout("Adjon meg egy *os rendszamot: ");
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();
        char[] asd = in.toCharArray();
        cout("Lehetséges rendszámok: ");
        for(int i = 0 ; i < line_count ;i++){
            char[] asd2 = adatok[i].rendszam.toCharArray();
            int j = 0;
            while( j < asd.length && (asd[j] == '*' || asd[j] == asd2[j]) ){
                j++;
            }
            if(j == asd.length){
                cout(adatok[i].rendszam);
            }
        }
    }

    private void hetedik() {
        task++;
        cout(task+". feladat");
        try{
            File output = new File("vizsgalt.txt");
            BufferedWriter write = new BufferedWriter(new FileWriter(output));
            Data current = adatok[0];
            write.write("0"+current.ora+" "+current.perc+" "+current.mp+" "+current.rendszam);
            write.newLine();
            for(int i = 1; i < line_count;i++){
                if(adatok[i].ora == current.ora){
                    if(adatok[i].perc == (current.perc+5) ){
                        if(adatok[i].mp >= current.mp){
                            current = adatok[i];
                            if(current.ora < 10){
                                write.write("0"+current.ora+" ");
                            }
                            else{
                                write.write(current.ora+" ");
                            }
                            if(current.perc < 10){
                                write.write("0"+current.perc+" ");
                            }
                            else{
                                write.write(current.perc+" ");
                            }
                            if(current.mp < 10){
                                write.write("0"+current.mp+" ");
                            }
                            else{
                                write.write(current.mp+" ");
                            }
                            write.write(current.rendszam);
                            write.newLine();
                        }
                    }else if(adatok[i].perc > current.perc+5){
                        current = adatok[i];
                        if(current.ora < 10){
                            write.write("0"+current.ora+" ");
                        }
                        else{
                            write.write(current.ora+" ");
                        }
                        if(current.perc < 10){
                            write.write("0"+current.perc+" ");
                        }
                        else{
                            write.write(current.perc+" ");
                        }
                        if(current.mp < 10){
                            write.write("0"+current.mp+" ");
                        }
                        else{
                            write.write(current.mp+" ");
                        }
                        write.write(current.rendszam);
                        write.newLine();
                    }
                }
                else {
                    if(adatok[i].perc == (current.perc-60)+5) {
                        if (adatok[i].mp >= current.mp) {
                            current = adatok[i];
                            if(current.ora < 10){
                                write.write("0"+current.ora+" ");
                            }
                            else{
                                write.write(current.ora+" ");
                            }
                            if(current.perc < 10){
                                write.write("0"+current.perc+" ");
                            }
                            else{
                                write.write(current.perc+" ");
                            }
                            if(current.mp < 10){
                                write.write("0"+current.mp+" ");
                            }
                            else{
                                write.write(current.mp+" ");
                            }
                            write.write(current.rendszam);
                            write.newLine();
                        }
                    }
                    else if(adatok[i].perc > (current.perc-60)+5){
                        current = adatok[i];
                        if(current.ora < 10){
                            write.write("0"+current.ora+" ");
                        }
                        else{
                            write.write(current.ora+" ");
                        }
                        if(current.perc < 10){
                            write.write("0"+current.perc+" ");
                        }
                        else{
                            write.write(current.perc+" ");
                        }
                        if(current.mp < 10){
                            write.write("0"+current.mp+" ");
                        }
                        else{
                            write.write(current.mp+" ");
                        }
                        write.write(current.rendszam);
                        write.newLine();
                    }
                }
            }
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
