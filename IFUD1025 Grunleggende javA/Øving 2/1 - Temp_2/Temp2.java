/*
Temp.java - 2017-02-04 (yyyy.mm.dd)

Programmet beregner brukervalgt tall fra fahrenheit til Celsius
*/

import static javax.swing.JOptionPane.*;

class Temp2 {
	public static void main(String[] args) {

		String utskrift = "";
		double fahrenheit = 0.0;
		String tall = showInputDialog("Skriv inn temperatur i fahrenheit");

		//Prøver å ta hva bruker har skrevet inn, og gjøre utregninger
		try {
			fahrenheit = Double.parseDouble(tall);
			double celsius = (fahrenheit - 32) * 5 / 9;
			utskrift = fahrenheit + " grader Fahrenheit er " + celsius + " grader Celsius.";
		}

		//Om konvertering til tall feiler, vil programmet gi varsel til bruker
		catch (NumberFormatException nfe) {
			utskrift = "Du skrev ikke inn ett tall";
		}
		//Skriver til bruker
		showMessageDialog(null, utskrift);
	}
}