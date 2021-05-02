package com.example.dialogfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.dialogfragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements LoginDialog.OnLoginInputListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置退出按钮的事件监听器，处理点击事件
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutDialog logoutDialog = new LogoutDialog();
                logoutDialog.show(getSupportFragmentManager(), "退出");
            }
        });
        // 设置登录按钮的事件监听器，处理点击事件
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.show(getSupportFragmentManager(), "登录");
            }
        });
    }

    // 登录事件处理
    @Override
    public void onDialogPositiveClick(String username, String password) {
        Toast.makeText(this, username + ", " + password, Toast.LENGTH_SHORT).show();
    }
    // 取消事件处理
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
    }
}