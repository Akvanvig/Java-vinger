/*
	Øving 8
	13.04.2018
	Anders Kvanvig
*/
import java.util.*;
import java.io.*;

class TestTribune {
    private static ArrayList<Tribune> tribuner = new ArrayList<Tribune>();
    private static String[] navn = {"Albert", "Julius", "Reidar"};

    public static void skrivTilFil() {
        try {
            ObjectOutputStream utStrom = new ObjectOutputStream(new FileOutputStream("resultat.ser"));
            utStrom.writeObject(tribuner);
            utStrom.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Tribune> lesFraFil() {
        ArrayList<Tribune> resultat = new ArrayList<Tribune>();
        try {
            ObjectInputStream innStrom = new ObjectInputStream(new FileInputStream("resultat.ser"));
            try {
                while (true) {
                    resultat = (ArrayList<Tribune>) innStrom.readObject();
                }
            } catch (EOFException ex) {

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            innStrom.close();
            return resultat;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        //Oppretter tribuner
        tribuner.add(new Staa("Tribune 1", 200, 30));
        tribuner.add(new Staa("Tribune 2", 50, 35));
        tribuner.add(new Sitte("Tribune 3", 120, 50, 4));
        tribuner.add(new VIP("VIP Tribune 1", 20, 100, 4));

        //Skaffer billetter
        ArrayList<Billett> billetter = new ArrayList<Billett>();
        for (Tribune tribune : tribuner) {
            billetter.addAll(Arrays.asList(tribune.kjopBilletter(navn)));
        }

        //Skriver ut billetter og tribuneinfo
        for (int i = 0; i < billetter.size(); i++) {
            if (i % 3 == 0) {
                System.out.println(tribuner.get(i/3));
            }
            System.out.println(billetter.get(i));
        }

        Collections.sort(tribuner);
        for (Tribune t : tribuner) {
            System.out.println(t);
        }

        skrivTilFil();
        ArrayList<Tribune> liste = lesFraFil();
        System.out.println(liste);
    }
}

/*
For å gjøre Tribune-klassen kompatibel med Array.sort(), må det gjøres følgende:
    - klassehode må få tillegget: implements Comparable<Tribune>
    - Det må legges inn en funksjon: public int compareTo(Tribune denAndre)

For å skrive den til fil, ville jeg serialisert arraylisten og så skrevet den til fil.
    - klassehode må få tillegget: implements Serializable
*/
