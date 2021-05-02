package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements ContentFragment.OnItemSelectedListener{
//    private SparseArray fragements;

    private FragmentManager manger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inintFragment();
        replaceFragment(R.id.fragment_title,fragements.get(R.id.fragment_title));
        replaceFragment(R.id.fragment_content,fragements.get(R.id.fragment_content));

        manger.addFragmentOnAttachListener(new FragmentOnAttachListener() {
            @Override
            public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
                if (fragment instanceof ContentFragment){
                    ContentFragment contentFragment =(ContentFragment)fragment;
                    contentFragment.setOnItemSelectedListener(MainActivity.this);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.top:
                Toast.makeText(this,"wuhu",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onContentSelected(int position) {
        Toast.makeText(this,String.format(Locale.CHINA,"点击了第%d个",position),Toast.LENGTH_SHORT).show();
    }

    private  SparseArray<Fragment> fragements;

    private void inintFragment() {
        fragements=new SparseArray<>();
        fragements.put(R.id.fragment_title,TitleFragment.newInstance());
        fragements.put(R.id.fragment_content,ContentFragment.newInstance("内容fragment"));
    }

    private void replaceFragment(int containerId, Fragment fragment){
        manger=getSupportFragmentManager();
        manger.beginTransaction()
                .replace(containerId,fragment)
                .addToBackStack(null)
                .commit();
    }
}