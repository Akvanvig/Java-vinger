package com.example.oving_5_tallgjetter_http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final static String grunnlenke = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";
    private HttpWrapperThreaded nettverk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nettverk = new HttpWrapperThreaded(this, grunnlenke);
    }

    public void onClick(View v) {
        int id = v.getId();
        EditText navnFelt = (EditText) findViewById(R.id.txtNavn);
        EditText kortNrFelt = (EditText) findViewById(R.id.txtKortNr);
        EditText tallFelt = (EditText) findViewById(R.id.txtTall);

        if (R.id.btnSend == id) {
            try {
                //Hvis bruker legger inn navn/kortnr
                if (navnFelt.getVisibility() == View.VISIBLE) {
                    String navn = navnFelt.getText().toString();
                    String kortnummer = kortNrFelt.getText().toString();

                    Map<String, String> parametere = createRequestValues(navn, kortnummer);
                    Log.e("nettverkKom","Variabler: Navn - '" + navn + "', kortnummer - '" + kortnummer + "'");
                    nettverk.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, parametere);
                } else { //Hvis bruker legger inn tall
                    String tall = tallFelt.getText().toString();

                    Map<String, String> parametere = createRequestValues(tall);
                    Log.e("nettverkKom","Variabler: Tall - '" + tall + "'");
                    nettverk.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, parametere);
                }
            } catch (Exception e) {
                Log.e("nettverkKom","Feilmelding: " + e.toString());
            }

            setVisibleFelt(1);
        } else if (R.id.btnRestart == id) {
            setVisibleFelt(0);
        }
    }


    //Setter riktige felt til å være synlige med tanke på hvilke felter som trengs
    //0 = Navn + Kortnummer
    //1 = tekstLabel + TallFelt
    public void setVisibleFelt(int i) {
        EditText navnFelt = (EditText) findViewById(R.id.txtNavn);
        EditText kortNrFelt = (EditText) findViewById(R.id.txtKortNr);
        EditText tallFelt = (EditText) findViewById(R.id.txtTall);
        TextView tekstLabel = (TextView) findViewById(R.id.lblTekstfelt);

        if (0 == i) {
            navnFelt.setVisibility(View.VISIBLE);
            kortNrFelt.setVisibility(View.VISIBLE);
            tallFelt.setVisibility(View.INVISIBLE);
            tekstLabel.setVisibility(View.INVISIBLE);
        } else {
            navnFelt.setVisibility(View.INVISIBLE);
            kortNrFelt.setVisibility(View.INVISIBLE);
            tallFelt.setVisibility(View.VISIBLE);
            tekstLabel.setVisibility(View.VISIBLE);
        }
    }

    public void showResponse(String respons) {
        TextView tekstLabel = (TextView) findViewById(R.id.lblTekstfelt);
        tekstLabel.setText(respons);
    }

    private Map<String,String> createRequestValues(String navn, String kortnummer){
        Map<String,String> valueList = new HashMap<String,String>();
        valueList.put("navn", navn);
        valueList.put("kortnummer", kortnummer);
        return valueList;
    }

    private Map<String,String> createRequestValues(String tall){
        Map<String,String> valueList = new HashMap<String,String>();
        valueList.put("tall", tall);
        return valueList;
    }
}
