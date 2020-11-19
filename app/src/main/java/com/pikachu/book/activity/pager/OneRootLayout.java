package com.pikachu.book.activity.pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pikachu.book.R;
import com.pikachu.book.activity.main.MainActivity;
import com.pikachu.book.adapter.MainOnePagerAdapter;
import com.pikachu.book.frament.main_one.PagerMainOneFragment;
import com.pikachu.book.tools.PKStatusBarTools;


import java.util.ArrayList;

@SuppressLint("NonConstantResourceId")
public class OneRootLayout{




    private final View view;
    private final Context context;
    private TextView text1;
    private TextView text2;
    private ViewPager pager;
    private ArrayList<Fragment> fragments;

    public OneRootLayout(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.pager_main_ui_one, null);
        findView();
        init();
    }

    private void findView() {
        text1 = view.findViewById(R.id.pager_main_one_text1);
        text2 = view.findViewById(R.id.pager_main_one_text2);
        pager = view.findViewById(R.id.pager_main_one_pager);
    }


    private void init() {


        fragments = new ArrayList<>();
        fragments.add(new PagerMainOneFragment("",true));
        fragments.add(new PagerMainOneFragment("",false));


        MainOnePagerAdapter mainOnePagerAdapter = new MainOnePagerAdapter(((MainActivity)context).getSupportFragmentManager(),fragments);
        pager.setAdapter(mainOnePagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    text1.setTextColor(0xFFFFFFFF);
                    text2.setTextColor(0x50FFFFFF);
                }else {
                    text2.setTextColor(0xFFFFFFFF);
                    text1.setTextColor(0x50FFFFFF);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });



        text1.setOnClickListener(v -> pager.setCurrentItem(0));
        text2.setOnClickListener(v -> pager.setCurrentItem(1));


    }


    public View getView() {
        return view;
    }
}
