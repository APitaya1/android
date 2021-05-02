package com.example.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class LogoutDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())  // 使用Builder构造对话框
                .setTitle("提示")  // 设置标题
                .setMessage("确认退出？") // 设置消息
                // 确认按钮的事件处理
                .setPositiveButton("确认", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    LogoutDialog.this.getActivity().finish();
                })
                // 取消按钮的事件处理
                .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                .create();  // 创建对话框
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //此方法在视图创建后返回，但是还没有添加到父级中，此处可以重新设定view的各个数据
    }
}
