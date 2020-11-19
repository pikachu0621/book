package com.pikachu.book.activity.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;

import com.pikachu.book.R;
import com.pikachu.book.activity.main.MainActivity;

public class ThreeRootLayout {


    private final View view;

    public ThreeRootLayout(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.pager_main_ui_tow, null);
    }


    public View getView() {




        return view;
    }

}
