/*
Tidkonvertering2.java - 2017-02-04 (yyyy.mm.dd)

Programmet beregner brukervalgt tall for sekunder, og gjør dette om til timer, minutter og sekunder
*/

import static javax.swing.JOptionPane.*;

class Tidkonvertering2 {
	public static void main(String[] args) {

		String tSek = showInputDialog("Skriv inn antall sekunder");
		String beskjed = "";

		int timer 	= 60;
		int min 	= 60;
		int sek 	= 0;

		//Sjekker verdier ført inn av bruker
		try {
			sek = Integer.parseInt(tSek);
		}
		catch (NumberFormatException nfe){
			beskjed = "Du har skrevet inn noe feil";
			showMessageDialog(null, beskjed);
		}

		//Regner om fra sekunder
		min 	= sek / min;
		sek 	= sek - (min * 60);
		timer 	= min / timer;
		min 	= min - (timer * 60);

		//Skriver til bruker
		beskjed = tSek + " sekunder = " + timer + " timer, " + min + " minutter, og " + sek + " sekunder.";
		showMessageDialog(null, beskjed);
	}
}