/*
Sammenligning.java - 2017-02-04 (yyyy.mm.dd)

Programmet lar bruker gjette p� forskjellige verdier/tegn/ord
*/

import static javax.swing.JOptionPane.*;

class Sammenligning {
	public static void main(String[] args) {

		boolean Sjekk;
		int tall2 		= 4;
		char tegn2		= 's';
		String tekst2	= "skjerm";

		String beskjed 	= "Et hemmelig heltall, et hemmelig tegn, og en hemmelig tekst er blitt valgt. Gjett hva de er!";
		showMessageDialog(null, beskjed);

		//Bruker her tall.equals(tall2) fordi det er datatypen string som blir brukt
		//Hadde jeg brukt char eller int, kunne jeg brukt == istedet
		String tekstTall 	= showInputDialog("Gjett et heltall");
		try {
			int tall = Integer.parseInt(tekstTall);
			Sjekk = tall == tall2;
			beskjed = "Det hemmelige tallet er '4'. Du skrev " + tall + ". Sammenligningsresultatet: " + Sjekk;
		}
		catch (NumberFormatException nfe) {
			int tall = 0;
			beskjed = "Du skrev ikke inn et tall >:(";
		}
		showMessageDialog(null, beskjed);

		String tekstTegn 	= showInputDialog("Gjett et tegn (Om du skriver flere vil det f\u00f8rste velges)");
		char tegn = tekstTegn.charAt(0);
		Sjekk 			= tegn == tegn2;
		beskjed 		= "Det hemmelige tegnet er 's'. Du skrev " + tegn + ". Sammenligningsresultatet: " + Sjekk;
		showMessageDialog(null, beskjed);

		String tekst 	= showInputDialog("Gjett en tekst");
		Sjekk 			= tekst.equals(tekst2);
		beskjed			= "Den hemmelige teksten er 'skjerm'. Du skrev " + tekst + ". Sammenligningsresultatet: " + Sjekk;
		showMessageDialog(null, beskjed);
	}
}
