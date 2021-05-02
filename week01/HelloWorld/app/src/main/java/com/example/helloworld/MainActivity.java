package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_0;
    Button bt_1;
    Button bt_2;
    Button bt_3;
    Button bt_4;
    Button bt_5;
    Button bt_6;
    Button bt_7;
    Button bt_8;
    Button bt_9;
    Button bt_point;//小数点按钮
    Button bt_clear;//清除按钮
    Button bt_del;//删除按钮
    Button bt_add;//加号按钮
    Button bt_less;//减号按钮
    Button bt_multiply;//乘号按钮
    Button bt_divide;//除号按钮
    Button bt_remainder;//整除按钮
    Button bt_equal;//等号按钮
    EditText tv_result;//显示输入内容的显示屏
    boolean clear_flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayout);
        Log.i("MainActivity","oncreate");

        //实例化按钮
        bt_0 = (Button) findViewById(R.id.bt_0);
        bt_1 = (Button) findViewById(R.id.bt_1);
        bt_2 = (Button) findViewById(R.id.bt_2);
        bt_3 = (Button) findViewById(R.id.bt_3);
        bt_4 = (Button) findViewById(R.id.bt_4);
        bt_5 = (Button) findViewById(R.id.bt_5);
        bt_6 = (Button) findViewById(R.id.bt_6);
        bt_7 = (Button) findViewById(R.id.bt_7);
        bt_8 = (Button) findViewById(R.id.bt_8);
        bt_9 = (Button) findViewById(R.id.bt_9);
        bt_point = (Button) findViewById(R.id.bt_dot);
        bt_clear = (Button) findViewById(R.id.bt_c);
        bt_del = (Button) findViewById(R.id.btn_backspace);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_less = (Button) findViewById(R.id.bt_less);
        bt_multiply = (Button) findViewById(R.id.bt_multipy);
        bt_divide = (Button) findViewById(R.id.bt_divide);
        bt_remainder=(Button)findViewById(R.id.bt_remainder);
        bt_equal = (Button) findViewById(R.id.bt_equal);
        tv_result =(EditText) findViewById(R.id.tv_result);
        tv_result.setText("");

        //按钮的点击事件
        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_point.setOnClickListener(this);
        bt_clear.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_less.setOnClickListener(this);
        bt_multiply.setOnClickListener(this);
        bt_remainder.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_equal.setOnClickListener(this);

    }

    public void onClick(View v) {
        String str=tv_result.getText().toString();

        switch (v.getId()){
            case R.id.bt_0:
            case R.id.bt_1:
            case R.id.bt_2:
            case R.id.bt_3:
            case R.id.bt_4:
            case R.id.bt_5:
            case R.id.bt_6:
            case R.id.bt_7:
            case R.id.bt_8:
            case R.id.bt_9:
            case R.id.bt_dot:
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    tv_result.setText("0");
                }
                tv_result.setText(str+((Button)v).getText());
                break;

            //加减乘除
            case R.id.bt_add:
            case R.id.bt_less:
            case R.id.bt_multipy:
            case R.id.bt_divide:
            case R.id.bt_remainder:
                if(clear_flag){
                    clear_flag=false;
                    tv_result.setText("");
                }
                tv_result.setText(str+" "+((Button)v).getText()+" ");
                break;

            case R.id.btn_backspace:
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    tv_result.setText("");
                }else if(str!=null&&!str.equals("")){
                    tv_result.setText(str.substring(0,str.length()-1));
                    if(str.length()==1){
                        tv_result.setText("0");
                    }
                }
                break;


            case R.id.bt_c:
                if(clear_flag){
                    clear_flag=false;
                    str="";//将str设置为空
                    tv_result.setText(null);}
                tv_result.setText("");
                break;
            case R.id.bt_equal:
                getResult();
                break;


        }
    }
    private void getResult(){
        String exp=tv_result.getText().toString();
        if(exp==null||exp.equals("")){
            return;
        }
        if(!exp.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag=false;
            return;
        }
        clear_flag=true;
        double result=0;
        String s1=exp.substring(0,exp.indexOf(" "));
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        String  s2=exp.substring(exp.indexOf(" ")+3);

        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                result = d1+d2;
            }else if(op.equals("-")){
                result = d1-d2;
            }else if(op.equals("x")){
                result = d1*d2;
            }else if(op.equals("/")){
                if(d2==0){
                    result = 0;
                }else{
                    result = d1/d2;
                }
            }
            else if(op.equals("%")){
                result=d1%d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&(int)result==result){//如果两个数字都是整数，并且结果为整数要强制转化为int
                int r =(int) result;
                tv_result.setText(r+"");
            }else {
                tv_result.setText(result+"");
            }
        }

        else if(!s1.equals("")&&s2.equals("")){//如果第二个为空，则不计算
            tv_result.setText(exp);
        }else if(s1.equals("")&&!s2.equals("")){//如果第一个为空，则计算，即在之前的结果上在计算
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                result = d2;
            }else if(op.equals("-")){
                result = d2;
            }else if(op.equals("X")){
                result = 0;
            }else if(op.equals("/")){
                result = 0;
            }
            else if(op.equals("%")){
                result = d2;
            }
            if(!s2.contains(".")){
                int r =(int) result;
                tv_result.setText(r+"");
            }else {
                tv_result.setText(result+"");
            }

        }else{
            tv_result.setText("");
        }

    }
}