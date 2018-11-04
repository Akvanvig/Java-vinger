package com.example.oving_6_sockets_tjener;

import android.app.Activity;
import android.os.Bundle;

public class TjenerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjener);
        new TjenerThread().start();
    }
}
