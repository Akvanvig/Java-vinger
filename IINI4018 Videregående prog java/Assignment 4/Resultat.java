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
}

class Filhandtering {
    private ArrayList<Resultat> resultater = new ArrayList<Resultat>();
    private String filSti;

    public Filhandtering(String filSti) {
        this.filSti = filSti;
    }

    public void lesFraFil() {
        try {
            String linje;
            FileReader filLeser = new FileReader(filSti  + "\\resultat.txt");
            BufferedReader bufretLeser = new BufferedReader(filLeser);
            //Leser ei linje av gangen, og splitter opp på komma
            while((linje = bufretLeser.readLine()) != null) {
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
            System.out.println("Unable to open file \"" + filSti + "\"");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \"" + filSti + "\"");
        }
    }

    public void serialiser() {
        /*
        try {
            FileOutputStream filUt = new FileOutputStream("resultater.ser");
            ObjectOutputStream oos = new ObjectOutputStream(filUt);
            oos.writeObject(resultater);
            oos.close();
            filUt.close();
        }
        catch(IOException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        */
        try{
            // Serialize data object to a file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filSti + "\\resultater.ser"));
            out.writeObject(resultater);
            out.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printResultat() {
        for (Resultat fag : resultater) {
            System.out.println(fag.toString());
        }
    }

    public static void main(String args[]) {
        Filhandtering fil = new Filhandtering(args[0]);
        fil.lesFraFil();
        fil.printResultat();
        fil.serialiser();
    }
}
