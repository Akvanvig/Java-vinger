package com.example.hangman;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//Standard ArrayAdpter with a small change to how the TextViews are printed, and a new function (letterGuessed)
//the letterGuessed function is responsible for chosing what color clicked letters should recieve.
public class LetterAdapter extends ArrayAdapter<String> {
    private byte[] guessed;

    public LetterAdapter(Context c, int textViewResourceId, String[] letters) {
        super(c, textViewResourceId, letters);
        guessed = new byte[letters.length];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
        switch (guessed[position]) {
            case 0: //Not Guessed
                text1.setBackgroundColor(Color.GRAY);
                break;
            case 1: //Guessed, In word
                text1.setBackgroundColor(Color.GREEN);
                break;
            case 2: //Guessed, Not in word
                text1.setBackgroundColor(Color.RED);
                break;
        }
        return view;
    }

    //Når det blir gjettet, sjekkes det om bokstaven er gjettet fra før, samt resultat registreres
    public boolean letterGuessed(int position, boolean guessCorrect) {
        if (guessed[position] != 0) {
            return false;
        }
        if (guessCorrect) {
            guessed[position] = 1;
        } else {
            guessed[position] = 2;
        }
        return true;
    }
}
