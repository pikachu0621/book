package com.pikachu.book.book.info;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.pikachu.book.R;
import com.pikachu.book.book.info.comments.CommentsInfoActivity;
import com.pikachu.book.book.info.look.LookBookActivity;
import com.pikachu.book.cls.DataSynEvent;
import com.pikachu.book.cls.json.JsonBookChapterCls;
import com.pikachu.book.cls.json.JsonBookCommentsCls;
import com.pikachu.book.cls.json.JsonBookItemCls;
import com.pikachu.book.cls.sql.F2BooksData;
import com.pikachu.book.tools.adapter.PagerAdapter;
import com.pikachu.book.tools.base.BaseActivity;
import com.pikachu.book.tools.dao.DaoTools;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.BookHost;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.url.LoadUrl;
import com.pikachu.book.tools.view.ScoreView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class BookInfoActivity extends BaseActivity implements BookChapterFragment.OnClickFragment {


    private AppBarLayout infoAppbar;
    private Toolbar infoToolbar;
    private ImageView infoImage1;
    private TextView infoText1;
    private ScoreView infoScore1;
    private TextView infoText2;
    private TextView infoText3;
    private TextView infoText4;
    private TabLayout infoTab;
    private ViewPager infoPager;
    private JsonBookItemCls.ListBean listBean;
    private boolean is_boy;
    private int colorW;
    private int colorW5;
    boolean isK = true;
    private BookChapterFragment bookChapterFragment;
    private F2BooksData f2BooksData;
    private Intent intent;
    private AlertDialog.Builder builder;
    private DaoTools instance;
    private int reType = 0 ,reeType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        initView();
        init();
    }

    private void initView() {
        infoAppbar = findViewById(R.id.info_appbar);
        infoToolbar = findViewById(R.id.info_toolbar);
        infoImage1 = findViewById(R.id.info_image1);
        infoText1 = findViewById(R.id.info_text1);
        infoScore1 = findViewById(R.id.info_score1);
        infoText2 = findViewById(R.id.info_text2);
        infoText3 = findViewById(R.id.info_text3);
        infoText4 = findViewById(R.id.info_text4);
        infoTab = findViewById(R.id.info_tab);
        infoPager = findViewById(R.id.info_pager);
    }


    private void init() {
        //反序列化对象
        listBean = (JsonBookItemCls.ListBean) getIntent().getSerializableExtra(AppInfo.APP_SA_BOOK_INFO);
        is_boy = getIntent().getBooleanExtra(AppInfo.APP_SA_IS_BOY, true);
        setTheme(is_boy, infoAppbar);
        colorW = getResources().getColor(R.color.white);
        colorW5 = getResources().getColor(R.color.white_50);


        //读取数据库信息
        readSqlBook();
        //头部信息
        setBookInfo();

    }

    //读取数据库信息
    private void readSqlBook() {
        //判断上次读取的地方/有记录-》设置text4=“继续阅读”
        //如果历史记录或者收藏记录 者不进行加载信息

        instance = DaoTools.getInstance(this);
        List<F2BooksData> f2BooksDatas = instance.inquireBookName(listBean.getTitle());
        if (f2BooksDatas != null && f2BooksDatas.size() > 0) {
            f2BooksData = f2BooksDatas.get(0);
            infoText4.setText("继续阅读");
            infoImage1.setOnClickListener(v -> {
                if (intent == null)
                    intent = new Intent(this, LookBookActivity.class);
                intent.putExtra(AppInfo.APP_SA_IS_BOY, is_boy);
                intent.putExtra(AppInfo.APP_SA_BOOK_SQL_INFO, f2BooksData);
                startActivity(intent);
            });
            // 添加fragment
            listBean = DaoTools.f2BooksDataToListBean(f2BooksData,listBean);
            addPagerFragment(listBean);
        } else {
            //添加章节。评论
            addPager();
        }

    }


    @SuppressLint("SetTextI18n")
    private void setBookInfo() {
        if (listBean != null) {
            //AppBar
            infoToolbar.setTitle(listBean.getTitle());
            //infoToolbar.setTitleTextColor(colorW);
            setSupportActionBar(infoToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //bookInfo
            //作者
            infoText1.setText("作者：" + listBean.getAuthor());
            //评分
            infoScore1.setNotColor(colorW5);
            infoScore1.setTheColor(colorW);
            infoScore1.setTextColor(colorW);
            infoScore1.setMaxPoints(5);
            infoScore1.setPoints(Float.parseFloat(listBean.getMark_score()));
            //有5万人读过，月热度1000
            infoText2.setText("有" + listBean.getReaduv() + "万人读过  --  " + "月热度" + listBean.getMonthuv());
            //内容
            //String replace = listBean.getDescription().replace("\n", "");//去除换行
            infoText3.setText(listBean.getDescription());
            //书封面
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_book_load)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter();
            Glide.with(this)
                    .load(listBean.getIcon())
                    .apply(options)
                    .thumbnail(Glide.with(this)
                            .load(R.drawable.ic_book_load))
                    .into(infoImage1);
        }
        //文字点击收缩
        infoText3.setOnClickListener(v -> {
            if (isK) {
                isK = false;
                infoText3.setEllipsize(null); // 展开
                infoText3.setSingleLine(isK);
            } else {
                isK = true;
                infoText3.setEllipsize(TextUtils.TruncateAt.END); // 收缩
                infoText3.setLines(5);
            }
        });
    }

    private void addPager() {


        //获取必要的 host token
        new LoadUrl(this, AppInfo.APP_API_BOOK_INFO.replace(AppInfo.APP_RE_STR[0], listBean.getTitle() + " " + listBean.getAuthor()), new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {
                showToast("获取 Token , host 失败");
            }

            @Override
            public void finish(String str) {

                String str1 = Tools.cutStr(str, AppInfo.APP_JS_INFO_STR[0], AppInfo.APP_JS_INFO_STR[1]);

                String host = Tools.cutStr(str1, AppInfo.APP_JS_INFO_STR[2], AppInfo.APP_JS_INFO_STR[3]);
                String token = Tools.cutStr(str1, AppInfo.APP_JS_INFO_STR[4], AppInfo.APP_JS_INFO_STR[3]);
                String title = Tools.cutStr(str1, AppInfo.APP_JS_INFO_STR[5], AppInfo.APP_JS_INFO_STR[3]);
                String id = Tools.cutStr(str1, AppInfo.APP_JS_INFO_STR[6], AppInfo.APP_JS_INFO_STR[3]);
                listBean.setId(Tools.toStringHex2(id));
                listBean.setTitle(Tools.toStringHex2(title));
                listBean.setHost(host);
                listBean.setToken(token);

                // 添加fragment
                addPagerFragment(listBean);

            }

        });
    }



    // 添加fragment
    private void addPagerFragment(JsonBookItemCls.ListBean listBean) {

        infoTab.setTabMode(TabLayout.MODE_FIXED);
        infoTab.setTabTextColors(colorW5, colorW);
        infoTab.setSelectedTabIndicatorColor(colorW);
        List<Fragment> fragments = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        if (listBean.getHost() != null && !listBean.getHost().equals("")
                && listBean.getTitle() != null && !listBean.getTitle().equals("")) {
            strings.add("章节");
            bookChapterFragment = new BookChapterFragment(listBean, true, BookInfoActivity.this);
            fragments.add(bookChapterFragment);
        }
        strings.add("评论");
        fragments.add(new BookChapterFragment(listBean, false, BookInfoActivity.this));
        infoPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments, strings));
        infoTab.setupWithViewPager(infoPager);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        //加入书库
        if (item.getItemId() == R.id.menu_1) {
            showToast("1");
            return true;
        }

        //开始阅读
        if (item.getItemId() == R.id.menu_2) {
            startRead(item);
            return true;
        }

        //章节列表排序
        if (item.getItemId() == R.id.menu_3) {
            //showToast("3");
            if (bookChapterFragment != null && bookChapterFragment.isPositiveOrder()) {
                item.setTitle("章节列表正序");
                bookChapterFragment.setOrder(false);
            } else if (bookChapterFragment != null) {
                item.setTitle("章节列表到序");
                bookChapterFragment.setOrder(true);
            }
            if (infoPager.getCurrentItem() == 1)
                infoPager.setCurrentItem(0);
            return true;
        }
        //分享
        if (item.getItemId() == R.id.menu_4) {
            showToast("4");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //开始阅读
    private void startRead(MenuItem item) {
        //可以记录上一次的阅读地点
        if (intent == null)
            intent = new Intent(this, LookBookActivity.class);
        intent.putExtra(AppInfo.APP_SA_BOOK_SQL_INFO, f2BooksData);
        //#8078D4FF(intent,3);
        startActivity(intent);
    }


    @Override
    public void onClickChapter(View v, int position, JsonBookChapterCls.DataBean.ChaptersBean listBean) {

        Tools.showToast(this, "ID--->" + listBean.getId() + "\nNAME" + listBean.getName() + "\nURL--->" + listBean.getUrl() + "\nIsHost--->" + BookHost.isHost(this.listBean.getHost()));
        Log.i("test_t", listBean.getUrl());
        if (!BookHost.isHost(this.listBean.getHost())) {

            if (builder != null) builder.create();
            builder = new AlertDialog.Builder(this)
                    .setTitle("Host无收录")
                    .setMessage("目前没有收录此Host可能无法解析，或者无法观看，您可以跳转浏览器阅读。给你带来的不便敬请谅解，我们会尽快收录")
                    .setNegativeButton("尝试解析", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //尝试解析
                            startLookBook(false,position,listBean);
                        }
                    })
                    .setPositiveButton("浏览器阅读", (dialog, which) -> {
                        Tools.jumpURl(BookInfoActivity.this, listBean.getUrl());
                    });
            builder.show();
        } else {
            startLookBook(true,position,listBean);
        }

    }



    /**
     *  跳转到阅读
     * @param isOnHos 是否收录host
     * @param position 第几章
     * @param listBean list
     */
    private void startLookBook(boolean isOnHos, int position,JsonBookChapterCls.DataBean.ChaptersBean listBean ) {
        if (intent == null)
            intent = new Intent(this, LookBookActivity.class);

        if (f2BooksData == null)
            f2BooksData = new F2BooksData();
        f2BooksData.setKnotName(listBean.getName());
        f2BooksData.setKnotConnectUrl(listBean.getUrl());
        f2BooksData.setApiSize(position);
        f2BooksData.setApiPage(bookChapterFragment.getPage());
        f2BooksData.setApiOrder(bookChapterFragment.getOrder());
        f2BooksData = DaoTools.listBeanToF2BooksData(this.listBean, f2BooksData);

        //添加历史
        instance.addBookHistory(this.f2BooksData);
        //发布事件   刷新历史
        EventBus.getDefault().post(new DataSynEvent(2));
        intent.putExtra(AppInfo.APP_SA_IS_BOY, is_boy);
        intent.putExtra(AppInfo.APP_SA_BOOK_SQL_INFO, f2BooksData);
        //#8078D4FF(intent,3);
        startActivity(intent);
    }




    //点击评论item
    @Override
    public void onClickComment(View v, int position, JsonBookCommentsCls.DataBean.ListBean listBean) {
        Intent intent = new Intent(this, CommentsInfoActivity.class);
        intent.putExtra(AppInfo.APP_SA_USER_INFO, listBean);
        intent.putExtra(AppInfo.APP_SA_IS_BOY, is_boy);
        startActivity(intent);
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reType == 1 && reeType==1)
            reType = 3; // 刷新两个
        else if (reType == 1 && reeType ==0)
            reType = 1; //刷新书架
        else if (reType == 0 && reeType == 1)
            reType = 2; //刷星历史
        else if (reType == 0 && reeType == 0)
            reType = -1; //不刷星
        setResult(reType);
    }*/


    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == 3)
            reeType = 1;
    }*/
}