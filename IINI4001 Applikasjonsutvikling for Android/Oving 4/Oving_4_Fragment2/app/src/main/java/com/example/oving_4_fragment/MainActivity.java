package com.example.oving_4_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    public int selectedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem mi = menu.add(0,0,0, R.string.forrige );
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mi = menu.add(0,1,0, R.string.neste );
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ListView v = (ListView) findViewById(R.id.listeBildenavn);
        int pos = selectedItem;
        int newPos;
        int maxPos = getResources().getStringArray(R.array.bildenavn).length - 1;
        Log.e("NavKnapp","id = " + id + ", pos = " + pos);
        switch (id) {
            case 0: //Neste
                if (pos > 0) {
                    newPos = pos - 1;
                } else {
                    newPos = maxPos;
                }
                v.performItemClick(v,newPos, newPos);
                return true;
            case 1: //Forrige
                if (pos < maxPos) {
                    newPos = pos + 1;
                } else {
                    newPos = 0;
                }
                v.performItemClick(v,newPos, newPos);
                return true;
        }
        return false;
    }

    public void setSelectedItem(int i) {
        selectedItem = i;
    }
}
