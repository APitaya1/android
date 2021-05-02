package com.example.notification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.notification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //通知ID
    private final static int NOTIFIATION = 100;
    
    //通知列表
    public static final String ID_BASIC = "basic";
    public static final String ID_HIGH = "high";
    //Spinner列表数据
    public static final String[] NOTIFICATION_STYLES = {
            "----请选择----", "基本样式", "悬浮样式",
            "长文本样式", "大图样式", "收件箱样式", "自定义VIEW"};

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        //创建两个通知通道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(ID_BASIC, "一般消息", NotificationManager.IMPORTANCE_DEFAULT);
            createNotificationChannel(ID_HIGH, "订阅消息", NotificationManager.IMPORTANCE_HIGH);
        }

        //给Spinner控件设置数据适配器
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, NOTIFICATION_STYLES);
        binding.spStyle.setAdapter(adapter);
        binding.spStyle.setOnItemSelectedListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(String id, String name, int importance) {
        NotificationChannel channel = new NotificationChannel(id, name, importance);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String id, String name, int improtance) {
        NotificationChannel channel = new NotificationChannel(id, name, improtance);
        //获取通知服务
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String style = NOTIFICATION_STYLES[position];
        binding.tvDescription.setText(style);
        if ("基本样式".equals(style)) {
            showBasicNotification();
        } else if ("悬浮样式".equals(style)) {
            showHighNotification();
        } else if ("长文本样式".equals(style)) {

        } else if ("大图样式".equals(style)) {

        } else if ("收件箱样式".equals(style)) {

        } else if ("自定义View".equals(style)) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(Menu.NONE, Menu.FIRST, 0, "邮件").setIcon(R.drawable.ic_email);
        return super.onCreateOptionsMenu(menu);
    }

    private void showHighNotification() {
    }

    private void showBasicNotification() {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}