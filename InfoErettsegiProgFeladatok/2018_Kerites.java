package com.company;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Kerites {
    class Data {
        boolean paritas; // 0 true 1 false
        int range; // telek range -> osszertek max 1000
        char kerites; // angol abc betui szineknek vagy ":" ha nemkeszult el vagy "#" ha nincs szine

        // Konstruktor
        Data(String line) {
            String[] split = line.split(" ");
            paritas = split[0].equals("0");
            range = Integer.parseInt(split[1]);
            kerites = split[2].charAt(0);
            if (paritas) {
                paros_darab++;
                paros_hossz += range;
            } else {
                paratlan_darab++;
                paratlan_hossz += range;
            }
        }
    }

    public Kerites() throws IOException {
        elso_feladat();
        masodik_feladat();
        harmadik_feladat();
        negyedik_feladat();
        otodik_feladat();
        hatodik_feladat();
    }

    // mivel min 8 meter egy telek , es max 1000 metert tehet ki az ossz ertekuk igy a tarolo tomb merete
    // 1000/8 = 125 egy oldalon => 250
    private Data[] data = new Data[250];
    private int line_counter = 0;

    // Segito valtozok
    private static int paratlan_darab = 0;
    private static int paratlan_hossz = 0;
    private static int paros_hossz = 0;
    private static int paros_darab = 0;

    // Feladatok
    public void elso_feladat() throws IOException {
        // Beolvassuk a file-t es taroljuk a tombben , kozben szamoljuk az osszes lepest a tombon
        File input = new File("kerites.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(input));
        } catch (IOException err) {
            System.err.format("File beolvasási hiba.");
        } finally {
            String line;
            while ((line = reader.readLine()) != null) {
                data[line_counter] = new Data(line);
                line_counter++;
            }
        }

    }

    public void masodik_feladat() {
        System.out.println("Osszesen ennyi telek kelt el: " + line_counter);
    }

    public void harmadik_feladat() {
        // ha paros oldalon volt
        if (data[line_counter - 1].paritas) {
            System.out.println("Az utolso telek paros oldalon volt es a haz szama: " + paros_darab * 2);
        }
        // ha paratlan
        else {
            System.out.println("A utolso telek paratlan oldalon volt , a haz szama: " + ((paratlan_darab * 2) - 1));
        }
    }

    public void negyedik_feladat() {
        // vegig megyunk a tombon
        for (int i = 0; i < line_counter; i++) {
            // az utolso elementnel nem vizsgalunk
            if (i != line_counter - 1) {
                // ha paratlan
                if (!data[i].paritas) {
                    // es a kovetkezo tag is es kerites szinek egyenloek
                    if (data[i].kerites == data[i + 1].kerites && !data[i + 1].paritas) {
                        System.out.println("Az elso paratalan oldali kerites aminek ugyanolyan szine van mint a kovetkezonek: " + (i+1));
                        break;
                    }
                }
            } else {
                System.out.println("Nincs ilyen kerites.");
            }
        }
    }

    public void otodik_feladat() {
        System.out.println("Adjon meg egy hazszamot: ");
        Scanner scan = new Scanner(System.in);
        int hazszam = scan.nextInt(); // haz szam max =: line_counter*2
        // keressuk a tombbeli indexet
        int index=0;
        // megnezzuk hogy paros-e a hazszam es annak mennyi a fele -> ez adja meg h egy oldalon hanyadik lenne a sorban
        boolean paros_e;
        int fele;

        if (hazszam % 2 == 0) {
            paros_e = true;
            fele = hazszam / 2;
        } else {
            paros_e = false;
            fele = (hazszam +1) / 2;
        }

        // vegig megyunk a tombon amig elnem erunk a kivalaszott oldalon a hazsszam feleig
        int oldal_counter = 0;
        for(int i = 0; i < line_counter;i++){
            if(data[i].paritas == paros_e){
                oldal_counter++;
            }
            if(oldal_counter == fele){
                index = i;
                break;
            }
        }

        // A) rész milyen a kerites ezen a hazszamon
        if (data[index].kerites == '#') {
            System.out.println("A kerites szine: ");
        } else if (data[index].kerites == ':'){
            System.out.println("Nincs keritese ennek a teleknek.");
        }else{
            System.out.println("A kerites szine: "+data[index].kerites);
        }

        // B) rész ezt nemteljesen ertem -> nezd meg a megoldast
        // az en probam: feltolteni egy alphabet arrayt meg egy tiltott arrayt a 3 tilos szinbol es exkludalni az utobbit az elsobol
        //char[] tiltott_szinek = {data[index].kerites , data[index-1].kerites , data[index+1].kerites};
        char[] lehetseges_szinek = new char[26];

        for(int i = 0; i < 26; i++){
            lehetseges_szinek[i] = (char)(65 + i);
        }

        char[] hasznalhato_szinek = new char[26];
        int counter =0;

        for(char letter : lehetseges_szinek){
            if(letter != data[index].kerites && letter != data[index-1].kerites && letter != data[index+1].kerites){
                hasznalhato_szinek[counter] = letter;
                counter++;
            }
        }
        // random kiadunk egy elementet a jo listabol
        int random = new Random().nextInt(counter);
        System.out.println("Egy valaszthato szin: "+lehetseges_szinek[random]);

    }

    public void hatodik_feladat() throws IOException {

        // Faljba kiiratas
        File output = new File("utcakep.txt");
        try (FileWriter writer = new FileWriter(output);
             BufferedWriter bw = new BufferedWriter(writer)) {

            // 2. sor egy ' '-t tartalmazo paratlan hossz karakter hosszu tomb lesz aminek a bizonyos helyeire szamokat illesztunk
            char[] second_line = new char[paratlan_hossz];
            for(int i = 0; i < second_line.length;i++){
                second_line[i] = ' ';
            }
            int array_counter = 0;
            int paratlan_lepo= 0;

            for(int i = 0; i < line_counter ; i++) {
                // megnezzuk a tomb osszes paratlan elemet
                if (!data[i].paritas) {
                    // a masodik tomb-nek adunk szamot
                    // megpedig ugy hogy szamoljuk a paratlan belepeseket es azt hany karaktert irtunk le eddig az elso sorban
                    // minden uj belepesnel megnoveljuk a paratlan lepot mivel (paratlan_lepo)*2 -1 adja meg a hazszamot
                    // viszont hozza kell adni egy '0'-t is mivel az atalakitas az ascii szamot adja vissza , nem az aktualis erteket
                    // If you add '0' with int variable, it will return actual value in the char variable. The ASCII value of '0' is 48.
                    // So, if you add 1 with 48, it becomes 49 which is equal to 1. The ASCII character of 49 is 1.
                    // es mielott elkezdjuk az uj karakter sorozatot az elso helyere ( a belepesnel ) atirjuk a masodik listat a nekunk kello ertekre
                    paratlan_lepo++;
                    int k = 0;
                    second_line[array_counter] = (char) (((paratlan_lepo * 2) - 1)+'0');
                    // range alkalommal kiirjuk a kerites szinet
                    while (k < data[i].range) {
                        // elso sor kiiratasa
                        bw.write(data[i].kerites);
                        array_counter++;
                        k++;
                    }

                }
            }

            // masodik sor kiiratasa
            bw.newLine();

            for(int i = 0; i < second_line.length; i++){
                bw.write(second_line[i]);
            }

        } catch (IOException e) {
            System.err.format("Output file hiba.");
        }

        System.out.println("File elkészült.");
    }
}
