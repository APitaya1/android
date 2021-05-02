package com.example.dialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dialogfragment.databinding.DialogLoginBinding;

public class LoginDialog extends DialogFragment {
    private OnLoginInputListener listener;
    private DialogLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DialogLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // 登录按钮的事件监听处理
        binding.btnLogin.setOnClickListener(v -> {
            // 获取输入的用户名和密码
            String username = binding.etUsername.getText().toString();
            String password = binding.etPassword.getText().toString();
            // 触发MainActivity的确定按钮的事件
            listener.onDialogPositiveClick(username, password);
            // 关闭登录对话框
            LoginDialog.this.dismiss();
        });

        // 取消按钮的事件监听处理
        binding.btnCancel.setOnClickListener(v -> {
            // 触发MainActivity的取消按钮的事件
            listener.onDialogNegativeClick(LoginDialog.this);
            // 关闭登录对话框
            LoginDialog.this.dismiss();
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 验证宿主Activity实现了回调接口
        try {
            // 实例化LoginInputListener，将事件对象传给Activity
            listener = (OnLoginInputListener) context;
        } catch (ClassCastException e) {
            // 如果Activity未实现接口，则抛出异常
            throw new ClassCastException(getActivity().toString()
                    + " 必须实现LoginInputListener");
        }
    }

    // 登录信息的监听回调接口
    public interface OnLoginInputListener {
        public void onDialogPositiveClick(String username, String password);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public void onStart() {
        /*
            因为View在添加后,对话框最外层的ViewGroup并不知道导入的View需要的的宽度。
            所以需要在onStart生命周期里修改对话框尺寸参数
         */
        WindowManager.LayoutParams params = requireDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        requireDialog().getWindow().setAttributes(params);
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //此方法在视图创建后返回，但是还没有添加到父级中，此处可以重新设定view的各个数据
    }

    // 创建带有按钮的对话框
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        // Get the layout inflater
//        LayoutInflater inflater = requireActivity().getLayoutInflater();
//        builder.setView(inflater.inflate(R.layout.dialog_login, null))
//                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // sign in the user ...
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        LoginDialog.this.getDialog().cancel();
//                    }
//                });
//        return builder.create();
//    }

}
