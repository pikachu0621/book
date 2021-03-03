package com.pikachu.book.home.fragment.tow.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pikachu.book.R;
import com.pikachu.book.cls.sql.F2BooksData;
import com.pikachu.book.tools.base.BaseFragment;
import com.pikachu.book.tools.dao.DaoTools;
import com.pikachu.book.tools.untli.Tools;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;


public class F2BooksFragment extends BaseFragment {


    private boolean isBooks;
    private F2BooksRecyclerAdapter.OnClickItemListener onClickItemListener;
    private View inflate;
    private FragmentActivity activity;
    private RecyclerView recyclerView;
    private F2BooksRecyclerAdapter f2BooksRecyclerAdapter;
    private SmartRefreshLayout refreshLayout;
    private LinearLayout lin1;
    private DaoTools instance;


    public F2BooksFragment(){}

    public F2BooksFragment(boolean isBooks, F2BooksRecyclerAdapter.OnClickItemListener onClickItemListener) {
        this.isBooks = isBooks;
        this.onClickItemListener = onClickItemListener;
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_tab, container, false);
        activity = getActivity();
        recyclerView = inflate.findViewById(R.id.tab_recycler);
        refreshLayout = inflate.findViewById(R.id.tab_refreshLayout);
        lin1 = inflate.findViewById(R.id.tab_lin1);
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

        instance = DaoTools.getInstance(activity);
    }

    private void loadData() {
        new Thread(() -> {

            //加载数据

            List<F2BooksData> f2BooksData;
            if (isBooks)
                f2BooksData = instance.inquireBookFrame();
            else
                f2BooksData = instance.inquireBookHistory();

            activity.runOnUiThread(() -> {
                if (f2BooksData == null || f2BooksData.size() <= 0) {
                    lin1.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    refreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    return;
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    lin1.setVisibility(View.GONE);
                }


                if (f2BooksRecyclerAdapter == null) {
                    f2BooksRecyclerAdapter = new F2BooksRecyclerAdapter(activity, isBooks, f2BooksData, onClickItemListener);
                    recyclerView.setAdapter(f2BooksRecyclerAdapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(activity, isBooks ? 3 : 4));
                } else {
                    f2BooksRecyclerAdapter.refreshData(f2BooksData);
                    f2BooksRecyclerAdapter.notifyDataSetChanged();
                }

                refreshLayout.finishRefresh(true);//结束刷新（刷新成功）
            });

        }).start();


    }

    //用于刷新列表
    public void refreshList() {
        loadData();
    }


}