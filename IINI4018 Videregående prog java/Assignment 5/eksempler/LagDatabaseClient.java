/*
 * LagDatabaseClient.java
 *
 * Programmet lager en eksempeldatabasen ved � kontakte databasetjeneren.
 */
import java.sql.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

class LagDatabaseClient {
  public static void main(String[] args) throws Exception {
    String brukernavn = DataLeser.lesTekst("Brukernavn: ");  // DataLeser, se nedenfor
    String passord = DataLeser.lesPassord();

    String databasedriver = "com.mysql.jdbc.Driver";
    Class.forName(databasedriver);  // laster inn driverklassen

    String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + brukernavn + "?user=" + brukernavn + "&password=" + passord;
    Connection forbindelse = DriverManager.getConnection(databasenavn);

    Statement setning = forbindelse.createStatement();

    try {
      setning.executeUpdate("drop table person");
      System.out.println("Har slettet tabellen person");
    } catch (SQLException e) {  // feiler dersom tabellen ikke eksisterer p� forh�nd
       System.out.println("Fors�kte � slette tabellen person, men fant den ikke.");
    }

    setning.executeUpdate("create table person(persnr integer primary key, " +
      "fornavn varchar(30) not null, etternavn varchar(30) not null)");
    System.out.println("Har laget tabellen person");

    setning.executeUpdate("insert into person values (100, 'Ole', 'Hansen')");
    setning.executeUpdate("insert into person values (101, 'Anne Grethe', '�s')");
    setning.executeUpdate("insert into person values (102, 'Jonny', 'Hansen')");
    System.out.println("Har lagt inn tre rader i tabellen. ");
    setning.close();
    forbindelse.close();
  }
}

class DataLeser {
  /**
   * Leser passord fra brukeren.
   * Teksten "trimmes" f�r den returneres til klienten.
   */
  public static String lesPassord() {
    JLabel jPassword = new JLabel("Passord: "); // forenklet: http://www.asjava.com/swing/joptionpane-showinputdialog-with-password/
    JTextField password = new JPasswordField();
    Object[] obj = {jPassword, password};
    showConfirmDialog(null, obj, "Please input password for JOptionPane showConfirmDialog", OK_CANCEL_OPTION);
     return password.getText().trim();
  }

  /**
   * Leser en tekst fra brukeren. Blank tekst godtas ikke.
   * Teksten "trimmes" f�r den returneres til klienten.
   */
  public static String lesTekst(String ledetekst) {
    String tekst = showInputDialog(ledetekst);
    while (tekst == null || tekst.trim().equals("")) {
      showMessageDialog(null, "Du m� oppgi data.");
      tekst = showInputDialog(ledetekst);
    }
    return tekst.trim();
  }
}
/* Utskrift (n�r tabellen person eksisterer p� forh�nd):
Har slettet tabellen person
Har laget tabellen person
Har lagt inn tre rader i tabellen.
*/