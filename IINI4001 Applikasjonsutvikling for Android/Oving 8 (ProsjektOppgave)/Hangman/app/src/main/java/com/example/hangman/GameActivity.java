package com.example.hangman;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    String TAG = "HANGMAN_GAMEACTIVITY";
    int difficulty;
    byte strikes = 0;
    ArrayList<String> wordlist;
    ArrayList<String> prevWordlist;
    char[] letters;
    String word;
    char[] wordLetters;
    boolean[] showLetter;
    //Alphabet button-list
    String[] lettersString;
    GridView gridLetters;
    LetterAdapter adapter;

    Random rand;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        prevWordlist = new ArrayList<String>();

        //Henter Ord/Ordliste
        wordlist = importWordlist();
        word = pickWord(wordlist);
        Log.v(TAG, word);
        getLetters(); //Sets letters, and lettersString to contain the given alphabet
        wordLetters = word.toUpperCase().toCharArray();
        showLetter = getBooleanLetters(wordLetters);

        getDifficulty();
        drawHangman();
        drawWord();

        drawUI();
    }

    public void drawUI() {
        //Tegner opp Hangman UI
        try {
            gridLetters = (GridView) findViewById(R.id.gridLetters);

            adapter = new LetterAdapter(this, android.R.layout.simple_list_item_1, lettersString);
            gridLetters.setAdapter(adapter);
            gridLetters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                //Ved klikk på bokstav sjekkes den, og gui tegnes opp på nytt
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Henter svar
                    Context c = getApplicationContext();
                    TextView t = (TextView) view;
                    String bokstav = t.getText().toString();
                    //Sjekker svar
                    boolean correct = checkLetter(bokstav);
                    boolean notPrevGuessed = adapter.letterGuessed(position, correct);
                    if (notPrevGuessed) {
                        updateStrikes(correct);
                        drawWord();
                        checkWord();
                        drawHangman();
                        checkStrikes();
                    }

                    //Oppdaterer knapper
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "1: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //Creates the actionmenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Writes out the Actionbar menu menuitems
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
                Log.e(TAG, "2:"+ e.getMessage());
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

    //Reads the wordlist from file, and returns it as an arraylist
    public ArrayList<String> importWordlist() {
        ArrayList<String> wl = new ArrayList<String>();
        int id = getWordlistID();
        Log.i(TAG, "Leser fra fil");
        try {
            InputStream is = getResources().openRawResource(id);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                wl.add(line);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wl;
    }

    //Gets the ID of the wordlist chosen in preferences
    private int getWordlistID() {
        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String wordlistIndex = prefs.getString(getString(R.string.key_wordlist), "0");
        int indexInt = Integer.parseInt(wordlistIndex);

        switch (indexInt) {
            case 0:
                return R.raw.ordliste;
            case 1:
                return R.raw.capitals;
            case 2:
                return R.raw.countries;
            case 3:
                return R.raw.elements;
            case 4:
                return R.raw.english_footballteams;
            case 5:
                return R.raw.norwegian_counties;
            case 6:
                return R.raw.pc_parts;
            case 7:
                return R.raw.us_states;
        }
        return -1;
    }

    private int getDifficulty() {
        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String difficultyIndex = prefs.getString(getString(R.string.key_difficulty), "0");
        Log.v(TAG, "DifficultyStr: " + difficultyIndex);
        return difficulty = Integer.parseInt(difficultyIndex);
    }

    //Choses a random word for the given list wordlist
    private String pickWord(ArrayList<String> wl) {
        rand = new Random();
        return wl.get(rand.nextInt(wl.size()));
    }

    //Gets all the letters of the given alphabet (Varies by language)
    private void getLetters() {
        String[] l = getResources().getStringArray(R.array.letters);
        char[] chars = new char[l.length];
        for (int i = 0; i < l.length; i++) {
            chars[i] = l[i].charAt(0);
        }
        letters = chars;
        lettersString = l;
    }

    //Returns and array of booleans based on if they should be shown, or guessed
    private boolean[] getBooleanLetters(char[] w) {
        boolean[] sl = new boolean[w.length];
        //Checks if all the letters are in the given alphabet, if not it will be show from the start
        for (int i = 0; i < w.length; i++) {
            boolean contains = false;
            try {
                for (int j = 0; j < letters.length; j++) {
                    if (w[i] == letters[j]) {
                        contains = true;
                        break;
                    }
                }
                if (! contains) {
                    sl[i] = true;
                }
            } catch (Exception e) {
                Log.e(TAG, "3" + e.getMessage());
                e.printStackTrace();
            }

        }
        return sl;
    }

    //Checks if a given letter is in the word the user is trying to guess, if it's found, it will mark it as such in the boolean array.
    private boolean checkLetter(String letter) {
        boolean letterFound = false;
        for (int i = 0; i < showLetter.length; i++) {
            if (letter.charAt(0) == wordLetters[i]) {
                showLetter[i] = true;
                letterFound = true;
            }
        }
        return letterFound;
    }

    //Updates the score representing number of errors/how much og the hanged man should be drawn
    private void updateStrikes(boolean correct) {
        if (! correct) {
            switch (difficulty) {
                case 0:
                    strikes ++;
                    break;
                case 1:
                    strikes += 2;
                    break;
                case 2:
                    strikes += 5;
                    break;
            }
        }

    }

    //Checks if number of faults is larger then max-value, and gives user info about loss
    private void checkStrikes() {
        if (strikes >= 10) {
            //Spill tapt
            endGame(false);
        }
    }

    //Draws the hangman figure to the UI
    private void drawHangman() {
        ImageView image = (ImageView) findViewById(R.id.imageView);
        switch (strikes) {
            case 0:
                image.setImageResource(R.drawable.hangman_00);
                break;
            case 1:
                image.setImageResource(R.drawable.hangman_01);
                break;
            case 2:
                image.setImageResource(R.drawable.hangman_02);
                break;
            case 3:
                image.setImageResource(R.drawable.hangman_03);
                break;
            case 4:
                image.setImageResource(R.drawable.hangman_04);
                break;
            case 5:
                image.setImageResource(R.drawable.hangman_05);
                break;
            case 6:
                image.setImageResource(R.drawable.hangman_06);
                break;
            case 7:
                image.setImageResource(R.drawable.hangman_07);
                break;
            case 8:
                image.setImageResource(R.drawable.hangman_08);
                break;
            case 9:
                image.setImageResource(R.drawable.hangman_09);
                break;
            case 10: //loss
                image.setImageResource(R.drawable.hangman_10_loss);
                break;
            case -1: //win
                image.setImageResource(R.drawable.hangman_10_win);
                break;
            case -2: //Menu
                image.setImageResource(R.drawable.hangman_10_menu);
                break;
            default:

        }

    }

    //Write Letters to user, shows number of letters and what letters the user has gotten correct
    private void drawWord() {
        TextView v = findViewById(R.id.txtWord);
        String text = "";
        for (int i = 0; i < showLetter.length; i++) {
            if (showLetter[i]) {
                text += wordLetters[i] + "\t";
            } else {
                text += "_ ";
            }

        }
        v.setText(text);
    }

    private void checkWord() {
        boolean finished = true;
        for (boolean letter : showLetter) {
            if (! letter) {
                finished = false;
                break;
            }
        }
        if (finished) {
            //spill vunnet
            strikes = -1;
            drawHangman();
            endGame(true);
        }
    }

    //Updates statistics, shows alertbox with result.
    private void endGame(boolean won) {
        //Oppdaterer statistikk
        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        int winCount = Integer.parseInt(prefs.getString(getString(R.string.key_winCount), "0"));
        int totalCount = Integer.parseInt(prefs.getString(getString(R.string.key_totalCount), "0"));
        totalCount ++;
        if (won) {
            winCount ++;
        }

        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(getString(R.string.key_winCount), "" + winCount);
        prefsEditor.putString(getString(R.string.key_totalCount), "" + totalCount);
        prefsEditor.commit();

        //Varsler bruker
        String popupMessage;
        if (won) {
            popupMessage = getString(R.string.end_victory);
        }else {
            popupMessage = getString(R.string.end_loss) + " " + word + ".";
        }
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(popupMessage);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                getString(R.string.mainmenu),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        GameActivity.super.onBackPressed();
                    }
                });

        builder1.setNeutralButton(getString(R.string.replay),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(final DialogInterface dialog) {
                restart();
            }
        });
        alert11.show();
    }

    private void restart() {
        //Nullstiller innstillinger
        strikes = 0;
        prevWordlist.add(word);

        Log.v(TAG, "prevWordlist: " + prevWordlist.size() +", wordlist: " + wordlist.size());
        //Henter ubrukt ord
        if (prevWordlist.size() < wordlist.size()) {
            boolean newWord;
            do {
                newWord = true;
                word = pickWord(wordlist);
                for (String w : prevWordlist){
                    if (word.equals(w)) {
                        newWord = false;
                    }
                }
            } while (! newWord);

        } else { //Hvis alle nye ord er brukt
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage(getString(R.string.noNewWords));
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    getString(R.string.mainmenu),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            GameActivity.super.onBackPressed();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(final DialogInterface dialog) {
                    GameActivity.super.onBackPressed();
                }
            });
            alert11.show();
        }

        //Setter opp gui
        Log.v(TAG, word);
        wordLetters = word.toUpperCase().toCharArray();
        showLetter = getBooleanLetters(wordLetters);

        getDifficulty();
        drawHangman();
        drawWord();

        drawUI();
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
