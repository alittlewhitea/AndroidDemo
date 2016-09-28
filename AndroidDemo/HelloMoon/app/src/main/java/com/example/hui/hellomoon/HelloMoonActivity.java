package com.example.hui.hellomoon;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class HelloMoonActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_moon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hello_moon, menu);
        return true;
    }
}
