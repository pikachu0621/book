package com.pikachu.book.home.fragment.tow.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pikachu.book.R;
import com.pikachu.book.cls.sql.F2BooksData;
import com.pikachu.book.tools.base.BaseFragment;
import com.pikachu.book.tools.untli.Tools;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;


public class F2BooksFragment extends BaseFragment {


    private final boolean isBooks;
    private View inflate;
    private FragmentActivity activity;
    private RecyclerView recyclerView;
    private F2BooksRecyclerAdapter f2BooksRecyclerAdapter;
    private SmartRefreshLayout refreshLayout;

    public F2BooksFragment(boolean isBooks) {
        this.isBooks = isBooks;
    }




    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_tab, container, false);
        activity = getActivity();
        recyclerView = inflate.findViewById(R.id.tab_recycler);
        refreshLayout = inflate.findViewById(R.id.tab_refreshLayout);
        init();
        return inflate;
    }

    @Override
    protected void initData() {
        refreshLayout.autoRefresh();//自动刷新
        loadData();
    }


    private void init() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setOnRefreshListener(refreshlayout -> loadData());
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
    }





    private void loadData(){


        new Thread(() -> {

            //模拟延迟
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //模拟加载数据
            ArrayList<F2BooksData> f2BooksData = new ArrayList<>();
            for (int i = 0;i<(isBooks?20:50);i++){
                f2BooksData.add(new F2BooksData("第一章 蟒雀吞龙",
                        "https://ims-cdn0.sm.cn/ims?kt=url&at=novel&key=aHR0cHM6Ly9ndy5hbGljZG4uY29tL0wxLzcyMy8xNTA1Mjk1MzA3LzQ4LzVjLzUwLzQ4NWM1MGZhYmY0YzEzNmY5ZGRkYzMwOWUxMjJlZmUzLmpwZw==&sign=yx:mhfWIS-vhd_C2wcePuiowWM5WOA=&tv=320_320&x.jpg",
                        null,0,"元尊-"+i,0,null,null,null,null,0));
            }





            activity.runOnUiThread(() -> {

                if (f2BooksRecyclerAdapter==null){
                    f2BooksRecyclerAdapter = new F2BooksRecyclerAdapter(activity, isBooks, f2BooksData, (v, position, f2BooksData1) -> Tools.showToast(activity,"点击列表-->"+position));
                    recyclerView.setAdapter(f2BooksRecyclerAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(activity,isBooks?3:4));
                }else {
                    f2BooksRecyclerAdapter.refreshData(f2BooksData);
                    f2BooksRecyclerAdapter.notifyDataSetChanged();
                }
                refreshLayout.finishRefresh(true);//结束刷新（刷新成功）

            });

        }).start();






    }





    //用于刷新列表
    public void refreshList(){
        loadData();
    }


    

    @Override
    protected void onInvisible() {

    }


}