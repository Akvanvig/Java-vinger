/*
Tidkonvertering.java - 2017-02-04 (yyyy.mm.dd)

Programmet beregner brukervalgte tall for sekunder, minutter og timer, og gjør dette om til sekunder
*/

import static javax.swing.JOptionPane.*;

class Tidkonvertering {
	public static void main(String[] args) {
		String utskrift = "";
		int timer 	= 0;
		int min 	= 0;
		int sek 	= 0;

		String tTimer	= showInputDialog("Skriv inn antall timer");
		String tMin		= showInputDialog("Skriv inn antall minutter");
		String tSek		= showInputDialog("Skriv inn antall sekunder");

		//Sjekker om bruker har skrevet inn tall
		try {
			timer 	= Integer.parseInt(tTimer);
			min 	= Integer.parseInt(tMin);
			sek 	= Integer.parseInt(tSek);
		}
		catch (NumberFormatException nfe) {
			utskrift = "Du har skrevet inn noe feil";
			showMessageDialog(null, utskrift);
		}

		//Regner om til Sekunder
		timer 	= timer * 60 * 60;
		min 	= min * 60;
		sek 	= timer + min + sek;

		//Skriver til bruker (Vil vises uansett, men bare gi riktig svar ved riktige verdier)
		utskrift = tTimer + " timer, " + tMin + " minutter, og " + tSek + " sekunder = " + sek + " sekunder.";
		showMessageDialog(null, utskrift);
	}
}