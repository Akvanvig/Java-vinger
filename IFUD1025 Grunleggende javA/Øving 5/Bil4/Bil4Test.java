class Bil4Test {
  public static void main(String[] args) {

    /* 1. programsetning: Lager et Bil1-objekt (med referanse) minBil
     vha. standardkonstruktøren: */
    Bil4 minBil = new Bil4("PX12345", "Peugeot", 2004);
    Bil4 dinBil = new Bil4("PP54321", "Opel", 1999);

    /*
    2. programsetning: Skriver ut innholdet i minBils objektvariabler:
    Legger her selve utskriften til egen funksjon for å gjøre det lettere å gjennomføre tester
    */
    Skriv_ut(minBil, dinBil);

    /*3. Tester fartsendringsfunksjoner */
    minBil.start();
    dinBil.okFart(30);
    Skriv_ut(minBil, dinBil);
    minBil.okFart(80);
    dinBil.start();
    Skriv_ut(minBil, dinBil);
    minBil.brems(90);
    minBil.okFart(230);
    minBil.brems(20);
    dinBil.okFart(20);
    Skriv_ut(minBil, dinBil);
    minBil.brems(60);
    dinBil.stopp();
    Skriv_ut(minBil, dinBil);

  }

//funksjon som kalles for å skrive ut info om bilene
  public static void Skriv_ut(Bil4 minBil, Bil4 dinBil) {
    System.out.println("Min Bil - regnr " + minBil.getRegnr()
        + ", merke " + minBil.getMerke()
        + ", \u00e5rsmodell " + minBil.getArsmodell()
        + ", hastighet " + minBil.getHastighet()
        + ", motorenIGang " + minBil.isMotorenIGang());
    System.out.println("Din Bil - regnr " + dinBil.getRegnr()
        + ", merke " + dinBil.getMerke()
        + ", \u00e5rsmodell " + dinBil.getArsmodell()
        + ", hastighet " + dinBil.getHastighet()
        + ", motorenIGang " + dinBil.isMotorenIGang());
    System.out.println("");
  }
}
