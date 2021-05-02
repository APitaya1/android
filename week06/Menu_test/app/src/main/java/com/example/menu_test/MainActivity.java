package com.example.menu_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menu_test.databinding.ActivityMainBinding;

import java.nio.file.AccessMode;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        binding.etName.setOnLongClickListener(view -> {
            if (actionMode != null) {
                return false;
            }
            final ActionMode.Callback actionModeCallBack = new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.context_menu, menu);
                    return false;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.item_copy)||item.getItemId() == R.id.item_paste){
                        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG);
                        return true;
                    }else if (item.getItemId() == R.id.item_clear) {
                        binding.etName.setText("");
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            };
            actionMode = startActionMode(actionModeCallBack);
            view.setSelected(true);
            return false;
        });
    }


}