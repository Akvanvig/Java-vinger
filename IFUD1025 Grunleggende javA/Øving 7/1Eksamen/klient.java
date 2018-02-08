import static javax.swing.JOptionPane.*;

class klient {
  public static void main(String[] args) {
    boolean fortsette = true;
    boolean neste = true;
    final String[] MULIGHETER = {"toString()", "Finn passordstyrke", "start på nytt", "avslutte"};

    while (fortsette){
      String brukernavn = showInputDialog("Skriv inn brukernavn");
      String passord = showInputDialog("Skriv inn passord");
      Bruker person = new Bruker(brukernavn, passord);

      int valg = 0;
      do {
        valg = showOptionDialog(null, "Velg", "Bruker", DEFAULT_OPTION, PLAIN_MESSAGE, null, MULIGHETER, MULIGHETER[0]);

        if (valg == 0) {
          System.out.println(person.toString());
          fortsette = true;
          neste = true;
        }
        else if (valg == 1) {
          System.out.println("Passordstyrken er: " + person.finnPassordStyrken());
          fortsette = true;
          neste = true;
        }
        else if (valg == 2) {
          neste = false;
          fortsette = true;
        }
        else {
          neste = false;
          fortsette = false;
        }
      } while (neste);

    }

  }
}
