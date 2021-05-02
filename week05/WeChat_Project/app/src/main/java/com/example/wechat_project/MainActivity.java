package com.example.wechat_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        addButton = findViewById(R.id.add_button);
        wechatButton=findViewById(R.id.wechat);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}