import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class anagram {

    anagram(){
        elso();
        masodik();
        harmadik();
        negyedik();
        otodik();
        hatodik();
        hetedik();
    }

    void cout(String text){
        System.out.println(text+"\n");
    }

    String array[] = new String[300];
    int line_c =0;
    int task = 0;
    void elso() {
        task++;
        cout(task+". feladat:");
        cout("Adja meg a szöveget: ");
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();

        char[] asd = in.toCharArray();
        char[] volte = new char[asd.length];

        int unique = 0;

        for(int i = 0; i < asd.length;i++){
            int j = 0;
            while(j < asd.length && asd[i] != volte[j]){
                j++;
            }
            if(j== asd.length){
                volte[unique] = asd[i];
                unique++;
            }
        }

        cout("Ebben "+unique+" kulonbozo karakter talalhato.");
    }

    void masodik(){
        task++;
        cout(task+". feladat:");
        try{
            File input = new File("szotar.txt");
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine())!=null){
                array[line_c] = line;
                line_c++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cout("beolvasva.");

    }

    char[] order(String word){
        char[] asd = word.toCharArray();
        for(int i = 0; i < word.length();i++){
            asd[i] = word.charAt(word.length()-1-i);
        }
        return asd;
    }

    char[] order2(String word){
        char[] asdd = word.toCharArray();
        for(int i = 0; i < word.length();i++){
            for(int j = i; j < word.length();j++){
                if(asdd[i] > asdd[j]){
                    char temp = asdd[i];
                    asdd[i] = asdd[j];
                    asdd[j] = temp;
                }
            }
        }
        return asdd;
    }

    void harmadik() {
        task++;
        cout(task+". feladat:");
        try{
            File output = new File("abc.txt");
            BufferedWriter write = new BufferedWriter(new FileWriter(output));
            for(int i = 0 ; i < line_c ;i++){
                write.write(order2(array[i]));
                write.newLine();
            }
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cout("File kesz.");
    }

    boolean anagramma(String one , String two){

        char[] asd1 = one.toCharArray();
        char[] asd2 = two.toCharArray();

        for(int i = 0; i < asd1.length;i++){
            int j = 0;
            while(j < asd2.length && asd1[i] != asd2[j]){
                j++;
            }
            if(j == asd2.length){
                return false;
            }
        }
        return true;
    }

    void negyedik() {
        task++;
        cout(task+". feladat:");
        cout("Adja meg az első szót: ");
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();
        cout("Adja meg az masodik szót: ");
        String in2 = scan.nextLine();
        if(anagramma(in , in2)){
            cout("Anagramma");
        }
        else{
            cout("Nem anagramma");
        }

    }

    void otodik() {
        task++;
        cout(task+". feladat:");
        cout("Adja meg a szót: ");
        Scanner scan = new Scanner(System.in);
        String in = scan.nextLine();
        boolean van = false;
        for(int i = 0 ; i < line_c;i++){
            if(in.length() == array[i].length()){
                if(anagramma(in, array[i])){
                    cout("Van anagramma: ");
                    van = true;
                    cout(array[i]);
                }
            }
        }
        if(!van){
            cout("Nincs a szotarban anagramma");
        }
    }

    void hatodik() {
        task++;
        cout(task+". feladat:");

        int max_length=0;
        String szow = "";

        String[] szovak = new String[300];
        int counter =0;
        for(int i = 0 ; i < line_c;i++) {
            if(array[i].length() > max_length){
                szow = array[i];
                max_length = szow.length();
                szovak = new String[300];
                counter =0;
                szovak[counter] = szow;
            }
            if(array[i].length() == max_length){
                if(anagramma(szow, array[i])){
                    counter++;
                    szovak[counter] = array[i];
                }
            }
        }
        cout("A leghoszabb szó: "+szow+"\n");
        cout("Anagrammai: (ha voltak)\n");
        for(int i = 0 ; i < counter;i++) {
            cout(szovak[i]);
        }
    }

    void orderByLength(){
        for(int i = 0; i < line_c;i++){
            for(int j = i; j < line_c;j++){
                if(array[i].length() > array[j].length()){
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    void hetedik() {
        task++;
        cout(task+". feladat:");
        orderByLength();
        try{
            File output = new File("rendezve.txt");
            BufferedWriter write = new BufferedWriter(new FileWriter(output));
            String[][] list = new String[line_c][line_c];
            int row=0;
            int col=0;
            int list_of_cols[] = new int[line_c];
            for(int i = 0; i < line_c ; i++){
                col = 0;
                if(array[i].isEmpty()){

                }
                else {
                    list[row][col] = array[i];
                    int j = i + 1;
                    while (j < line_c && array[j].length() == array[i].length()) {
                        if (anagramma(array[j], array[i])) {
                            col++;
                            list[row][col] = array[j];
                            array[j] = "";
                        }
                        j++;
                    }
                    list_of_cols[row] = col;
                    row++;
                }
            }

            for(int i =0 ; i < row;i++){
                String last;
                if(i != 0){
                    last = list[i-1][0];
                }
                else{
                    last = list[i][0];
                }
                int j = 0;
                if(last.length() != list[i][0].length())
                {
                    write.newLine();
                }
                last = list[i][0];
                while(j < list_of_cols[i]){
                    write.write(list[i][j]);
                    write.write(" ");
                    j++;
                }
                write.newLine();

            }

            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cout("file kész.");
    }
}
