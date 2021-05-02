package com.example.login_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private LinearLayout linearLayout;
    private EditText name, phone;
    private RadioGroup radioGroup;
    //    private RadioButton btnBoy;
//    private RadioButton btnGirl;
//    private CheckBox c1;
//    private CheckBox c2;
//    private CheckBox c3;
//    private CheckBox c4;
//    private Button bt_concern;
    //checkbox选中的文本字符串
    private String selected = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        //初始化布局对象
        linearLayout = findViewById(R.id.linearlayout);
        //初始化控件
        name = findViewById(R.id.editTxt_userName);
        phone = findViewById(R.id.editTxt_phone);
        RadioButton btnBoy = findViewById(R.id.radio_boy);
        RadioButton btnGirl = findViewById(R.id.radio_girl);
        CheckBox c1 = findViewById(R.id.c1);
        CheckBox c2 = findViewById(R.id.c2);
        CheckBox c3 = findViewById(R.id.c3);
        CheckBox c4 = findViewById(R.id.c4);
        c1.setChecked(true);
        //获取按钮对象，并设置点击事件监听器
        Button bt_concern = findViewById(R.id.Button_concern);

        //设置事件监听器
        c1.setOnCheckedChangeListener(this);


        //单击事件
        bt_concern.setOnClickListener(v -> {

        });
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {

    }
}
