package com.pikachu.book.book.info;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.pikachu.book.R;
import com.pikachu.book.cls.json.JsonBookItemCls;
import com.pikachu.book.tools.adapter.PagerAdapter;
import com.pikachu.book.tools.base.BaseActivity;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.url.LoadUrl;
import com.pikachu.book.tools.view.ScoreView;

import java.util.ArrayList;
import java.util.List;

public class BookInfoActivity extends BaseActivity {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        initView();
        init();

    }


    private void init() {
        //反序列化对象
        listBean = (JsonBookItemCls.ListBean) getIntent().getSerializableExtra("BOOK_OBJECT");
        is_boy = getIntent().getBooleanExtra("IS_BOY", true);
        setTheme(is_boy, infoAppbar);
        colorW = getResources().getColor(R.color.white);
        colorW5 = getResources().getColor(R.color.white_50);
        //头部信息
        setBookInfo();
        //添加章节。评论
        addPager();
    }

    private void addPager() {

        infoTab.setTabMode(TabLayout.MODE_FIXED);
        infoTab.setTabTextColors(colorW5, colorW);
        infoTab.setSelectedTabIndicatorColor(colorW);


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

                // 添加fragment
                List<Fragment> fragments = new ArrayList<>();
                List<String> strings = new ArrayList<>();
                if (str1 != null && !str1.equals("")
                        && host != null && !host.equals("")
                        && token != null && !token.equals("")) {
                    strings.add("章节");
                    fragments.add(new BookChapterFragment(listBean, host, token,is_boy));
                }
                strings.add("评论");
                fragments.add(new BookChapterFragment(listBean,is_boy));
                infoPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments, strings));
                infoTab.setupWithViewPager(infoPager);

            }
        });

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
        if (item.getItemId() == R.id.menu_1) {
            showToast("1");
            return true;
        }
        if (item.getItemId() == R.id.menu_2) {
            showToast("2");
            return true;
        }
        if (item.getItemId() == R.id.menu_3) {
            showToast("3");
            return true;
        }
        return super.onOptionsItemSelected(item);
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


    private void initView() {
        infoAppbar = findViewById(R.id.info_appbar);
        infoToolbar = findViewById(R.id.info_toolbar);
        infoImage1 = findViewById(R.id.info_image1);
        infoText1 = findViewById(R.id.info_text1);
        infoScore1 = findViewById(R.id.info_score1);
        infoText2 = findViewById(R.id.info_text2);
        infoText3 = findViewById(R.id.info_text3);
        //infoText4 = findViewById(R.id.info_text4);
        infoTab = findViewById(R.id.info_tab);
        infoPager = findViewById(R.id.info_pager);
    }
}