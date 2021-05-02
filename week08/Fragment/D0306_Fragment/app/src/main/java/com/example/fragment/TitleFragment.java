package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.fragment.databinding.FragmentTitleBinding;

public class TitleFragment extends Fragment {
    private FragmentTitleBinding binding;

    public TitleFragment() {
        // Required empty public constructor
    }

    public static TitleFragment newInstance() {
        TitleFragment fragment = new TitleFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTitleBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // 设置结果监听器
        getParentFragmentManager().setFragmentResultListener("key", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                        String result = bundle.getString("title");
                        binding.tvTitle.setText(result);
                    }
                });
        return view;
    }
}