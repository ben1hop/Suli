import com.sun.jdi.IntegerType;
import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class radio {
    class adat{
        int nap;
        int amator;
        int farkas;
        int kolyok;
        String uzenet;
        boolean hasFarkas;
        boolean befejezett_uz;

        adat(int nap , int amator, String message){
            this.nap = nap;
            this.amator = amator;
            setSecondLine(message);
        }
        boolean isNum(char a){
            if(a >= 0 && a < 10){
                return true;
            }
            return false;
        }
        void setSecondLine(String message){
            uzenet = message;
            char[] array = message.toCharArray();
            if(isNum(array[0])){
                hasFarkas =true;
                farkas = array[0];
                if(isNum(array[2])){
                    kolyok = array[2];
                }
            }
        }
        boolean hasFarkasIntext(){
            return uzenet.contains("farkas");
        }
    }

    int feladat = 0;
    ArrayList<adat> adatok = new ArrayList<>();
    int line_counter=0;

    radio(){
        elso();
        masodik();
        harmadik();
        negyedik();
        otodik();
        hatodik();
        hetedik();
    }

    void cout(String text){
        System.out.println(text);
    }

    private void elso() {
        feladat++;
        cout(feladat+". feladat: ");
        try{
            File input = new File("veetel.txt");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine())!= null){
                String[] values = line.split(" ");
                line = reader.readLine();
                adatok.add(new adat(Integer.parseInt(values[0]), Integer.parseInt(values[1]), line));
                line_counter++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void masodik() {
        feladat++;
        cout(feladat+". feladat: ");
        cout("Elso rogzito :"+adatok.get(0).amator+"\nUtolso rogzito: "+adatok.get(line_counter-1).amator);
    }

    private void harmadik() {
        feladat++;
        cout(feladat+". feladat: ");
        for(int i = 0; i < line_counter;i++){
            if(adatok.get(i).hasFarkasIntext()){
                cout("Nap"+adatok.get(i).nap+" es radios "+adatok.get(i).amator);
            }
        }
    }

    private void negyedik() {
        feladat++;
        cout(feladat+". feladat: ");
        int[] napok = {0,0,0,0,0,0,0,0,0,0,0};

        for(int i = 0;i  < line_counter;i++){
            napok[(adatok.get(i).nap)-1]++;
        }

        for(int j = 0; j < napok.length ;j++){
            cout((j+1)+". nap "+napok[j]+" radioamator");
        }
    }

    private void otodik() {
        feladat++;
        cout(feladat+". feladat: ");
        try{
            File output = new File("adaas.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            class New{
                int nap;
                ArrayList<String> text = new ArrayList<>();
            }
            New[] helyre = new New[11];

            for(int i = 0;i  < line_counter;i++){
                helyre[(adatok.get(i).nap)-1].text.add(adatok.get(i).uzenet);
            }

            for(int j = 0; j < helyre.length ;j++){
                char[] new_array ;
                for(int k = 0; k < helyre[j].text.size(); k++){
                    new_array = helyre[j].text.get(k).toCharArray();
                    
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void hatodik() {
        feladat++;
        cout(feladat+". feladat: ");
        cout("Fuggveny kesz.");
    }

    boolean szame(String asd){
        boolean valasz = true;
        for(int i = 0; i < asd.length();i++){
            if(asd.charAt(i) <'0' || asd.charAt(i)>'9'){
                valasz = false;
            }
        }
        return valasz;
    }

    private void hetedik() {
        feladat++;
        cout(feladat+". feladat: ");
        Scanner scan = new Scanner(System.in);
        cout("Adjon meg egy nap es radios szamot: ");
        int nap = scan.nextInt();
        int radios = scan.nextInt();
        int i =0;
        while(i < line_counter){
            if(adatok.get(i).nap == nap && adatok.get(i).amator == radios){
                if(adatok.get(i).hasFarkas){
                    cout(adatok.get(i).farkas+" db farkas es "+adatok.get(i).kolyok+" db kolykot lattak aznap.");
                }
                else{
                    cout("Nincs informacio");
                }
            }
        }
        if(i == line_counter){
            cout("nincs ilyen feljegyzes");
        }
    }
}
