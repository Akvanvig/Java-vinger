import java.util.*;
import java.io.*;

class Resultat implements Serializable {
    private final String fagNr;
    private final String fagNavn;
    private int antStudiepoeng;
    private char[] karakterer;

    public Resultat(String fagKode, String fagNavn, int antStudiepoeng, char[] karakterer) {
        this.fagNr = fagKode;
        this.fagNavn = fagNavn;
        this.antStudiepoeng = antStudiepoeng;
        this.karakterer = karakterer;
    }

    public String toString() {
        String resultat = fagNr + ",\t" + fagNavn + ",\t\t" + antStudiepoeng;
        for (char karakter: karakterer) {
            resultat += ",\t" + karakter;
        }
        return resultat;
    }

    //Brukes for å skriver ut noe mer ryddig
    public String[] getText() {

        String tekstKarakterer = "";
        for (int i = 0; i < karakterer.length; i++) {
            if (i == 0) {
                tekstKarakterer += karakterer[i];
            }
            else {
                tekstKarakterer += ", " + karakterer[i];
            }
        }
        String[] resultat = {fagNr, fagNavn, "" + antStudiepoeng, tekstKarakterer};

        return resultat;
    }
}

class Filhandtering {
    private ArrayList<Resultat> resultater = new ArrayList<Resultat>();

    //Lese fra csv-fil (txt)
    public void lesFraFil() {
        try {
            String linje;
            InputStreamReader filLeser = new InputStreamReader( new FileInputStream("resultat.txt"), "UTF-8");
            BufferedReader bufretLeser = new BufferedReader(filLeser);
            boolean forsteLinje = true; //Brukes for å fjerne et ekstra spørsmålstegn som dukker opp

            //Leser ei linje av gangen, og splitter opp på komma
            while((linje = bufretLeser.readLine()) != null) {
                if (forsteLinje) {
                    linje = linje.substring(1);
                    forsteLinje = false;
                }
                String[] data = linje.split(",");
                char[] karakterer = new char[data.length - 3];
                //Henter ut karakterer i faget
                for (int i = 3; i < data.length; i++) {
                    karakterer[i - 3] = data[i].charAt(0);
                }
                //Oppretter nytt Resultat-objekt med dataene
                resultater.add(new Resultat(data[0], data[1], Integer.parseInt(data[2]), karakterer));
            }
            bufretLeser.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file ");
        }
        catch(IOException ex) {
            System.out.println("Error reading file ");
        }
    }

    //Skrive til Serialisert fil
    public void serialiseringTilFil() {
        try {
            ObjectOutputStream utStrom = new ObjectOutputStream(new FileOutputStream("resultater.ser"));
            utStrom.writeObject(resultater);
            utStrom.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Lese fra serialisert fil
    public void lesSerialisertFil() {
        try {
            ObjectInputStream innStrom = new ObjectInputStream(new FileInputStream("resultater.ser"));
            try {
                while (true) {
                    resultater = (ArrayList<Resultat>)innStrom.readObject();
                }
            }
            catch (EOFException ex) {
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            innStrom.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printResultat() {
        for (Resultat fag : resultater) {
            System.out.println(fag);
        }
        System.out.println("");
    }

    public void printRes() {
        System.out.printf("%-15s  %-30s %-10s  %-30s%n", "Fagkode", "Fagnavn", "Studpoeng", "Karakterer");
        for (Resultat fag:resultater) {
            String[] tekst = fag.getText();
            System.out.printf("%-15s  %-30s %-10s  %-30s%n", tekst[0], tekst[1], tekst[2], tekst[3]);
        }
    }

    //Testprogram
    //Leser fra original .txt-fil, lagrer til eget Resultat-objekt og skriver dette objektet til ei serialisert fil.
    //Dette leses så og skrives til bruker
    public static void main(String args[]) {
        Filhandtering fil = new Filhandtering();
        fil.lesFraFil();
        fil.printResultat();
        fil.serialiseringTilFil();
        fil.lesSerialisertFil();
        fil.printRes();
    }
}
