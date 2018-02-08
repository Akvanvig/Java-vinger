class Bil1Test {
  public static void main(String[] args) {

    /* 1. programsetning: Lager et Bil1-objekt (med referanse) minBil
     vha. standardkonstruktøren: */
    Bil1 minBil = new Bil1();
    Bil1 dinBil = new Bil1();

    /* 2. programsetning: Skriver ut innholdet i minBils objektvariabler: */
    System.out.println("Min Bil - regnr " + minBil.regnr
        + ", merke " + minBil.merke
        + ", \u00e5rsmodell " + minBil.årsmodell
        + ", hastighet " + minBil.hastighet
        + ", motorenIGang " + minBil.motorenIGang);
    System.out.println("Din Bil - regnr " + dinBil.regnr
        + ", merke " + dinBil.merke
        + ", \u00e5rsmodell " + dinBil.årsmodell
        + ", hastighet " + dinBil.hastighet
        + ", motorenIGang " + dinBil.motorenIGang);

    /*3. Endrer hastighet: */
    minBil.hastighet = 111;
    dinBil.hastighet = 222;

    /* 4. programsetning: Skriver ut innholdet i bilenes objektvariabler: */
    System.out.println("Min Bil - regnr " + minBil.regnr
        + ", merke " + minBil.merke
        + ", \u00e5rsmodell " + minBil.årsmodell
        + ", hastighet " + minBil.hastighet
        + ", motorenIGang " + minBil.motorenIGang);
    System.out.println("Din Bil - regnr " + dinBil.regnr
        + ", merke " + dinBil.merke
        + ", \u00e5rsmodell " + dinBil.årsmodell
        + ", hastighet " + dinBil.hastighet
        + ", motorenIGang " + dinBil.motorenIGang);
  }
}
