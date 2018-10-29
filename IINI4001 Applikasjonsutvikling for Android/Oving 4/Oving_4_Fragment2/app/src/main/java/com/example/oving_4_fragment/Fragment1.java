package com.example.oving_4_fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    ArrayAdapter<String> adapter;
    private String[] bilderNavn;
    private String[] bilderBeskrivelse;
    private TypedArray bilder;


    public Fragment1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Henter bilder, navn og beskrivelser
        bilderNavn = getResources().getStringArray(R.array.bildenavn);
        bilderBeskrivelse = getResources().getStringArray(R.array.bildebeskrivelse);
        bilder = getResources().obtainTypedArray(R.array.bilder);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, bilderNavn);

        //Oppretter View
        View v = inflater.inflate(R.layout.fragment1, container, false);
        ListView listeNavn = v.findViewById(R.id.listeBildenavn);

        //Legger til tekst/onClick på ordene i lista
        listeNavn.setAdapter(adapter);
        listeNavn.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listeNavn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context c = getActivity();
                View f2 = ((Activity) c).findViewById(R.id.fragment2);
                ImageView imgFelt = (ImageView) f2.findViewById(R.id.imgFelt);
                TextView beskrivelse = (TextView) f2.findViewById(R.id.lblBeskrivelse);

                //Setter Riktig bilde
                Drawable bilde = bilder.getDrawable(position);
                imgFelt.setImageDrawable(bilde);

                //Setter Riktig tekst
                beskrivelse.setText(bilderBeskrivelse[position]);

                //Oppdaterer valgt posisjon i aktiviteten
                ((MainActivity) c).setSelectedItem(position);
            }
        });
        return v;
    }
}
