/*
	Øving 1
	11.02.2018
	Anders Kvanvig
*/
import static javax.swing.JOptionPane.*;

class ResTest {
  public static void main(String[] args) {
    String[] muligheter = {"Reserver Bord", "Finn Bordnummer", "Frigi Bord", "Skriv Ut Bord", "Beregn alder", "Avslutt"};
    boolean avslutt = false;
    String navn = showInputDialog("Hva heter restauranten?");
    //Kunne på disse to lagt inn en test for om tall ble ført inn
    int etbAar  = Integer.parseInt(showInputDialog("I hvilket år ble restauranten etablert?"));
    int antBord = Integer.parseInt(showInputDialog("Hvor mange bord har restaranten?"));

    Restaurant res = new Restaurant(navn, etbAar, antBord);

    do {
      int valg = showOptionDialog(null, "Hva ønsker du å gjøre?" , res.getNavn() + " etablert i " + res.getEtbAar(), DEFAULT_OPTION, PLAIN_MESSAGE, null, muligheter, muligheter[0]);
      System.out.println(valg);
      switch (valg) {
        case 0: //Reserver et gitt antall bord
          int antallBord = Integer.parseInt(showInputDialog("Hvor mange bord skal reserveres?"));
          String resNavn = showInputDialog("I hvilket navn skal bordene reserveres?");
          boolean vellykket = res.reserver(antallBord, resNavn);
          if (vellykket) {
            showMessageDialog(null, antallBord + " bord er reservert under navnet " + resNavn);
          }
          else {
            showMessageDialog(null, "Ikke nok ledige bord \n Det er for øyeblikket " + res.antLedige() + " bord ledig.", "Varsel", WARNING_MESSAGE);
          }
          break;

        case 1: //Finn reserverte bordnummer
          int[] resBord = res.getReservasjoner(showInputDialog("Hvilket navn er bordene reservert på?"));
          String respons = "De følgende bordene er reservert på dette navnet: \n";
          boolean forste = true;
          for (int bord:resBord) {
            if (forste) {
              respons += bord;
              forste = false;
            }
            else {
              respons += ", " + bord;
            }
          }
          showMessageDialog(null, respons);

          break;

        case 2: //Frigi bord
          String bord = showInputDialog("Skriv inn bord du ønsker å frigi (Separer bordnummer med komma, ingen andre tegn.)");
          String[] bordeneStr = bord.split(",");
          int[] bordeneInt = new int[bordeneStr.length];
          try {
            for (int i = 0; i < bordeneStr.length; i++) {
              bordeneInt[i] = Integer.parseInt(bordeneStr[i], 10);
            }
            res.frigiBord(bordeneInt);
            showMessageDialog(null, "Bordene har blitt frigjort");
          }
          catch (NumberFormatException nfe) {
            showMessageDialog(null, "Bordnummer må skrives inn på formatet '1,3,4,2'");
          }
          break;

        case 3: //Skriv ut alle bordene
          String[] bordene = res.getBordene();
          String resultat = "";
          for (String bordet:bordene) {
            if (bordet != null) {
              resultat += bordet + ", ";
            }
            else {
              resultat += "[tomt], ";
            }
          }
          showMessageDialog(null, resultat);
          break;

        case 4: //Beregn restaurantens alder
          showMessageDialog(null, res.getNavn() + " ble etablert i år " + res.getEtbAar() + ", dermed er den i år " + res.getAlder() + " år gammel.");
          break;

        case 5: //Avslutt program
          avslutt = true;
          break;
      }
    } while (!avslutt);
  }
}
