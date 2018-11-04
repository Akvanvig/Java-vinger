package com.example.oving_6_sockets_klient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class KlientActivity extends Activity {

    String ipTjener = "10.0.2.2";
    int portTjener = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klient);
    }

    public void skrivTilLabel(String tekst) {
        TextView label = (TextView) findViewById(R.id.lblTekstTilBruker);
        label.setText(tekst);
    }

    public String[] hentTall() {
        EditText tall1 = (EditText) findViewById(R.id.txtTall1);
        EditText tall2 = (EditText) findViewById(R.id.txtTall2);
        String[] tall = {tall1.getText().toString(), tall2.getText().toString()};
        return tall;
    }

    public void onBeregnKlikk(View v) {
        new KlientThread(this, ipTjener, portTjener).start();
    }
}
