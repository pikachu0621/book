package com.pikachu.book.home.fragment.tow;

import android.app.ActivityOptions;
import android.content.Intent;
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
import com.pikachu.book.book.info.BookInfoActivity;
import com.pikachu.book.cls.sql.F2BooksData;
import com.pikachu.book.home.fragment.tow.fragment.F2BooksFragment;
import com.pikachu.book.home.fragment.tow.fragment.F2BooksRecyclerAdapter;
import com.pikachu.book.tools.adapter.PagerAdapter;
import com.pikachu.book.tools.base.BaseFragment;
import com.pikachu.book.tools.dao.DaoTools;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.Tools;
import java.util.ArrayList;


public class TowRootFragment extends BaseFragment implements F2BooksRecyclerAdapter.OnClickItemListener {

    private View inflate;
    private FragmentActivity activity;
    private View mainF2View;
    private TextView mainF2Text1;
    private TextView mainF2Text2;
    private TextView mainF2Text3;
    private ViewPager mainF2Pager;
    private int color1;
    private int color2;
    private F2BooksFragment f2BooksFragment;
    private F2BooksFragment f2BooksFragment1;

    public TowRootFragment() {

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_h_root2, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    @Override
    protected void initData() {
    }


    private void init() {
        //设置一个占位view 用于占位状态栏
        Tools.setNonHigh(activity, mainF2View);
        color1 = getResources().getColor(R.color.white);
        color2 = getResources().getColor(R.color.white_50);




        ArrayList<Fragment> fragments = new ArrayList<>();
        f2BooksFragment = new F2BooksFragment(true, this);
        f2BooksFragment1 = new F2BooksFragment(false, this);

        fragments.add(this.f2BooksFragment);
        fragments.add(this.f2BooksFragment1);
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

    //列表点击事件
    @Override
    public void noClickItem(View v, int position, F2BooksData f2BooksData) {


        //序列化对象传递
        Intent intent = new Intent(activity, BookInfoActivity.class);
        intent.putExtra(AppInfo.APP_SA_BOOK_INFO, DaoTools.f2BooksDataToListBean(f2BooksData));
        intent.putExtra(AppInfo.APP_SA_IS_BOY,true);
        Bundle bundle = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bundle = ActivityOptions.makeSceneTransitionAnimation(activity, v, "ui_m_item_tr").toBundle();
        }
        //#8078D4FF(intent,2,bundle);
        startActivity(intent,bundle);
    }



/*
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvent(DataSynEvent dataSynEvent) {
        Tools.showToast(activity,"触发事件-->"+dataSynEvent.getType());
        if (dataSynEvent.getType() == 1) {
            f2BooksFragment.refreshList();
        }else if (dataSynEvent.getType() == 2){
            f2BooksFragment1.refreshList();
        }
    }
*/


    public void refreshList(int type){
        if (type == 1) {
            f2BooksFragment.refreshList();
        }else if (type == 2){
            f2BooksFragment1.refreshList();
        }
    }



    public void setPager(int tow){
        mainF2Pager.setCurrentItem(tow);
    }





}