//Legger inn ti usorterte kort, og sorterer så disse etter verdien på kortet.

class Kortsortering {
  public static void main(String[] args) {
    //Skriver ned noen kortverdier for å teste med
    int[] valoerKort = {13, 9, 5, 13, 1, 4, 10, 5, 2, 6};
    String[] fargeKort = {"Hjerte", "Kl\u00f8ver", "Spar", "Ruter", "Hjerte", "Kl\u00f8ver", "Spar", "Ruter", "Hjerte", "Kl\u00f8ver"};

    //Oppretter kortene
    Kort[] kortene = new Kort[10];

    System.out.println("Kortene usortert:");
    //Skriver verdiene til kortene, for så å skrive ut med toString() funksjonen
    for (int i = 0; i < 10; i++) {
      kortene[i] = new Kort(fargeKort[i], valoerKort[i]);
      System.out.println(kortene[i].toString());
    }

    System.out.println("");
    System.out.println("Kortene sortert:");
    //Sorterer lista, for så å skrive ut med toString() funksjonen
    java.util.Arrays.sort(kortene);
    for (Kort kortet : kortene) {
      System.out.println(kortet.toString());
    }
  }
}
