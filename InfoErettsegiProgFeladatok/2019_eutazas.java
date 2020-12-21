import java.io.*;

public class eutazas {

    // KONSTRUKTOR ES DATA TIPUS
    // *----------------------------------------------------------
    class Data{
        Data(int a,String date, int id , String type , int duration){
            megallo = a;
            String[] usedate = date.split("-");
            use_date = Integer.parseInt(usedate[0]);
            use_hour = Integer.parseInt(usedate[1]);
            this.id  = id;
            this.type = type;
            this.duration = duration;
        }
        int megallo;
        int use_date;
        int use_hour;
        int id;
        String type;
        int duration;

    }
    eutazas(){
        task_c++;
        elso();
        task_c++;
        masodik();
        task_c++;
        harmadik();
        task_c++;
        negyedik();
        task_c++;
        otodik();
        cout("Fuggveny elkeszült.");
        hetedik();
        cout("File elkeszült.");
    }
    // *----------------------------------------------------------


    // SEGEDFUGGVENYEK
    // *----------------------------------------------------------
    void cout(String txt){
        System.out.println(txt);
    }
    boolean ervenyes(Data value){
        if(value.type.equals("JGY")){
            return value.duration > 0;
        }
        else{
            return value.duration >= value.use_date;
        }
    }
    boolean kedvezmenyes(String value){
        switch(value){
            case "TAB":
            case "NYB":
                return true;
            default:
                return false;
        }
    }
    boolean ingyenes(String value){
        switch(value){
            case "NYP":
            case "RVS":
            case "GYK":
                return true;
            default:
                return false;
        }
    }
    // *----------------------------------------------------------


    // FELADAT FUGGVENYEK NOVEKVO SORRENDBEN
    // *----------------------------------------------------------
    private void elso() {
        cout(task_c+". feladat");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            while((line = reader.readLine())!= null){
                String[] split = line.split(" ");
                adatok[line_c] = new Data(Integer.parseInt(split[0]), split[1] , Integer.parseInt(split[2]) , split[3] , Integer.parseInt(split[4]));
                line_c++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void masodik() {
        cout(task_c+". feladat");
        cout(line_c+" utas szerett volna felszállni a buszra.");
    }

    private void harmadik() {
        cout(task_c+". feladat");
        int num = 0;
        for(int i = 0; i < line_c;i++){
            if(!ervenyes(adatok[i])){
                num++;
            }
        }
        cout("A buszra "+num+" utas nem szállhatott fel.");
    }

    private void negyedik() {
        cout(task_c+". feladat");
        int max = 0;
        int index  =0;
        for(int i = 0; i < line_c ;i++){
            int j = i+1;
            int step = 1;
            while( j < line_c && adatok[j].megallo == adatok[i].megallo){
                j++;
                step++;
            }
            if(step > max){
                max = step;
                index = adatok[i].megallo;
            }
            i = j-1;
        }

        cout("A legtöbb utas ("+max+" fő) a "+index+". megállóban próbált felszállni. ");
    }

    private void otodik() {
        cout(task_c+". feladat");
        int ingyenes = 0;
        int kedv = 0;
        for(int i = 0; i < line_c ;i++){
            if(ervenyes(adatok[i])){
                if(ingyenes(adatok[i].type)){
                    ingyenes++;
                }
                if(kedvezmenyes(adatok[i].type)){
                    kedv++;
                }
            }
        }
        cout("Ingyenesen utazók száma: "+ingyenes+" fő");
        cout("A kedvezményesen utazók száma: "+kedv+" fő");
    }

    public int napokszama(int e1 , int h1 , int n1 , int e2 , int h2 , int n2) {
        h1 = (h1 + 9) % 12;
        e1 = e1 - h1 / 10;
        int d1 = 365*e1 + e1 / 4 - e1 % 100 + e1 / 400 + (h1*306 +5) / 10 +n1 -1;
        h2 = (h2 + 9) % 12;
        e2 = e2 -h2 / 10;
        int d2 = 365*e2 +e2 / 4 - e2 / 100 +e2 / 400 + (h2*306 +5) / 10 + n2 -1;
        return d2-d1;

        /*Függvény napokszama(e1:egész, h1:egész, n1: egész, e2:egész, h2: egész, n2: egész): egész
        h1 = (h1 + 9) MOD 12
        e1 = e1 - h1 DIV 10
        d1= 365*e1 + e1 DIV 4 - e1 DIV 100 + e1 DIV 400 + (h1*306 + 5) DIV 10 + n1 - 1
        h2 = (h2 + 9) MOD 12
        e2 = e2 - h2 DIV 10
        d2= 365*e2 + e2 DIV 4 - e2 DIV 100 + e2 DIV 400 + (h2*306 + 5) DIV 10 + n2 - 1
        napokszama:= d2-d1
        Függvény vége
      */

    }

    private void hetedik() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("figyelmeztetes.txt"));
            for(int i = 0 ; i < line_c;i++){
                if(ervenyes(adatok[i] )&& !adatok[i].type.equals("JGY")){
                    if(adatok[i].duration - adatok[i].use_date <=3 && adatok[i].duration - adatok[i].use_date >=0){
                        String date = String.valueOf(adatok[i].duration);
                        String newdate = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
                        writer.write(adatok[i].id+" "+newdate);
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
    // *----------------------------------------------------------


    // OSZTALYVALTOZOK
    // *----------------------------------------------------------
    File input = new File("utasadat.txt");
    Data[] adatok = new Data[2000];
    int line_c = 0;
    int task_c = 0;
    // *----------------------------------------------------------

}


