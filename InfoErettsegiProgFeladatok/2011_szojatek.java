import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class szavak {
    szavak(){
        elso();
        masodik();
        //harmadik();
        negyedik();
        otodik();
    }
    int task_num =0;
    File input = new File("szoveg.txt");

    //4.
    ArrayList<String> szavak = new ArrayList<>();

    void cout(String txt)
    {
        System.out.println(txt);
    }

    boolean isMagan(char a){
        return a == 'a' || a == 'b'|| a == 'e'|| a == 'i'|| a == 'o'|| a == 'u';
    }

    private void elso() {
        task_num++;
        cout(task_num+". feladat.");
        cout("Adjon meg egy szot: ");
        Scanner scan = new Scanner(System.in);
        String szo = scan.nextLine();
        for(int i = 0; i < szo.length();i++){
            if(isMagan(szo.charAt(i))){
                cout("Van benne magánhangzó.");
                return;
            }
        }
        cout("Nincs benne magánhangzó.");
        return;
    }

    private void masodik() {
        cout("2. feladat.");
        String szo = "";
        int length = 0;

        // masodik
        int talalt = 0;
        int line_c = 0;
        float szazalek = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine())!=null){
                line_c++;
                // elso
                if(line.length() > length){
                    length = line.length();
                    szo = line;
                }
                // masodik
                talalt += harmadik(line);

                // harmadik
                if(line.length() == 5){
                    szavak.add(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // elso
        cout("A leghosszabb szó: "+szo+" "+length+" hosszú.");

        // masodik
        cout("3. feladat.");
        szazalek = (float)talalt / (float)line_c;
        String szaz = String.format("%.2f", szazalek);
        cout(talalt+"/"+line_c+" : "+szaz+"%");
    }

    private int harmadik(String line) {
        int szoHossz = line.length();
        int mgnH = 0;
        for(int i = 0; i < line.length();i++){
            if(isMagan(line.charAt(i))){
                mgnH++;
            }
        }
        if(mgnH > (szoHossz-mgnH)){
            return 1;
        }
        return 0;
    }

    private void negyedik() {
        cout("4. feladat.");
        Scanner scan = new Scanner(System.in);
        cout("Adjon meg egy szórészlet: ");
        String szoreszlet = scan.nextLine();

        ArrayList<String> letraszavak = new ArrayList<>();

        for(int i = 0; i < szavak.size();i++){
            if(szoreszlet.charAt(0) == szavak.get(i).charAt(1) &&
                szoreszlet.charAt(1) == szavak.get(i).charAt(2) &&
                szoreszlet.charAt(2) == szavak.get(i).charAt(3)
            ){
                letraszavak.add(szavak.get(i));
            }
        }

        for(int i = 0; i < letraszavak.size();i++){
            System.out.print(letraszavak.get(i)+" ");
        }
        cout("");
    }
    String middle(String word){
        String value = String.valueOf(word.charAt(1))+String.valueOf(word.charAt(2)+String.valueOf(word.charAt(3)));
        return value;
    }

    private void otodik() {
        cout("5. feladat.");
        try{
            File output = new File("letra.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            ArrayList<String> hasznalt_letra = new ArrayList<>();

            for(int i = 0; i < szavak.size();i++){
                int j = 0;
                while( j < hasznalt_letra.size() && hasznalt_letra.get(j) != middle(szavak.get(i))){
                    j++;
                }
                if(j == hasznalt_letra.size()){
                    boolean hasPair = false;
                    hasznalt_letra.add(middle(szavak.get(i)));
                    for(int k = i+1; k < szavak.size();k++){
                        if(middle(szavak.get(k)).equals(middle(szavak.get(i))) ){
                            if(!hasPair){
                                writer.write(szavak.get(i));
                                writer.newLine();
                                writer.write(szavak.get(k));
                                writer.newLine();
                            }
                            else{
                                writer.write(szavak.get(k));
                                writer.newLine();
                            }
                            hasPair = true;
                        }
                    }
                    if(hasPair){
                        writer.newLine();
                    }
                }
            }
        writer.flush();
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
