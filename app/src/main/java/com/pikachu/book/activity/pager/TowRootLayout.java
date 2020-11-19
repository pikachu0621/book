package com.pikachu.book.activity.pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;

import com.pikachu.book.R;
import com.pikachu.book.activity.main.MainActivity;

public class TowRootLayout {


    private final View view;

    public TowRootLayout(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.pager_main_ui_three, null);
    }




    public View getView() {




        return view;
    }

}
