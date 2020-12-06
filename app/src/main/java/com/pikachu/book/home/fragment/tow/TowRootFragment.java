package com.pikachu.book.home.fragment.tow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.pikachu.book.R;
import com.pikachu.book.home.fragment.tow.fragment.F2BooksFragment;
import com.pikachu.book.tools.adapter.PagerAdapter;
import com.pikachu.book.tools.untli.Tools;

import java.util.ArrayList;


public class TowRootFragment extends Fragment {

    private View inflate;
    private FragmentActivity activity;
    private View mainF2View;
    private TextView mainF2Text1;
    private TextView mainF2Text2;
    private TextView mainF2Text3;
    private ViewPager mainF2Pager;
    private int color1;
    private int color2;

    public TowRootFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_h_root2, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    private void init() {
        //设置一个占位view 用于占位状态栏
        Tools.setNonHigh(activity, mainF2View);

        color1 = getResources().getColor(R.color.white);
        color2 = getResources().getColor(R.color.white_50);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new F2BooksFragment(true));
        fragments.add(new F2BooksFragment(false));
        mainF2Pager.setAdapter(new PagerAdapter(getFragmentManager(),fragments));
        mainF2Pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    mainF2Text1.setTextColor(color1);
                    mainF2Text2.setTextColor(color2);
                }else {
                    mainF2Text1.setTextColor(color2);
                    mainF2Text2.setTextColor(color1);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mainF2Text1.setOnClickListener(v -> mainF2Pager.setCurrentItem(0));
        mainF2Text2.setOnClickListener(v -> mainF2Pager.setCurrentItem(1));
    }


    private void initView() {
        mainF2View = inflate.findViewById(R.id.main_f2_view);
        mainF2Text1 = inflate.findViewById(R.id.main_f2_text1);
        mainF2Text2 = inflate.findViewById(R.id.main_f2_text2);
        mainF2Text3 = inflate.findViewById(R.id.main_f2_text3);
        mainF2Pager = inflate.findViewById(R.id.main_f2_pager);

    }
}