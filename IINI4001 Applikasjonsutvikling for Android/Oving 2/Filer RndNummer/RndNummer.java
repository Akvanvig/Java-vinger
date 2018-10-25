package com.example.oving2_oppg1_testverktoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

public class RndNummer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random();

        //Henter data fra intent
        int upperLimit = 0;
        try {
            upperLimit = Integer.parseInt(getIntent().getExtras().get("limit").toString());
        } catch (Exception  ex){
            Log.i("genRndTall()", ex.toString());
        }

        Log.i("genRndTall()","Øvre grense er: " + upperLimit);

        //Finner tilfeldig tall, viser til bruker
        int rndInt = random.nextInt(upperLimit);
        Log.i("genRndTall()","Det tilfeldige tallet er: " + rndInt);
        //Toast.makeText(this, "Det tilfeldige tallet er: " + rndInt, Toast.LENGTH_LONG).show();

        //Sender tilbake til appen som etterspør tallet.
        Intent returData = new Intent();
        returData.putExtra("tall", "" + rndInt);
        setResult(RESULT_OK, returData );
        finish();
    }
}