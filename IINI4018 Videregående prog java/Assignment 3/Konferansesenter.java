/*
	Øving 3
	27.02.2018
	Anders Kvanvig
*/
import java.util.*;
import java.time.*;

class Konferansesenter{
  private ArrayList<Rom> moterom = new ArrayList<Rom>;
  private String navn;

  public Konferansesenter(String navn) {
    this.navn = navn;
  }
  //Returnerer true om rom reserveres, false om ikke ledig
  public boolean reserverRom(LocalDateTime fraTid,LocalDateTime tilTid, int antPers,String navn, int tlfNummer) {

  }

  public void regRom(int romNummer, int persTillatt) {
    return moterom.size();
  }

  //Forutsetter gyldig Indeks
  public Rom getRomIndeks(int romIndeks) {
    return moterom[romIndeks];
  }

  //Forutsetter gyldig romnummer
  public Rom getRom(int romNummer) {
    for (Rom rom:moterom) {
      if (rom.getRomNummer() == romnummer) {
        return rom;
      }
    }
  }

}
