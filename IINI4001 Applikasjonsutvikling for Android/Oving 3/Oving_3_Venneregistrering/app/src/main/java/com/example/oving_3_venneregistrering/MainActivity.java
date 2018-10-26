package com.example.oving_3_venneregistrering;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends Activity{
    private ArrayList<String> navn = new ArrayList<String>();
    private ArrayList<String> fodselsdato = new ArrayList<String>();
    private GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new GridViewAdapter(this, navn, fodselsdato);

        GridView navneliste = (GridView) findViewById(R.id.personListe);
        oppdaterListe(navneliste);
    }

    public void onClick(View v) {
        EditText navnefelt = (EditText) findViewById(R.id.txtNavn);
        EditText datofelt = (EditText) findViewById(R.id.txtDato);
        GridView navneliste = (GridView) findViewById(R.id.personListe);

        String navnSkrevet = navnefelt.getText().toString();
        String datoSkrevet = datofelt.getText().toString();

        if (! navnSkrevet.equals("") && ! datoSkrevet.equals("")) {
            //Stoler på at dato skrevet stemmer
            try {
                navn.add(navnSkrevet);
                fodselsdato.add(datoSkrevet);
                oppdaterListe(navneliste);
                Log.e("vennereg_",navn.toString());
            } catch (Exception e) {
                Log.e("vennereg_",e.getMessage());
            }

        }
    }

    public void oppdaterListe(GridView navneliste) {
        navneliste.setAdapter(adapter);
        navneliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EditText navnefelt = (EditText) findViewById(R.id.txtNavn);
                EditText datofelt = (EditText) findViewById(R.id.txtDato);
                GridView navneliste = (GridView) findViewById(R.id.personListe);

                String navnet = navn.get(position);
                Log.e("vennereg_",navnet);
                String datoen = fodselsdato.get(position);
                navnefelt.setText(navnet);
                datofelt.setText(datoen);
                navn.remove(position);
                fodselsdato.remove(position);
                navneliste.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }


}
