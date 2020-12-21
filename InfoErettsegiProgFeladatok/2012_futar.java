import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class futar {

    // adatok strukturaja
    class adatok{
        int nap; // a fuvar napja , 1-7
        int fuvar; // a fuvar sorszama , 1-40 es minden nap 1tol kezd
        int tav; // a fuvar tavolsaga kmben  , <30
        adatok(int n , int f, int t) {
            nap=n;
            fuvar=f;
            tav=t;
        }
    }

    // Filestream & input
    File input = null;
    File output = null;
    BufferedReader reader = null;
    BufferedWriter writer = null;
    Scanner scan = null;

    // Adatok taroloja
    int line_counter = 0;
    ArrayList<adatok> data = new ArrayList<>(10);

    // a konstruktor ami meghivja minden feladat methodusat
    futar(){
        elso();
        masodik();
        harmadik();
        negyedik();
        otodik();
        hatodik();
        hetedik();
        nyolcadik();
        kilencedik();
    }
    // kiiratas
    void cout(String text){
        System.out.println(text);
    }
    // par global valtozo es segito metodus
    int elso_munkanap = 7;
    int utolso_munkanap = 1;

    boolean second_search(adatok current ){
        return current.nap == elso_munkanap && current.fuvar == 1;
    }
    boolean third_search(adatok current , int utolsofuvar){
        return current.nap == utolso_munkanap && current.fuvar == utolsofuvar;
    }

    int jutalom(int i){
        if(i < 3){
            return 500;
        }
        if(i < 6){
            return 700;
        }
        if(i < 11){
            return 900;
        }
        if(i < 21){
            return 1400;
        }
        else{
            return 2000;
        }
    }

    // Feladatok sorrendben
    private void elso() {
        cout("1. feladat:");
        try{
            input = new File("tavok.txt");
            reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine()) != null){
                String[] substring = line.split(" ");
                data.add(line_counter, new adatok(Integer.parseInt(substring[0]), Integer.parseInt(substring[1]), Integer.parseInt(substring[2])));

                // 2. és 3. feladat előkészítése csak h nelegyen annyi ciklus
                if(data.get(line_counter).nap < elso_munkanap){
                    elso_munkanap = data.get(line_counter).nap;
                }
                if(data.get(line_counter).nap > utolso_munkanap){
                    utolso_munkanap = data.get(line_counter).nap;
                }

                line_counter++;
            }
        }
        catch(IOException err){
            System.err.print(err);
        }
    }

    private void masodik() {
        cout("2. feladat:"+elso_munkanap);
        int j = 0;
        while(j < data.size() && !second_search(data.get(j))) {
            j++;
        }
        cout("A elso fuvar tavja: "+data.get(j).tav+" km");
    }

    private void harmadik() {
        cout("3. feladat:"+utolso_munkanap);
        int utolso_fuvar = 1;

        for(int i = 0; i < data.size(); i++){
            if(data.get(i).nap > utolso_fuvar && data.get(i).nap == utolso_munkanap){
                utolso_fuvar = data.get(i).fuvar;
            }
        }
        int j = 0;
        while(j < data.size() && !third_search(data.get(j), utolso_fuvar)){
            j++;
        }
        cout("A utolso fuvar tavja: "+data.get(j).tav+" km");
    }

    private void negyedik() {
        cout("4. feladat:");
        int[] napok = {1 ,2 ,3 ,4 ,5 ,6 ,7};
        boolean[] szabad = new boolean[7];
        for(int i = 0; i < data.size();i++){
            int j = 0;
            while(j < 7 && data.get(i).nap != napok[j]){
                j++;
            }
            if(j < 7){
                szabad[data.get(i).nap-1] = true;
            }
        }
        for(int i = 0; i < 7 ; i++){
            if(!szabad[i]){
                cout("A szabadnapok: "+napok[i]);
            }
        }

    }

    private void otodik() {
        cout("5. feladat:");
        int max_fuvar=0;
        int max_nap=0;

        int fuvarszam = 0;
        for(int i = 1; i < 8; i++){
            for(int j = 0; j < data.size();j++){
                if(data.get(j).nap == i){
                    fuvarszam++;
                }
            }
            if(fuvarszam>max_fuvar){
                max_fuvar = fuvarszam;
                max_nap = i;
            }
            fuvarszam = 0;
        }
        cout("A legtöbb fuvar a "+max_nap+"-ik napon volt: "+max_fuvar);
    }

    private void hatodik() {
        cout("6. feladat:");

        int[] tav = {0,0,0,0,0,0,0};
        for(int i = 0; i < data.size();i++){
            switch(data.get(i).nap){
                case 1:
                    tav[0]+=data.get(i).tav;
                    break;
                case 2:
                    tav[1]+=data.get(i).tav;
                    break;
                case 3:
                    tav[2]+=data.get(i).tav;
                    break;
                case 4:
                    tav[3]+=data.get(i).tav;
                    break;
                case 5:
                    tav[4]+=data.get(i).tav;
                    break;
                case 6:
                    tav[5]+=data.get(i).tav;
                    break;
                case 7:
                    tav[6]+=data.get(i).tav;
                    break;
                default:
                    break;
            }
        }
        for(int i = 0; i < 7 ; i++){
            cout((i+1)+". nap: "+tav[i]+" km");
        }
    }

    private void hetedik() {
        cout("7. feladat:");
        scan = new Scanner(System.in);
        cout("Adjon mge egy ut hosszt: ");
        int tav = scan.nextInt();
        cout("Ezért "+jutalom(tav)+" Ft jár.");

    }

    private void nyolcadik() {
        cout("8. feladat:");
        // rendezes napra
        for(int i = 0; i< data.size()-1; i++){
            for(int j = i+1; j < data.size();j++){
                if(data.get(i).nap > data.get(j).nap){
                    adatok temp = data.get(i);
                    data.set(i , data.get(j));
                    data.set(j, temp);
                }
            }
        }
        // rendezes utra
        for(int i = 0; i< data.size()-1; i++){
            for(int j = i+1; j < data.size();j++){
                if(data.get(j).nap == data.get(i).nap){
                    if(data.get(i).fuvar > data.get(j).fuvar){
                        adatok temp = data.get(i);
                        data.set(i , data.get(j));
                        data.set(j, temp);
                    }
                }
            }
        }


        try{
            output = new File("dijazas.txt");
            writer = new BufferedWriter(new FileWriter(output));
            for(int i = 0; i < data.size();i++){
                writer.write(data.get(i).nap+". nap "+data.get(i).fuvar+". út: "+jutalom(data.get(i).tav)+" Ft\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cout("File elkészült.");
    }

    private void kilencedik() {
        cout("9. feladat:");
        int sum = 0;
        for(int i = 0;i < data.size();i++){
            sum+=jutalom(data.get(i).tav);
        }
        cout("A heti összeg :"+sum);
    }

}
