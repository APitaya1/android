package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button2 = findViewById(R.id.phone);
        button2.setOnClickListener(this);
        Button button3 = findViewById(R.id.go_activity);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    private void Call() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    private void gotoActivity() {
        Intent intent = new Intent();
        intent.putExtra("data", "软件2058");
        ArrayList<Integer> datas = new ArrayList<>();
        datas.add(85);
        datas.add(90);
        datas.add(78);
        intent.putIntegerArrayListExtra("List", datas);
    }



}