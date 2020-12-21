import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Application {
    Random random;
    Scanner scanner;

    // Filestream
    File input = new File("kiserlet.txt");
    BufferedReader reader = new BufferedReader(new FileReader(input));

    File output = new File("dobasok.txt");
    BufferedWriter writer = new BufferedWriter(new FileWriter(output));

    // Laszt feladat
    class hahi{
        hahi(){
            sorozat = new String[4];
        }
        String[] sorozat;
    }


    // Global vars nektek kedveskeim
    int line_counter = 0;
    int fej_counter = 0;
    int iras_counter = 0;

    Application() throws IOException {
        elso_feladat();
        masodik_feladat();
        nesze_neked_tarolas_nelkul();
        harmadik_feladat();
        negyedik_feladat();
        otodik_feladat();
        hatodik_feladat();
        hetedik_feladat();
    }
    private void nesze_neked_tarolas_nelkul() throws IOException {
        String line;
        while((line = reader.readLine())!= null){
            line_counter++;
            if(line.equals("F")){
                fej_counter++;
            }else{
                iras_counter++;
            }
        }
        scanner.close();
    }

    private void hetedik_feladat() {
        System.out.println("7. feladat");
        hahi[] sorozat_tarolo = new hahi[1000];

        int counter_A = 0; //FFFF
        int counter_B = 0; //FFFI

        for(int i = 0; i < sorozat_tarolo.length; i++){
            sorozat_tarolo[i] = new hahi();
            sorozat_tarolo[i].sorozat[0] = randominator();
            sorozat_tarolo[i].sorozat[1] = randominator();
            sorozat_tarolo[i].sorozat[2] = randominator();
            sorozat_tarolo[i].sorozat[3] = randominator();
            if(sorozat_tarolo[i].sorozat[0].equals("F") && sorozat_tarolo[i].sorozat[1].equals("F") && sorozat_tarolo[i].sorozat[2].equals("F")){
                if(sorozat_tarolo[i].sorozat[3].equals("F")){
                    counter_A++;
                }else{
                    counter_B++;
                }
            }
        }

        try{
            writer.write("FFFF: "+counter_A+", FFFI: "+counter_B);
            writer.newLine();
            for(int i = 0; i < sorozat_tarolo.length; i++){
                writer.write(sorozat_tarolo[i].sorozat[0]+sorozat_tarolo[i].sorozat[1]+sorozat_tarolo[i].sorozat[2]+sorozat_tarolo[i].sorozat[3]+" ");
            }
            writer.flush();
            writer.close();

        }catch(IOException err){
            System.out.println("Output error.");
        }
    }

    private void hatodik_feladat() throws IOException {
        String current_line;
        String last_line="";
        int i = 0;
        int length=1;
        int index=0;

        int max_length=1;
        int max_index=0;

        reader = new BufferedReader(new FileReader(input));

        while(i < line_counter){
            if(i == 0){
                current_line = reader.readLine();
            }
            else{
                current_line = reader.readLine();
                if(current_line.equals("F")){
                    if(last_line.equals("F")){
                        length++;
                        index = i;
                    }
                }
                else{
                    if(max_length < length){
                        max_length = length;
                        max_index = index;
                    }
                    length = 1;
                    index = 0;
                }
            }
            last_line = current_line;
            i++;
        }
        System.out.println("6. feladat");
        if(max_length != 1 && max_index != 0){
            System.out.println("A leghosszbb ilyen sorozat "+max_length+" hosszu. Kezdo index: "+max_index);
        }
        else{
            System.out.println("Nem volt ilyen.");
        }
    }

    private void otodik_feladat() {
        System.out.println("5. feladat");
        // ez bullshit tesom
    }

    private void negyedik_feladat() {
        // nemtom ket jegyre kerekiteni
        System.out.println("4. feladat");
        System.out.println("A fej dobasanak relativ gyakorsaga "+(float)fej_counter/line_counter+"%");
    }

    private void harmadik_feladat() {
        System.out.println("3. feladat");
        System.out.println(line_counter+" dobasbol allt a kíserlet.");
    }

    private void masodik_feladat() {
        System.out.println("2. feladat");
        System.out.println("Kerem adjon meg egy tippet: (I/F)");
        scanner = new Scanner(System.in);
        String input = scanner.next();
        String test = randominator();
        System.out.println("Gep: "+test);
        if(input.equals(test)){
            System.out.print("On eltalalta.\n");
        }
        else{
            System.out.println("On nemtalalta el.\n");
        }

    }

    private void elso_feladat() {
        System.out.println("1. feladat");
        System.out.println("Egy random feldobás eredménye: "+randominator());
    }

    private String randominator(){
        random = new Random();
        int chance = random.nextInt(99);
        if(chance < 49){
            return "F";
        }else{
            return "I";
        }
    }
}
