<<<<<<< HEAD
package com.example.oving_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu meny){
        super.onCreateOptionsMenu(meny);
        meny.add("Anders");
        meny.add("Kvanvig");
        Log.i("onCreateOptionsMenu()","meny laget");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Log.i("OnOptionsItemSelected()","\"" + item.getTitle() + "\" ble valgt av bruker");
        return true;
    }
}
=======
package com.example.oving_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu meny){
        super.onCreateOptionsMenu(meny);
        meny.add("Anders");
        meny.add("Kvanvig");
        Log.i("onCreateOptionsMenu()","meny laget");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Log.i("OnOptionsItemSelected()","\"" + item.getTitle() + "\" ble valgt av bruker");
        return true;
    }
}
>>>>>>> 2da28bdf352ab0ed3f0de02301ccf2cff69139dd
