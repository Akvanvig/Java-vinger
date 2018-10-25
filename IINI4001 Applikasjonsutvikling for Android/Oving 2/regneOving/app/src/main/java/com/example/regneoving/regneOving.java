package com.example.regneoving;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class regneOving extends Activity {

    private static int RND_TALL1_RC = 2;
    private static int RND_TALL2_RC = 3;
    private int tall1 = 0;
    private int tall2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regne_oving);
    }

    public void onClick(View v) {
        Button knapp = (Button) v;
        String tekst = knapp.getText().toString();

        //Henter info fra Gui
        EditText svarFelt;
        EditText grenseFelt;

        try {
            svarFelt = (EditText) findViewById(R.id.txtSvar);
            grenseFelt = (EditText) findViewById(R.id.txtGrense);
        } catch (Exception e) {
            Toast.makeText(this, "Klarte ikke hente ut informasjonen", Toast.LENGTH_LONG);
            //Avbryter funksjon ettersom riktige felter ikke kunne hentes
            return;
        }

        //Henter ut variabler til riktig datatyper
        int svar;
        int grense;

        try {
            svar = Integer.parseInt(svarFelt.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Klarte ikke konvertere innhold av felt", Toast.LENGTH_LONG);
            //Avbryter funksjon ettersom innhold av felter ikke kunne konverteres til riktig datatype
            return;
        }

        try {
            grense = Integer.parseInt(grenseFelt.getText().toString());
        } catch (Exception e) {
            grense = 100;
            grenseFelt.setText("" + grense);
        }

        //Sjekker om bruker gjettet riktig
        //Først pluss
        if (tekst.equals(getString(R.string.pluss))) {
            if (tall1 + tall2 == svar) {
                Toast.makeText(this, R.string.riktig, Toast.LENGTH_LONG).show();
            } else {
                int res = tall1 + tall2;
                String resTekst = getString(R.string.feil) + " " + res;
                Toast.makeText(this, resTekst, Toast.LENGTH_LONG).show();
            }
            //Deretter gange
        } else if (tekst.equals(getString(R.string.gange))) {
            if (tall1 * tall2 == svar) {
                Toast.makeText(this, R.string.riktig, Toast.LENGTH_LONG).show();
            } else {
                int res = tall1 * tall2;
                String resTekst = getString(R.string.feil) + " " + res;
                Toast.makeText(this, resTekst, Toast.LENGTH_LONG).show();
            }
        } else {
            return;
        }

        genNyeTall(grense);
    }

    private void genNyeTall( int grense) {
        //Genererer nye tall
        Intent intent1 = new Intent("com.example.GetNumber");
        intent1.putExtra("limit", grense);
        startActivityForResult(intent1, RND_TALL1_RC);

        Intent intent2 = new Intent("com.example.GetNumber");
        intent2.putExtra("limit", grense);
        startActivityForResult(intent2, RND_TALL2_RC);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RND_TALL1_RC && resultCode == RESULT_OK) {
            tall1 = Integer.parseInt(data.getExtras().get("tall").toString());
        } else if (requestCode == RND_TALL2_RC && resultCode == RESULT_OK) {
            tall2 = Integer.parseInt(data.getExtras().get("tall").toString());
        } else {
            Toast.makeText(this, "Noe gikk galt", Toast.LENGTH_LONG).show();
            return;
        }
        TextView tall1Felt = (TextView)findViewById(R.id.lblTall1);
        TextView tall2Felt = (TextView)findViewById(R.id.lblTall2);
        tall1Felt.setText("" + tall1);
        tall2Felt.setText("" + tall2);
    }
}
