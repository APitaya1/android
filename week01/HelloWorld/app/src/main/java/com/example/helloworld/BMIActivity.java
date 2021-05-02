package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity implements View.OnClickListener {
    TextView weight;
    TextView height;
    Button btn_result;
    RadioButton boy;
    RadioButton girl;
    TextView BMI;
    TextView Health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);

        weight=(TextView)findViewById(R.id.weight);
        height=(TextView)findViewById(R.id.height);
        btn_result=(Button) findViewById(R.id.result);
        boy=(RadioButton) findViewById(R.id.rb_boy);
        girl=(RadioButton) findViewById(R.id.rb_girl);
        BMI=(TextView)findViewById(R.id.bmi);
        Health=(TextView)findViewById(R.id.health);
        BMI.setText("");
        Health.setText("");
        btn_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DecimalFormat nf = new DecimalFormat("0.00");  //保留两位小数点
        double Weight=Double.parseDouble(weight.getText().toString());
        double Height=Double.parseDouble(height.getText().toString());
        double result=0;

        if(Weight<=0 || Height<=0) {
            BMI.setText("值异常，不计算");
            Health.setText("值异常，不计算");
            return ;
        }
        result=(Weight/2)/(Height*Height);
        BMI.setText(nf.format(result));

        //男生BMI
        if(boy.isChecked()){
            if(result<20)
                Health.setText("体重过轻");
            else if(result>=20&&result<25)
                Health.setText("体重正常");
            else if(result>=25&&result<27)
                Health.setText("体重超重");
            else if(result>=27&&result<30)
                Health.setText("轻度肥胖");
            else if(result>=30&&result<35)
                Health.setText("中度肥胖");
            else if(result>=35)
                Health.setText("重度肥胖");
        }

        //女生BMI
        if(girl.isChecked()){
            if(result<19)
                Health.setText("体重过轻");
            else if(result>=19&&result<24)
                Health.setText("体重正常");
            else if(result>=24&&result<26)
                Health.setText("体重超重");
            else if(result>=26&&result<29)
                Health.setText("轻度肥胖");
            else if(result>=29&&result<34)
                Health.setText("中度肥胖");
            else if(result>=34)
                Health.setText("重度肥胖");
        }
    }
}