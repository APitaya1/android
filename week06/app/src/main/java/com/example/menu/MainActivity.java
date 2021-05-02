package com.example.menu;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.menu.databinding.ActivityMainBinding;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 注册浮动上下文菜单
        registerForContextMenu(binding.etName);

        // 上下文操作模式的上下文菜单
        // 重点：为按钮设置上下文操作模式【在应用顶部出现操作栏】
        // 第一步：实现ActionMode的内部CallBack回调接口
        // 第二步：在view的长按事件中启动上下文操作模式
//        binding.etName.setOnLongClickListener(view -> {
//            if (actionMode != null) {
//                return false;
//            }
//            actionMode = startActionMode(actionModeCallback);
//            view.setSelected(true);
//            return false;
//        });

        // 设置弹出菜单
        binding.etPhone.setOnClickListener(v -> {
            // 创建弹出式菜单对象（最低版本 11）, 第二个参数是绑定的那个view
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            // 获取菜单填充器
            MenuInflater inflater = popup.getMenuInflater();
            // 填充菜单
            inflater.inflate(R.menu.popup_menu, popup.getMenu());
            //绑定菜单项的点击事件
            popup.setOnMenuItemClickListener(MainActivity.this);
            // 显示弹出菜单
            popup.show();
        });
    }

    // 创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        menu.add(Menu.NONE, Menu.FIRST, 3, "帮助").setIcon(R.drawable.ic_help);
        setIconsVisible(menu, true);

        // SearchView的初始化
        SearchView searchView = null;
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(MainActivity.this, "搜索字符串：" + query, Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    // 选项菜单项的选择事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_search) {

        } else if (item.getItemId() == R.id.item_download ||
                item.getItemId() == R.id.item_settings ||
                item.getItemId() == Menu.FIRST) {
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 创建上下文浮动菜单的回调方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.et_name) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
            menu.add(Menu.NONE, Menu.FIRST, 0, "拷贝");
        }
    }

    // 上下文浮动菜单项的选择事件
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_copy || item.getItemId() == R.id.item_paste) {
            Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.item_clear) {
            binding.etName.setText("");
            return true;
        }
        return super.onContextItemSelected(item);
    }

    // 上下文操作模式对象
    private ActionMode actionMode;

    // 实现ActionMode 中 CallBack回调接口
    final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        // 创建方法，在启动上下文操作模式startActionMode(Callback)时调用
        // 在此配置上下文菜单的资源
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        // 在创建方法后进行调用
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        // 菜单项被点击，类似onContextItemSelected()方法
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_copy:
                    Toast.makeText(MainActivity.this, "拷贝", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_paste:
                    Toast.makeText(MainActivity.this, "粘贴", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_clear:
                    binding.etName.setText("");
                    break;
                default:
                    break;
            }
            return true;
        }

        // 上下文操作模式结束时被调用
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    // PopupMenu菜单项的点击事件
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_copy:
                Toast.makeText(this, "复制···", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_delete:
                Toast.makeText(this, "删除···", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }

    ;

    /**
     * 使用反射方法显示图标
     *
     * @param menu 菜单对象
     * @param flag 显示图标的标识符
     */
    private void setIconsVisible(Menu menu, boolean flag) {
        //判断menu是否为空
        if (menu != null) {
            try {
                //如果不为空,使用反射获取 menu对象的的setOptionalIconsVisible方法
                Method method = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                //强制访问该方法
                method.setAccessible(true);
                //调用该方法显示icon
                method.invoke(menu, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}