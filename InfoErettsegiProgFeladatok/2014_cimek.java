package IPv6;

import java.io.*;
import java.lang.module.FindException;
import java.util.Arrays;
import java.util.Scanner;

public class cimek {
    // hexadecimalis szamok 16 szamjegybol allnak 0-9+a-f ezek erteke 0-15
    // es ugy konvertaljuk decimalisba h 16ot a szamjegyuk helyere emelunk es megszorozzuk az ertekukkel
    String[] adatok = new String[500]; // max 500 min 20 sor
    int line_count=0;

    int task_num = 1;

    // ha az ascii szamok utan kezdodnek a betuk akkor 1-9+
                                // a,   b , c ,   d , e ,   f
    final int [] letter_values = { 10, 11 , 12 , 13 , 14 , 15};

    File input = new File("ip.txt");
    BufferedReader reader;
    BufferedWriter writer;

    cimek(){
        elso_task();
        masodik_task();
        harmadik_task();
        negyedik_task();
        otodik_task();
        hatodik_task();
        hetedik_task();
    }

    void cout(String text){
        System.out.println(text);
    }

    int hexa_into_deciaml(char[] block){
        // example : 02A5
        int[] decimal = new int[4];
        // 5 -> 5
        decimal[3] = decide_ascii(block[3]) * 1; // 16^0
        // A -> 10
        decimal[2] = decide_ascii(block[2]) * 16; // 16^1
        // 2 -> 2
        decimal[1] = decide_ascii(block[1]) * 256; // 16^2
        // 0 -> 0
        decimal[0] = decide_ascii(block[0]) * 4096; // 16^3

        return decimal[0]+decimal[1]+decimal[2]+decimal[3];
    }

    private void elso_task() {
        cout(task_num+". feladat.");
        task_num++;
        try{
            reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine()) != null){
                adatok[line_count] = line;
                line_count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void masodik_task() {
        cout(task_num+". feladat.");
        task_num++;
        cout(line_count+" adatsor van az allomanyban.");
    }
/*
    private void harmadik_task() {
        cout(task_num+". feladat.");
        task_num++;

        int min=100000000;
        int index=0;

        for(int i = 0 ; i < line_count;i++){
            String[] values = adatok[i].split(":");
            int line_value=0;
            int block_value=0;
            // convert each line to blocks
            for(int j = 0; j < 8 ; j++){
                block_value = hexa_into_deciaml(values[j].toCharArray());
                // convert each block into values
                line_value += block_value;
                //cout(line_value+"");
            }
            // compare the sum of it
            if(line_value<min){
                min = line_value;
                index = i;
            }
        }
        cout("A legalacsonyabb tarolt ip: "+adatok[index]);
    }*/
    private void harmadik_task(){
        cout(task_num+". feladat.");
        task_num++;
        String min = adatok[0];
        for(int i = 1; i < line_count;i++){
            if(min.compareTo(adatok[i]) > 0){
                min = adatok[i];
            }
        }
        cout("A legalacsonyabb tarolt ip: "+min);
    }

    int decide_ascii(char letter){
        switch(letter){
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'a':
                return 10;
            case 'b':
                return 11;
            case 'c':
                return 12;
            case 'd':
                return 13;
            case 'e':
                return 14;
            case 'f':
                return 15;
            default:
                break;
        }
        return -1;
    }

    private void negyedik_task() {
        cout(task_num+". feladat.");
        task_num++;
        String doc_cim = "2001:0db8:";
        String glob_cim = "2001:0e";
        String helyi_cim = "fc";
        String helyi_cim_2 = "fd";

        int doc_counter=0;
        int glob_counter=0;
        int helyi_counter=0;

        for(int i = 0; i < line_count;i++){
            if(adatok[i].startsWith(doc_cim)){
                doc_counter++;
            }
            if(adatok[i].startsWith(glob_cim)){
                glob_counter++;
            }
            if(adatok[i].startsWith(helyi_cim) || adatok[i].startsWith(helyi_cim_2)){
                helyi_counter++;
            }
        }
        cout("Dok cimek: "+doc_counter+"\nGlobalis cimek: "+glob_counter+"\nHelyi cimek: "+helyi_counter);
    }

    private void otodik_task() {
        cout(task_num+". feladat.");
        task_num++;
        File output = new File("sok.txt");
        try{
            writer = new BufferedWriter(new FileWriter(output));
            for(int i = 0; i < line_count;i++){
                int zero_counter = 0;
                char[] array = adatok[i].toCharArray();
                for(int j = 0; j < array.length;j++){
                    if(decide_ascii(array[j]) == 0){
                        zero_counter++;
                    }
                }
                if(zero_counter>17){
                    writer.write((i+1)+". "+adatok[i]);
                    writer.newLine();
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hatodik_task() {
        cout(task_num+". feladat.");
        task_num++;
        cout("Adjon meg egy sorszamot:");
        Scanner scan = new Scanner(System.in);
        String value = adatok[(scan.nextInt())-1];
        cout(value);
        String[] split = value.split(":");
        for(int i = 0; i < 8 ;i++){
            if(split[i].startsWith("0")){
                split[i].substring(1);
            }
        }
    }

    private void hetedik_task() {
        cout(task_num+". feladat.");
        task_num++;
    }

}
