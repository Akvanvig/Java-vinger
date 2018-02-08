class Bil2Test {
  public static void main(String[] args) {

    /* 1. programsetning: Lager et Bil1-objekt (med referanse) minBil
     vha. standardkonstruktøren: */
    Bil2 minBil = new Bil2();
    Bil2 dinBil = new Bil2();

    /* 2. programsetning: Skriver ut innholdet i minBils objektvariabler: */
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

    /*3. Endrer hastighet: */
    minBil.setHastighet(111);
    dinBil.setHastighet(222);

    /* 4. programsetning: Skriver ut innholdet i bilenes objektvariabler: */
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
  }
}
