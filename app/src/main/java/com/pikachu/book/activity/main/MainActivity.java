package com.pikachu.book.activity.main;
// STOPSHIP: 2020/11/18 pikachu

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.pikachu.book.activity.pager.OneRootLayout;
import com.pikachu.book.activity.pager.ThreeRootLayout;
import com.pikachu.book.activity.pager.TowRootLayout;
import com.pikachu.book.adapter.MainPagerAdapter;
import com.pikachu.book.R;
import com.pikachu.book.activity.BaseActivity;
import com.pikachu.book.tools.PKStatusBarActivity;
import com.pikachu.book.tools.PKStatusBarTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends PKStatusBarActivity {


    @BindView(R.id.main_pager)
    ViewPager mainPager;
    @BindView(R.id.main_nar_one)
    LinearLayout mainNarOne;
    @BindView(R.id.main_nar_tow)
    LinearLayout mainNarTow;
    @BindView(R.id.main_nar_three)
    LinearLayout mainNarThree;

    @BindViews({R.id.main_nar_text1, R.id.main_nar_text2, R.id.main_nar_text3})
    TextView[] id_text;
    @BindViews({R.id.main_nar_image1, R.id.main_nar_image2, R.id.main_nar_image3})
    ImageView[] id_image;

    int[] drawable = {R.drawable.ic_main_one, R.drawable.ic_main_tow, R.drawable.ic_main_three};
    int[] drawable2 = {R.drawable.ic_main_one_1, R.drawable.ic_main_tow_1, R.drawable.ic_main_three_1};

    private View[] views;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PKStatusBarTools.with(this).noToNON().init();
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        addPager();
    }


    /**
     * 添加_主分页
     */
    private void addPager() {

        views = new View[]{new OneRootLayout(this).getView(),
                new TowRootLayout(this).getView(),
                new ThreeRootLayout(this).getView()};
        List<View> listView = new ArrayList<>(Arrays.asList(views));
        mainPager.setAdapter(new MainPagerAdapter(listView));

        mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int positionUp = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //showToast(position + "当前");
                setPageItem(position);
            }

            /**
             * 切换底部视图数据
             * @param position
             */
            private void setPageItem(int position) {
                (id_text[positionUp]).setVisibility(View.GONE);
                (id_image[positionUp]).setImageResource(drawable2[positionUp]);
                positionUp = position;
                (id_text[position]).setVisibility(View.VISIBLE);
                (id_image[position]).setImageResource(drawable[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    /**
     * 底部导航栏 点击事件
     *
     * @param view
     */
    @OnClick({R.id.main_nar_one, R.id.main_nar_tow, R.id.main_nar_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_nar_one:
                mainPager.setCurrentItem(0);
                break;
            case R.id.main_nar_tow:
                mainPager.setCurrentItem(1);
                break;
            case R.id.main_nar_three:
                mainPager.setCurrentItem(2);
                break;
        }
    }
}