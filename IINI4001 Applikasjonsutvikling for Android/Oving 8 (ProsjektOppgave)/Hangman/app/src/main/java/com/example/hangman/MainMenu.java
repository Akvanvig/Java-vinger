package com.example.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    String TAG = "HANGMAN_MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Henter ut statistikk for runder spillt
        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String winCount = prefs.getString(getString(R.string.key_winCount), "0");
        String totalCount = prefs.getString(getString(R.string.key_totalCount), "0");
        int winC = Integer.parseInt(winCount);
        int totC = Integer.parseInt(totalCount);
        int lossC = totC - winC;
        double winPercent = 0.0;
        if (totC != 0) {
            winPercent = (100.0 / totC) * winC;
        }

        //Skriver ut statistikk til bruker
        TextView view = (TextView) findViewById(R.id.txtStats);
        String text = getString(R.string.stats_total) + " " + totC +
                "\n\n\n" + getString(R.string.stats_win) + " " + winC +
                "\n\n\n" + getString(R.string.stats_lost) + " " + lossC +
                "\n\n\n" + getString(R.string.stats_percetWin) + " " + (int) winPercent + "%";
        view.setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.abPreferences) {
            Intent intent = new Intent();
            intent.setClassName(this, "com.example.hangman.HangmanPreferenceActivity");
            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }

            return true;
        } else if (id == R.id.abAbout) {
            klikkAbout();
        } else if (id == R.id.abClose) {
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBtnClick(View view) {
        if (view.getId() == R.id.btnStart) {
            Intent intent = new Intent();
            intent.setClassName(this, "com.example.hangman.GameActivity");
            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        } else if (view.getId() == R.id.btnSettings) {
            Intent intent = new Intent();
            intent.setClassName(this, "com.example.hangman.HangmanPreferenceActivity");
            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        } else if (view.getId() == R.id.btnAbout) {
            try {
                klikkAbout();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        } else if (view.getId() == R.id.imgbtnFlagg) {
            String text = getString(R.string.changeLanguage);
            Toast.makeText(this.getBaseContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public void klikkAbout() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(getString(R.string.about_text));
        builder1.setCancelable(true);

        builder1.setNeutralButton(getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }



}
