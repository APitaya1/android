package com.example.weixininterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.weixininterface.R.id.add_button;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageButton searchButton;
    ImageButton addButton;
    ImageButton wechatButton;
    ImageButton addressbookButton;
    ImageButton findButton;
    ImageButton meButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        addButton = findViewById(add_button);
        wechatButton = findViewById(R.id.wechat);
        addressbookButton = findViewById(R.id.addressbook);
        findButton = findViewById(R.id.find);
        meButton = findViewById(R.id.find);
        //添加按钮的点击事件
        wechatButton.setOnClickListener(v -> {

        });
        //通讯录按钮的点击事件
        addressbookButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddressbookActivity.class);
            startActivity(intent);
        });
        //添加按钮的点击事件 实现下拉框
        addButton.setOnClickListener(v -> {
            //创建弹出式菜单对象（最低版本11）
            PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
            //获取菜单填充器
            MenuInflater inflater = popup.getMenuInflater();
            //填充菜单
            inflater.inflate(R.menu.addmenu, popup.getMenu());
            //绑定菜单项的点击事件
            popup.setOnMenuItemClickListener(this);
            //显示
            popup.show();
        });


    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.talk:
                Toast.makeText(MainActivity.this, "11111", LENGTH_LONG).show();
                break;
            case R.id.friend:
                Toast.makeText(MainActivity.this, "11111", LENGTH_LONG).show();
                break;
            case R.id.scan:
                Toast.makeText(MainActivity.this, "11111", LENGTH_LONG).show();
                break;
            case R.id.receving:
                Toast.makeText(MainActivity.this, "11111", LENGTH_LONG).show();
                break;
        }
        return false;
    }
}
