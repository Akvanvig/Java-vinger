/*

  Klassediagram:
   ___________________________________
  | Aar                              |
  |----------------------------------|
  | -int year {readonly}             |
  |----------------------------------|
  | +Aar(int year)                   |
  | +int getAar()                    |
  | +int beregnIFjor()               |
  | +int beregnNesteAar()            |
  | +int beregnOmNoenAar(int tall)   |
  | +boolean isSkuddAar()            |
  |__________________________________|


*/

class Aar {
  private int år;

  Aar(int år) {
    this.år = år;
  }

  public int getAar() {
    return år;
  }

  public int beregnIFjor() {
    return (år - 1);
  }

  public int beregnNesteAar() {
    return (år + 1);
  }

  public int beregnOmNoenAar(int antall) {
    return (år + antall);
  }

  public boolean isSkuddaar() {
    boolean skuddaar;

    //Sjekker om det er kan divideres på 4
    if (år % 4 == 0) {
      //Sjekker om det kan divideres på 100
      if (år % 100 == 0) {
        //Sjekker om det kan divideres på 400
        if (år % 400 == 0) {
          skuddaar = true;
        }
        else {
          skuddaar = false;
        }
      }
      else {
        skuddaar = true;
      }
    }
    else{
      skuddaar = false;
    }

    return skuddaar;
  }
}
