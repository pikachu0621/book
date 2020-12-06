package com.pikachu.book.book.info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.pikachu.book.R;
import com.pikachu.book.book.info.comments.CommentsInfoActivity;
import com.pikachu.book.cls.JsonBookChapterCls;
import com.pikachu.book.cls.JsonBookCommentsCls;
import com.pikachu.book.cls.JsonBookItemCls;
import com.pikachu.book.home.fragment.one.fragment.fragment.RecyclerAdapter;
import com.pikachu.book.home.fragment.one.fragment.fragment.TabFragment;
import com.pikachu.book.tools.base.BaseFragment;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.url.LoadUrl;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;


public class BookChapterFragment extends BaseFragment implements BookChapterRecycler.OnClickItemCHListener, BookChapterRecycler.OnClickItemCListener {

    private final boolean isBookChapter;
    private String host;
    private String token;
    private View inflate;
    private FragmentActivity activity;
    private int page;
    private final String title;
    private final String id;
    private final String author;
    private SmartRefreshLayout bookInfoRefreshLayout;
    private RecyclerView bookInfoRecycler;
    private BookChapterRecycler bookChapterRecycler;


    public BookChapterFragment(JsonBookItemCls.ListBean listBean, String host, String token) {
        this.isBookChapter = true;
        title = listBean.getTitle();
        id = listBean.getId();
        author = listBean.getAuthor();
        this.host = host;
        this.token = token;
    }


    public BookChapterFragment(JsonBookItemCls.ListBean listBean) {
        this.isBookChapter = false;
        title = listBean.getTitle();
        id = listBean.getId();
        author = listBean.getAuthor();
    }


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_book_chapter, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    private void init() {
        bookInfoRefreshLayout.setRefreshHeader(new ClassicsHeader(activity));
        bookInfoRefreshLayout.setRefreshFooter(new ClassicsFooter(activity));
        bookInfoRefreshLayout.setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
        bookInfoRefreshLayout.setDisableContentWhenLoading(true);//是否在加载的时候禁止列表的操作
        bookInfoRefreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        /*bookInfoRefreshLayout.setEnableRefresh(!isBookChapter);//是否启用下拉刷新功能
        bookInfoRefreshLayout.setEnableLoadMore(!isBookChapter);//是否启用上拉加载功能*/
        bookInfoRefreshLayout.setOnRefreshListener(refreshlayout -> load(true));
        bookInfoRefreshLayout.setOnLoadMoreListener(refreshlayout -> load(false));
    }


    @Override
    protected void initData() {
        bookInfoRefreshLayout.autoRefresh();//自动刷新
        load(true);
    }

    private void load(boolean isUpData) {

        if (isUpData)
            if (isBookChapter)
                page = 0;
            else
                page = 1;
        else page++;

        String url;
        if (isBookChapter) {
            url = AppInfo.APP_API_BOOK_CH.replace(AppInfo.APP_RE_STR[1], "" + page)
                    .replace(AppInfo.APP_RE_STR[2], title)
                    .replace(AppInfo.APP_RE_STR[3], author)
                    .replace(AppInfo.APP_RE_STR[4], id)
                    .replace(AppInfo.APP_RE_STR[5], host)
                    .replace(AppInfo.APP_RE_STR[6], token);
        } else {
            url = AppInfo.APP_API_COMMENTS.replace(AppInfo.APP_RE_STR[1], "" + page)
                    .replace(AppInfo.APP_RE_STR[2], title)
                    .replace(AppInfo.APP_RE_STR[3], author)
                    .replace(AppInfo.APP_RE_STR[4], id);
        }

        //加载
        new LoadUrl(activity, url, new LoadUrl.OnCall() {



            public void dd( boolean is){
                if (is)
                    bookInfoRefreshLayout.finishRefresh(false);//结束刷新（刷新失败）
                else {
                    bookInfoRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
                    page--;
                }
            }

            //加载失败或者出错
            @Override
            public void error(Exception e) {
                dd(isUpData);
            }

            @Override
            public void finish(String str) {

                //加载成功后 截取需要的字符串
                String str1 = Tools.cutStr(str, "jsonp" + page + "(", ");");
                Log.i("test_t",str1);



                if (str1 == null || str1.equals("")) {
                   dd(isUpData);
                   return;
                }
                int listDataSize = -1;
                JsonBookChapterCls jsonBookChapterCls = null;
                JsonBookCommentsCls jsonBookCommentsCls = null;

                if (isBookChapter) {
                    //扑获异常时的 json 串
                    if (!str1.contains("error\":1")){
                        jsonBookChapterCls = new Gson().fromJson(str1, JsonBookChapterCls.class);
                        if (jsonBookChapterCls==null){
                            dd(isUpData);
                            return;
                        }
                        listDataSize = jsonBookChapterCls.getData().getChapters().size();
                    }else {
                        if (page> 0 && !isUpData){ //可能没有数据也会提示 error = 1 所以这里做个判断
                            bookInfoRefreshLayout.finishLoadMoreWithNoMoreData();
                        }else {
                            dd(isUpData);
                        }

                    }

                } else {

                    if (str1.contains("error\":\"\"")) {
                        jsonBookCommentsCls = new Gson().fromJson(str1, JsonBookCommentsCls.class);
                        if (jsonBookCommentsCls==null){
                            dd(isUpData);
                            return;
                        }
                        listDataSize = jsonBookCommentsCls.getData().getList().size();
                    }else {

                        if (page> 1 && !isUpData){//可能没有数据也会提示 error = 1 所以这里做个判断
                            bookInfoRefreshLayout.finishLoadMoreWithNoMoreData();
                        }else {
                            dd(isUpData);
                        }
                      /*  Tools.showToast(activity,"评论json串异常,请尝试重试");
                        dd(isUpData);*/
                    }
                }


                if (listDataSize > 0) {

                    if (isBookChapter)
                        //章节
                        chapter(isUpData, jsonBookChapterCls);
                    else
                        //评论
                        comments(isUpData, jsonBookCommentsCls);

                    if (isUpData)
                        bookInfoRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else
                        bookInfoRefreshLayout.finishLoadMore(true);//结束加载（加载成功）

                } else {
                    if (isUpData)
                        bookInfoRefreshLayout.finishRefresh(true);//结束刷新（刷新成功）
                    else {
                        //page--;
                        bookInfoRefreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
                    }
                }


            }
        });

    }


    //章节
    private void chapter(boolean isUpData, JsonBookChapterCls jsonBookChapterCls) {

        if (bookChapterRecycler == null || isUpData) {
            bookChapterRecycler = new BookChapterRecycler(activity, jsonBookChapterCls, BookChapterFragment.this);
            bookInfoRecycler.setLayoutManager(new LinearLayoutManager(activity));
            bookInfoRecycler.setAdapter(bookChapterRecycler);
        } else {
            bookChapterRecycler.addList2(jsonBookChapterCls.getData().getChapters());
            bookChapterRecycler.notifyDataSetChanged();
        }

    }
    //评论
    private void comments(boolean isUpData, JsonBookCommentsCls jsonBookCommentsCls) {

        if (bookChapterRecycler == null || isUpData) {
            bookChapterRecycler = new BookChapterRecycler(activity, jsonBookCommentsCls, BookChapterFragment.this);
            bookInfoRecycler.setLayoutManager(new LinearLayoutManager(activity));
            bookInfoRecycler.setAdapter(bookChapterRecycler);
        } else {
            bookChapterRecycler.addList(jsonBookCommentsCls.getData().getList());
            bookChapterRecycler.notifyDataSetChanged();
        }

    }








    private void initView() {
        bookInfoRefreshLayout = inflate.findViewById(R.id.book_info_refreshLayout);
        bookInfoRecycler = inflate.findViewById(R.id.book_info_recycler);
    }




    //章节列表点击
    @Override
    public void onClick(View v, int position, JsonBookChapterCls.DataBean.ChaptersBean listBean) {
        Tools.showToast(activity,"ID--->"+listBean.getId()+"\nNAME"+listBean.getName()+"\nURL--->"+listBean.getUrl());
    }


    //评论列表点击
    @Override
    public void onClick(View v, int position, JsonBookCommentsCls.DataBean.ListBean listBean) {
        Intent intent = new Intent(activity, CommentsInfoActivity.class);
        startActivity(intent);
    }



    @Override
    protected void onInvisible() {
    }


}