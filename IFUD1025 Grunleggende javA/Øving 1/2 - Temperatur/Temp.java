/*
Temp.java - 2017-02-04 (yyyy.mm.dd)

Programmet beregner hardkodet tall fra Fahrenheit til Celsius
*/

class Temp {
	public static void main(String[] args) {
		double Fahrenheit = 98.0;
		double Celsius = (Fahrenheit - 32) * 5 / 9;
		System.out.println(Fahrenheit + " grader Fahrenheit er " + Celsius + " grader Celsius.");
	}
}
/*
Viser at 98 grader fahrenheit blir ca 36,67 grader celsius
*/