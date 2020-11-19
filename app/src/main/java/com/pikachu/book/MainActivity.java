package com.pikachu.book;
// STOPSHIP: 2020/11/18 pikachu

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_pager)
    ViewPager mainPager;
    @BindView(R.id.main_nar_one)
    LinearLayout mainNarOne;
    @BindView(R.id.main_nar_tow)
    LinearLayout mainNarTow;
    @BindView(R.id.main_nar_three)
    LinearLayout mainNarThree;
    private View[] views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    //
    private void init() {
        addPager();
    }


    private void addPager() {

        LayoutInflater from = LayoutInflater.from(this);
        views = new View[]{from.inflate(R.layout.pager_main_ui_one, null),
                from.inflate(R.layout.pager_main_ui_one, null),
                from.inflate(R.layout.pager_main_ui_one, null)};
        List<View> listView = new ArrayList<>(Arrays.asList(views));
        mainPager.setAdapter(new PagerAdapter(listView));

    }


    @OnClick({R.id.main_nar_one, R.id.main_nar_tow, R.id.main_nar_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_nar_one:

                break;
            case R.id.main_nar_tow:

                break;
            case R.id.main_nar_three:

                break;
        }
    }
}