package com.example.notification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String ID_BASIC="basic";
    private String ID_HIGH="high";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Notification.Builder notification = new Notification.Builder(MainActivity.this);
//        notification.setSmallIcon(R.mipmap.peach);
//        notification.setContentTitle("一只桃子派送中");
//        notification.setContentText("[点击签收...]");
//        notification.setWhen(System.currentTimeMillis());
//        notification.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE);//设置声音震动
//        //创建一个启动详情界面的Intent
//        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
//        PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
//        notification.setContentIntent(pi);
//        notificationManager.notify();

        //---------------------------
        //channel的id
        String id = "channel_1";
        //channel的描述信息
        String description = "123";
        //channel的重要性
        int importance = NotificationManager.IMPORTANCE_HIGH;
        //生成channel
        NotificationChannel channel = new NotificationChannel(id, "123", importance);
        //为channel添加属性
        channel.enableVibration(true);
        channel.enableLights(true);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //添加channel
        manager.createNotificationChannel(channel);
        Notification notification = new Notification.Builder(MainActivity.this, id)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setSmallIcon(R.mipmap.peach)
                .setContentTitle("一只桃子派送中...")
                .setContentText("【点击签收】")
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);


        //----------------------------
        createNotificationChannel(ID_BASIC,"一般",NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannel(ID_HIGH,"订阅",NotificationManager.IMPORTANCE_HIGH);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(String id, String name, int improtance) {
        NotificationChannel channel = new NotificationChannel(id, name, improtance);
        //获取通知服务
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }
}