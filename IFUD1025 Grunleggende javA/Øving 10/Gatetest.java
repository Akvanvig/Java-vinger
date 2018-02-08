/**
  * Gatetest
  *
  */
class Gatetest {
  public static void main(String[] args) {
    Gate gaten1 = new Gate("Solgaten", 10); // kaller den første konstruktøren
    gaten1.kast(5, 55);
    gaten1.kast(1, 110);
    gaten1.kast(3, 600);
    gaten1.kast(2, 1500);
    gaten1.kast(7, 1090);
    gaten1.kast(8, 100);
    gaten1.kast(10, 550);
    gaten1.kast(5, 1000);
    System.out.println(gaten1);
    if (gaten1.finsToFulleVedSidenAvHverandre(false)) {
      System.out.println("To fulle ved siden av hverandre på odde siden!");
    }
    if (gaten1.finsToFulleVedSidenAvHverandre(true)) {
      System.out.println("To fulle ved siden av hverandre på like siden!");
    }

    System.out.println("\nDet kastes mer søppel (880 i dunk 3)");
    gaten1.kast(3, 880);
    System.out.println(gaten1);
    if (gaten1.finsToFulleVedSidenAvHverandre(false)) {
      System.out.println("To fulle ved siden av hverandre på odde siden!");
    }
    if (gaten1.finsToFulleVedSidenAvHverandre(true)) {
      System.out.println("To fulle ved siden av hverandre på like siden!");
    }

    System.out.println("\nTømmer odde siden");
    gaten1.tom(false);
    System.out.println(gaten1);
    if (gaten1.finsToFulleVedSidenAvHverandre(false)) {
      System.out.println("To fulle ved siden av hverandre på odde siden!");
    }
    if (gaten1.finsToFulleVedSidenAvHverandre(true)) {
      System.out.println("To fulle ved siden av hverandre på like siden!");
    }

    System.out.println("\nDet kastes mer søppel (100 i dunk 2; 880 i dunk 3; 1500 i dunk 4; 550 kg i 9)");
    gaten1.kast(3, 880);
    gaten1.kast(9, 550);
    gaten1.kast(2, 100);
    gaten1.kast(4, 1500);
    System.out.println(gaten1);
    if (gaten1.finsToFulleVedSidenAvHverandre(false)) {
      System.out.println("To fulle ved siden av hverandre på odde siden!");
    }
    if (gaten1.finsToFulleVedSidenAvHverandre(true)) {
      System.out.println("To fulle ved siden av hverandre på like siden!");
    }

    System.out.println("\nTømmer like siden");
    gaten1.tom(true);
    System.out.println(gaten1);
    if (gaten1.finsToFulleVedSidenAvHverandre(false)) {
      System.out.println("To fulle ved siden av hverandre på odde siden!");
    }
    if (gaten1.finsToFulleVedSidenAvHverandre(true)) {
      System.out.println("To fulle ved siden av hverandre på like siden!");
    }

    int[] vektene2 = {1, 2, 3, 4, 5, 6};
    Gate gaten2 = new Gate("Brattenga", vektene2); // kaller den andre konstruktøren
    System.out.println("\n\n" + gaten2);
    vektene2[3] = 333;
    System.out.println("Pga. dyp kopiering i konstruktøren blir ikke Brattenga endret:");
    System.out.println(gaten2);
    int[] vektene3 = gaten2.getVektene();
    vektene3[3] = 666;
    System.out.println("Pga. dyp kopiering i getVektene() blir ikke Brattenga endret:");
    System.out.println(gaten2);
  }
} // Gatetest
