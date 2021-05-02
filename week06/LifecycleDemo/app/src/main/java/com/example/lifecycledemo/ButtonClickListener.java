package com.example.lifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ButtonClickListener extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1= findViewById(R.id.button1);
        Intent intent = new Intent(ButtonClickListener.this,Activity1.class);
        startActivity(intent);
    }
}