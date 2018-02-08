/*
 * Kortstokk
 */
class Kortstokk {
  private static final java.util.Random KORTSTOKKEN = new java.util.Random(); // Se delkapittel 8.4 og s. 275
  private static final int STOR_AS_TALLVERDI = (int) 'A'; // A's tallverdi i Unicode-tegnsettet (s. 62, 921)
  private static final int ANTALL_BOKSTAVER = 26; // f.o.m. A t.o.m. Z

  /*
   * Bruker Random til å lage en bokstav (dvs. et kort)
   */
  public char finnKort() {
    int tall = KORTSTOKKEN.nextInt(ANTALL_BOKSTAVER) + STOR_AS_TALLVERDI;
        // nextInt() returnerer et heltall i intervallet [0, ANTALL_BOKSTAVER - 1]
    return (char) tall;
  }
} // Kortstokk
