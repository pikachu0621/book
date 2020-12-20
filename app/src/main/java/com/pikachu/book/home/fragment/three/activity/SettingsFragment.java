package com.pikachu.book.home.fragment.three.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.pikachu.book.R;


public class SettingsFragment extends Fragment {


    private View inflate;
    private LinearLayout fragmentSettingLin1;
    private LinearLayout fragmentSettingLin2;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_settings, container, false);
        return inflate;
    }

    private void initView() {
        fragmentSettingLin1 = inflate.findViewById(R.id.fragment_setting_lin1);
        fragmentSettingLin2 = inflate.findViewById(R.id.fragment_setting_lin2);
    }
}