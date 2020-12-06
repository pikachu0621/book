package com.pikachu.book.home.fragment.one;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.pikachu.book.R;
import com.pikachu.book.book.search.SearchActivity;
import com.pikachu.book.tools.adapter.PagerAdapter;
import com.pikachu.book.cls.JsonTabCls;
import com.pikachu.book.home.fragment.one.fragment.BoyAndGirlFragment;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.url.LoadUrl;

import java.util.ArrayList;


public class OneRootFragment extends Fragment {

    private View inflate;
    private FragmentActivity activity;
    private View viewNon;
    private TextView text1;
    private TextView text2,text3;
    private ViewPager pager;
    private ArrayList<Fragment> fragments;
    private boolean isBoy = true;

    public OneRootFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_h_root1, container, false);
        activity = getActivity();
        findView();
        init();
        return inflate;
    }


    private void findView() {
        text1 = inflate.findViewById(R.id.pager_main_one_text1);
        text2 = inflate.findViewById(R.id.pager_main_one_text2);
        text3 = inflate.findViewById(R.id.pager_main_one_text3);
        viewNon = inflate.findViewById(R.id.pager_non);
        pager = inflate.findViewById(R.id.pager_main_one_pager);
    }


    private void init() {


        //设置一个占位view 用于占位状态栏
        Tools.setNonHigh(activity,viewNon);
        text3.setOnClickListener(v -> {

            Intent intent = new Intent(activity, SearchActivity.class);
            intent.putExtra("IS_BOY",isBoy);
            startActivity(intent);
        });


        //加载Tab
        new LoadUrl(activity, AppInfo.APP_API_BOOK_TAB_LIST,new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {
                Tools.showToast(activity,"加载分类出错");
            }
            @Override
            public void finish(String str) {

                //加载成功后 截取需要的字符串
                String str1 = Tools.cutStr(str, AppInfo.APP_JS_STR[0], AppInfo.APP_JS_STR[1]);
                //Log.i("test_t",str1);
                JsonTabCls jsonTabCls = new Gson().fromJson(str1, JsonTabCls.class);

                //添加  男女  分类 ViewPager
                fragments = new ArrayList<>();
                fragments.add(new BoyAndGirlFragment(jsonTabCls.getBoy(),true)); // 男
                fragments.add(new BoyAndGirlFragment(jsonTabCls.getGirl(),false)); // 女

                //添加适配器
                pager.setAdapter(new PagerAdapter(getFragmentManager(),fragments));
                pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {



                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

                    @Override
                    public void onPageSelected(int position) {
                        if (position == 0){
                            text1.setTextColor(0xFFFFFFFF);
                            text2.setTextColor(0x50FFFFFF);
                            isBoy = true;
                        }else {
                            text2.setTextColor(0xFFFFFFFF);
                            text1.setTextColor(0x50FFFFFF);
                            isBoy = false;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) { }
                });
                text1.setOnClickListener(v -> pager.setCurrentItem(0));
                text2.setOnClickListener(v -> pager.setCurrentItem(1));

            }
        });


    }








}