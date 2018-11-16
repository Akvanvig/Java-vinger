package com.example.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

//Importerer AppCompatPreferenceActivity for å få inn Toolbar, koden til denne er openSource, og hentet fra
//Google https://chromium.googlesource.com/android_tools/+/7200281446186c7192cb02f54dc2b38e02d705e5/sdk/extras/android/support/samples/Support7Demos/src/com/example/android/supportv7/app/AppCompatPreferenceActivity.java
public class HangmanPreferenceActivity extends com.example.android.supportv7.app.AppCompatPreferenceActivity {
    private static String TAG = "HANGMAN_SETTINGS";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.headers_preference, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        //Validerer om det mottas et gyldig fragment
        //Spillinstillinger
        if (GameSettingsPreferenceFragment.class.getName().equals(fragmentName)) {
            return true;
        }
        //Andreinnstillinger
        return OthersPreferenceFragment.class.getName().equals(fragmentName);
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

        //Check what button in the actionbar menu was pressed
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
        } else if (id == R.id.abClose) {
            finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }


    //Fragmentet hvor andre innstillinger settes
    public static class OthersPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.other_settings_preference);

            //Legger til knapp for å slette statistikk
            Preference button = findPreference(getString(R.string.key_resetStats));
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    String text = getString(R.string.statsReset);
                    //Resets prefs for stats
                    SharedPreferences prefs;
                    prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor prefsEditor = prefs.edit();
                    prefsEditor.putString(getString(R.string.key_winCount), "0");
                    prefsEditor.putString(getString(R.string.key_totalCount), "0");
                    prefsEditor.commit();
                    //Gives user respose
                    Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
                    return true;
                }
            });
        }

    }

    //Fragmentet hvor spillinnstillinger settes
    public static class GameSettingsPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.game_settings_preference);
            //Binger summary til å vise verdien i listene er satt til
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_difficulty)));
            bindPreferenceSummaryToValue(findPreference(getString(R.string.key_wordlist)));
        }

        private static void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);
            sBindPreferenceSummaryToValueListener.onPreferenceChange(
                    preference,
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), "")
            );
        }

        //Sjekker endringer i Preference, og oppdaterer
        private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object nyVerdi) {
                String stringVerdi = nyVerdi.toString();
                if (preference instanceof ListPreference) {
                    ListPreference listPreference = (ListPreference) preference;
                    int index = listPreference.findIndexOfValue(stringVerdi);
                    //Endrer så summary viser den nye verdien
                    preference.setSummary(index >= 0 ? listPreference.getEntries()[index]: null);
                }
                return true;
            }
        };
    }

}
