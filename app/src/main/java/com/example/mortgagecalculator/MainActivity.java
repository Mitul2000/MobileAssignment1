package com.example.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //switches from main activity to the Home activity
        Intent myIntent = new Intent(this, Home.class);
        this.startActivity(myIntent);
    }
}