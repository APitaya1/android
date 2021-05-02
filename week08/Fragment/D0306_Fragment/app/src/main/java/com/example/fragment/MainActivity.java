package com.example.fragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements ContentFragment.OnItemSelectedListener {
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_static);

        replaceFragment(R.id.fragment_title, TitleFragment.newInstance());
        replaceFragment(R.id.fragment_content, ContentFragment.newInstance("content_param"));

        TitleFragment titleFragment = (TitleFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_title);

        // 设置Fragment的监听器
        manager.addFragmentOnAttachListener(new FragmentOnAttachListener() {
            @Override
            public void onAttachFragment(@NonNull FragmentManager fragmentManager,
                                         @NonNull Fragment fragment) {
                if (fragment instanceof ContentFragment) {
                    ContentFragment contentFragment = (ContentFragment) fragment;
                    contentFragment.setOnItemSelectedListener(MainActivity.this);
                }
            }
        });
    }

    // 实现接口方法
    @Override
    public void onContentSelected(int position) {
        Toast.makeText(this, String.format(Locale.CHINA, "点击了第%d个", position),
                Toast.LENGTH_SHORT).show();
    }

    private void replaceFragment(int containerId, Fragment fragment) {
        // 1. 调用getSupportFragmentManager()获取兼容库的FragmentManager
        manager = getSupportFragmentManager();
        manager.beginTransaction() // 2. 开启一个事务
                .replace(containerId, fragment)// 3. 向容器添加或替换fragment
                .addToBackStack(null)// 4. 将fragment加入回退栈，但后续不再访问（可选）
                .commit(); // 5. 提交事务
    }

}