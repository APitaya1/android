package com.example.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragment.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment {
    private static final String ARG_PARAM = "param";
    private String mParam;
    private FragmentContentBinding binding;

    public ContentFragment() {
        // Required empty public constructor
    }

    // 创建 Fragment对象
    public static ContentFragment newInstance(String param) {
        ContentFragment fragment = new ContentFragment();
        // 使用Bundle对象装载数据
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        // 传递 bundle对象
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 接收Activity传递的数据
        Bundle bundle = getArguments();
        if (bundle != null) {
            mParam = bundle.getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        if(mParam != null) {
            binding.tvContent.setText(mParam);
        }
        binding.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 调用回调方法
                callback.onContentSelected(1);

                // 生成数据传递给 TitleFragment
                Bundle result = new Bundle();
                result.putString("title", "内容标题");
                getParentFragmentManager().setFragmentResult("key", result);
            }
        });
        return view;
    }

    // 定义接口对象
    private OnItemSelectedListener callback;

    // 捕获接口实现
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            // 获取接口对象
            this.callback = (OnItemSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    // 接口对象的set()方法
    public void setOnItemSelectedListener(OnItemSelectedListener callback) {
        this.callback = callback;
    }

    // 定义接口
    public interface OnItemSelectedListener {
        void onContentSelected(int position);
    }
}