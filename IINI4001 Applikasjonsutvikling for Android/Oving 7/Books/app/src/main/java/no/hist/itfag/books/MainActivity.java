package no.hist.itfag.books;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private DatabaseManager db;
    private String TAG = "Main_Log";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(android.R.style.Theme_Holo_Light);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            db = new DatabaseManager(this);
            db.clean();
            //Følgende brukes en gang for å skrive bøker/forfattere til dokument
            /*skrivTilFil("navneliste", "Bud Kurniawan;Android Application Development: A Beginners Tutorioal\n" +
                    "Mildrid Ljosland;Programmering i C++\n" +
                    "Else Lervik;Programmering i C++\n" +
                    "Mildrid Ljosland;Algoritmer og datastrukturer\n" +
                    "Helge Hafting;Algoritmer og datastrukturer\n" +
                    "Timothy L. Warner;Teach Yourself Windows Powershell in 24 Hours\n" +
                    "Øyvind Hallsteinsen;Innføring i datakommunikasjon\n" +
                    "Bjørn Klefstad;Innføring i datakommunikasjon\n" +
                    "Olav Skundberg;Innføring i datakommunikasjon\n" +
                    "Kjell Toft Hansen;Databaser\n" +
                    "Tore Mallaug;Databaser\n" +
                    "Else Lervik;Programmering i Java\n" +
                    "Vegard B. Havdal;Programmering i Java");*/
            importerFraFil("navneliste");
            //ArrayList<String> res = db.getAllAuthors();
            ArrayList<String> res = db.getAllBooks();
            //ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
            //ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
            //ArrayList<String> res = db.getAllBooksAndAuthors();
            showResults(res);
        }
        catch (Exception e) {
            String tekst = e.getMessage();
            TextView t = (TextView)findViewById(R.id.tw1);
            t.setText(tekst);
        }

        //Setter gitt farge på lista
        oppdaterBgColorList();
    }
    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n");
        }
        TextView t = (TextView)findViewById(R.id.tw1);
        t.setText(res);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.preferences) {
            Intent intent = new Intent();
            intent.setClassName(this, "no.hist.itfag.books.MyPreferenceActivity");
            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void importerFraFil(String filnavn) {
        //Fil på formatet: "Boktittel;Navn Navnesen\n" (dvs. ny linje for hver person (Kan hende boktitler lagres dobelt, noe som ikke er optimatlt).)
        File dir = getFilesDir();
        File file = new File(dir, filnavn);
        BufferedReader bufferedReader = null;
        Log.v(TAG, "Henter data fra fil");
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            do {
                if (line != "") {
                    String[] inndata = line.split(";");
                    db.insert(inndata[0], inndata[1]);
                    Log.v(TAG, "Lagt til: " + inndata[0] + ", " + inndata[1]);
                }
                line = bufferedReader.readLine();
            } while (line != null);

        } catch (IOException ioe) {
            Log.e(TAG, "IO: " + ioe.getMessage());
            ioe.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ioe) {
                Log.e(TAG, ioe.getMessage());
                ioe.printStackTrace();
            }

        }
    }

    public void skrivTilFil(String filnavn, String tekst) {
        File dir = getFilesDir();
        File file = new File(dir, filnavn);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.write(tekst);
            writer.close();
        } catch (IOException ioe) {
            Log.e(TAG, "IO: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public void oppdaterBgColorList() {
        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(this );

        String farge = prefs.getString(getString(R.string.key_bakgrunnsfarge), "black");
        int fargeKode = 0;
        if (farge.equals("black")) {
            fargeKode = Color.BLACK;
        } else if (farge.equals("blue")) {
            fargeKode = Color.BLUE;
        } else if (farge.equals("green")) {
            fargeKode = Color.GREEN;
        } else {
            fargeKode = Color.GRAY;
        }
        TextView l = (TextView) findViewById(R.id.tw1);
        l.setBackgroundColor(fargeKode);
    }


    @Override
    public void onResume() {
        super.onResume();
        oppdaterBgColorList();
    }

    public void onBtnClick(View v) {
        Button btn = (Button) v;
        ArrayList<String> res = new ArrayList<String>();
        if (btn.equals(findViewById(R.id.btnForfatter))) {
             res = db.getAllAuthors();
        } else if (btn.equals(findViewById(R.id.btnTittel))) {
            res = db.getAllBooks();
        }
        showResults(res);
    }

}
