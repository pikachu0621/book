package com.pikachu.book.home.fragment.three;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.pikachu.book.R;


public class ThreeRootFragment extends Fragment {

    private View inflate;
    private FragmentActivity activity;

    public ThreeRootFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_h_root3, container, false);
        activity = getActivity();
        init();
        return inflate;
    }

    private void init() {



    }





}