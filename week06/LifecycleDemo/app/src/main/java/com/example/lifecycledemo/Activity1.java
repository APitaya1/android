package com.example.lifecycledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        Button button1= findViewById(R.id.button2);
        //创建 Intent 对象
        Intent intent = new Intent(Activity1.this,Activity2.class);
        //启动 Activity2
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activity1","onStart() is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}