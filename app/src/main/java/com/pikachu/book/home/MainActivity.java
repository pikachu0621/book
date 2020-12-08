package com.pikachu.book.home;
// STOPSHIP: 2020/11/18 pikachu

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pikachu.book.R;
import com.pikachu.book.home.fragment.one.OneRootFragment;
import com.pikachu.book.home.fragment.three.ThreeRootFragment;
import com.pikachu.book.home.fragment.tow.TowRootFragment;
import com.pikachu.book.tools.adapter.PagerAdapter;
import com.pikachu.book.tools.state.PKStatusBarActivity;
import com.pikachu.book.tools.state.PKStatusBarTools;
import java.util.ArrayList;


@SuppressLint("NonConstantResourceId")
public class MainActivity extends PKStatusBarActivity {

    private ViewPager mainPager;
    private TextView[] id_text;
    private ImageView[] id_image;

    int[] drawable = {R.drawable.ic_main_one, R.drawable.ic_main_tow, R.drawable.ic_main_three};
    int[] drawable2 = {R.drawable.ic_main_one_1, R.drawable.ic_main_tow_1, R.drawable.ic_main_three_1};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PKStatusBarTools.with(this).noToNON().init();
        initView();
        init();
    }

    private void init() {
        addPager();
    }

    /**
     * 添加_主分页
     */
    private void addPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneRootFragment());
        fragments.add(new TowRootFragment());
        fragments.add(new ThreeRootFragment());
        mainPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments));
        mainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int positionUp = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
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
        mainPager.setOffscreenPageLimit(3);
    }

    private void initView() {
        mainPager = findViewById(R.id.main_pager);

        id_text = new TextView[]{findViewById(R.id.main_nar_text1),
                findViewById(R.id.main_nar_text2),
                findViewById(R.id.main_nar_text3)};
        id_image = new ImageView[]{findViewById(R.id.main_nar_image1),
                findViewById(R.id.main_nar_image2),
                findViewById(R.id.main_nar_image3)};

        //底部导航栏 点击事件
        findViewById(R.id.main_nar_one).setOnClickListener(v -> mainPager.setCurrentItem(0));
        findViewById(R.id.main_nar_tow).setOnClickListener(v -> mainPager.setCurrentItem(1));
        findViewById(R.id.main_nar_three).setOnClickListener(v -> mainPager.setCurrentItem(2));
    }
}