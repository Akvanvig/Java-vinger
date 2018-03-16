/*
	Øving 5
	16.03.2018
	Anders Kvanvig
*/

import java.sql.*;

class DbKobling{
    private String dbNavn;
    private String dbDriver;

    //Oppretter Streng for å kommunisere med databasen
    public DbKobling(String brukernavn, String passord){
        dbDriver = "com.mysql.jdbc.Driver";
        dbNavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + brukernavn + "?user=" + brukernavn + "&password=" + passord + "&autoReconnect=true&useSSL=false";
        try {
            Class.forName(dbDriver);
        }
        catch (Exception e) {
            System.out.println("feil :)");
        }
    }

    public void testDbKobling() throws SQLException{
        Connection forbindelse = DriverManager.getConnection(dbNavn);
        forbindelse.close();
    }


    //Oppgave b
    public String[] hentBokInfo(String isbn) throws Exception{
        //Kjører en liten sjekk for å verifisere isbn-koden
        if (isbn.length() != 13 || isbn.length() - isbn.replace("-", "").length() != 3) {
            throw new Exception("Ugyldig isbn-kode");
        }

        String sporring = "SELECT t.forfatter, t.tittel, COUNT(e.isbn) antall FROM boktittel t JOIN eksemplar e ON t.isbn = e.isbn WHERE t.isbn ='" + isbn + "'";

        //Kobler opp mot database, og sender spørring
        Connection forbindelse = DriverManager.getConnection(dbNavn);
        Statement setning = forbindelse.createStatement();
        ResultSet res = setning.executeQuery(sporring);
        //Leser resultat fra spørring
        String[] resultat = new String[3];
        if (res.next()) {
            resultat[0] = res.getString("tittel");
            resultat[1] = res.getString("forfatter");
            resultat[2] = "" + res.getInt("antall");
        }
        res.close();
        setning.close();
        forbindelse.close();
        return resultat;
    }


    //Oppgave c
    //Returnerer true om boken er oppdatert
    public boolean regLaanetaker(String navn, String isbn, int eksemplar) throws Exception {
        //Kjører en liten sjekk for å verifisere isbn-koden
        if (isbn.length() != 13 || isbn.length() - isbn.replace("-", "").length() != 3) {
            throw new Exception("Ugyldig isbn-kode");
        }
        String sporring = "UPDATE eksemplar SET laant_av = '" + navn + "' WHERE isbn = '" + isbn + "' AND eks_nr = '" + eksemplar + "' AND laant_av IS NULL";
        //kobler opp mot database og endrer hvis krav er oppfylt
        Connection forbindelse = DriverManager.getConnection(dbNavn);
        Statement setning = forbindelse.createStatement();
        int res = setning.executeUpdate(sporring);
        setning.close();
        forbindelse.close();
        return res == 1;
    }
}


//Testprogram
class DBTest {
    public static void main(String args[]) {
        //test DbKobling
        DbKobling test = new DbKobling("andekva", "HPLc157c");
        try {
            test.testDbKobling();
        }
        catch (SQLException e) {
            System.out.println("Kan ikke koble til database");
        }

        System.out.println();

        //Test hent hentBokInfo()
        String[] testIsbn = {"0-201-50998-X", "kk", "4-442-36435-3"};
        for (int i = 0; i<3;i++) {
            try{
                String[] temp = test.hentBokInfo(testIsbn[i]);
                if (temp[0] != null) {
                    System.out.println("Tittel: " + temp[0] + "\nForfatter: " + temp[1] + "\nAntall: " + temp[2]);
                }
                else {
                    System.out.println("Ingen bøker ved gitt navn");
                }
            }
            catch (Exception e) {
                System.out.println(e + "isbn");
            }
        }

        System.out.println();

        //Test regLaanetaker()
        String[][] testreg = {{"Hans", "Elise", "Erik"}, {"0-201-50998-X", "0-201-50998-X", "4-442-36435-3"}};
        for (int i = 0; i < 3; i++) {
            try {
                System.out.println(test.regLaanetaker(testreg[0][i], testreg[1][i], 1));
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
