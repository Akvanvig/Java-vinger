class Bil3Test {
  public static void main(String[] args) {

    /* 1. programsetning: Lager et Bil1-objekt (med referanse) minBil
     vha. standardkonstruktøren: */
    Bil3 minBil = new Bil3("PX12345", "Peugeot", 2004);
    Bil3 dinBil = new Bil3("PP54321", "Opel", 1999);

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
