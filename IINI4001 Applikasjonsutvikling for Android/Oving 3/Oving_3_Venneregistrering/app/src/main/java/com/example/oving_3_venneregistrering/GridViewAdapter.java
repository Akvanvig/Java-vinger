package com.example.oving_3_venneregistrering;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> navn;
    private ArrayList<String> dato;

    public GridViewAdapter(Context c, ArrayList<String> navn, ArrayList<String> dato) {
        context = c;
        this.navn = navn;
        this.dato = dato;
    }

    @Override
    public int getCount() {
        return navn.size();
    }

    @Override
    public Object getItem(int position) {
        return navn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TextView view;
        if (convertView == null) {
            view = new TextView(context);
            String tekst = navn.get(position) + " \n " + dato.get(position);
            view.setText(tekst);
        } else {
            view = (TextView) convertView;
        }
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
