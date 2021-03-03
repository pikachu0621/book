package com.pikachu.book.home.fragment.one.fragment.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.pikachu.book.R;
import com.pikachu.book.book.info.BookInfoActivity;
import com.pikachu.book.cls.json.JsonBookItemCls;
import com.pikachu.book.tools.base.BaseFragment;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

public class TabFragment extends BaseFragment  implements RecyclerAdapter.OnClickItemListener {


    private String type;
    private boolean isBoy;
    private View inflate;
    private FragmentActivity activity;
    private RecyclerView recyclerView;
    private int page = 1;
    private RefreshLayout refreshLayout;
    private RecyclerAdapter recyclerAdapter;
    private boolean isOneData = true;


    public TabFragment() {
    }

    public TabFragment(String type, boolean isBoy) {
        this.type = type;
        this.isBoy = isBoy;
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


    private void init() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        refreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setOnRefreshListener(refreshlayout -> load(true));
        refreshLayout.setOnLoadMoreListener(refreshlayout -> load(false));
    }

    private void load(boolean isUpData) {

        if (isUpData) page = 1;
        else page++;

        //加载书
        new LoadUrl(activity, AppInfo.APP_API_BOOK_TYPE_LIST.replace(AppInfo.APP_RE_STR[0], type).replace(AppInfo.APP_RE_STR[1], "" + page), new LoadUrl.OnCall() {


            @Override
            public void error(Exception e) {
                //Tools.showToast(activity, "加载书出错");

                if (isUpData)
                    refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
                else{
                    refreshLayout.finishLoadMore(false);//结束加载（加载失败）
                    page--;
                }

            }

            @Override
            public void finish(String str) {
                //加载成功后 截取需要的字符串
                String str1 = Tools.cutStr(str, "jsonp" + page + "(", ");");
                JsonBookItemCls jsonBookItemCls = new Gson().fromJson(str1, JsonBookItemCls.class);



                if (jsonBookItemCls.getStatus() == 1) {

                    if (recyclerAdapter == null || isUpData) {
                        recyclerAdapter = new RecyclerAdapter(activity, jsonBookItemCls, isBoy,TabFragment.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                        recyclerView.setAdapter(recyclerAdapter);
                    } else{
                        recyclerAdapter.addList(jsonBookItemCls.getList());
                        recyclerAdapter.notifyDataSetChanged();
                    }

                    if (isUpData) refreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else refreshLayout.finishLoadMore(true);//结束加载（加载成功）

                } else {
                    if (isUpData) refreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else{
                        page--;
                        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
                    }
                }

            }
        });

    }


    @Override
    protected void initData() {
        refreshLayout.autoRefresh();//自动刷新
        load(true);
    }



    //列表点击事件

    @Override
    public void onClick(View v, int position, JsonBookItemCls.ListBean listBean) {


        //序列化对象传递
        Intent intent = new Intent(activity, BookInfoActivity.class);
        intent.putExtra(AppInfo.APP_SA_BOOK_INFO,listBean);
        intent.putExtra(AppInfo.APP_SA_IS_BOY,isBoy);
        Bundle bundle = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bundle = ActivityOptions.makeSceneTransitionAnimation(activity, v, "ui_m_item_tr").toBundle();
        }
        //#8078D4FF(intent,2,bundle);
        startActivity(intent,bundle);

    }
}






















