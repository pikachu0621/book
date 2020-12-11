/**
 * 主题完成
 * 字体完成
 * 亮度完成
 * <p>
 * <p>
 * <p>
 * 不足
 * 章节列表要能上加载和下加载 （这里没实现）
 * 小说也是 （能上下加载）
 *
 * 重新写适配器
 *
 */

package com.pikachu.book.book.info.look;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pikachu.book.R;
import com.pikachu.book.book.info.BookChapterFragment;
import com.pikachu.book.cls.DataSynEvent;
import com.pikachu.book.cls.ThemeInfo;
import com.pikachu.book.cls.json.JsonBookChapterCls;
import com.pikachu.book.cls.json.JsonBookCommentsCls;
import com.pikachu.book.cls.sql.F2BooksData;
import com.pikachu.book.tools.base.BaseActivity;
import com.pikachu.book.tools.dao.DaoTools;
import com.pikachu.book.tools.state.PKStatusBarTools;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.BookHost;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.url.LoadUrl;
import com.pikachu.book.tools.view.QMUIRadiusImageView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class LookBookActivity extends BaseActivity implements BookChapterFragment.OnClickFragment {

    private TextView lookText1;
    private RecyclerView lookRecycler;
    private View lookView;
    private Toolbar lookToolbar;
    private LinearLayout lookZiti;
    private ImageView lookImg1;
    private SeekBar lookSeekBar1;
    private ImageView lookImg2;
    private ImageView lookImg3;
    private SeekBar lookSeekBar2;
    private ImageView lookImg4;
    private LinearLayout lookZhiti;
    private LinearLayout lookLin5;
    private LinearLayout lookLin6;
    private LinearLayout lookLin7;
    private LinearLayout lookLin8;
    private LinearLayout lookLin1;
    private LinearLayout lookLin2;
    private LinearLayout lookLin3;
    private LinearLayout lookLin4;
    private DrawerLayout lookDrawer;
    private QMUIRadiusImageView lookQmui1;
    private TextView lookText2;
    private TextView lookText4;
    private LinearLayout lookDibu,lookLin11;
    private LinearLayout lookDrawerBg;
    private View lookView1;
    private LinearLayout lookLin9;
    private LinearLayout lookLin10, lookOnClick;
    private RelativeLayout lookOnClick1,lookOnClick2;
    private int w1;
    private boolean is_boy;
    private F2BooksData bookInfo;
    private BookChapterFragment bookChapterFragment;
    private ThemeInfo[] themeInfos = {
            new ThemeInfo(0xffdbdbdb, 0xA0000000, 0x10000000, 0xA0000000, 0xff787878, 0xff787878, 0xA0000000, 0xc0000000),
            new ThemeInfo(0xffFFD6D6, 0xA0000000, 0x10000000, 0xA0000000, 0xff787878, 0xff787878, 0xA0000000, 0xc0000000),
            new ThemeInfo(0xffD6FFDD, 0xA0000000, 0x10000000, 0xA0000000, 0xff787878, 0xff787878, 0xA0000000, 0xc0000000),
            new ThemeInfo(0xff2B2B2B, 0xA0FFFFFF, 0x10FFFFFF, 0xFFFFFFFF, 0x80FFFFFF, 0x80FFFFFF, 0xFFFFFFFF, 0xc0FFFFFF),
    };
    private LookBookRecyclerAdapter lookBookRecyclerAdapter;
    private DaoTools instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_book);
        PKStatusBarTools.with(this).noToNON().init();
        initView();
        init();
    }


    private void init() {
        w1 = getResources().getColor(R.color.white);
        lookToolbar.setTitleTextColor(w1);
        bookInfo = (F2BooksData) getIntent().getSerializableExtra(AppInfo.APP_SA_BOOK_SQL_INFO);
        is_boy = getIntent().getBooleanExtra(AppInfo.APP_SA_IS_BOY, true);
        lookToolbar.setTitle(bookInfo.getKnotName());
        setSupportActionBar(lookToolbar);
        Tools.setNonHigh(this, lookView);
        Tools.setNonHigh(this, lookView1);
        setHead(true, null, null, this::finish);
        instance = DaoTools.getInstance(this);
        //加载小说内容
        loadContent();
        //打开菜单
        openBrightness();
        //调整亮度/字体
        steLig();
        //主题设置
        setThemeClick();
        //加载列表
        loadList();
        //倒序
        loadDao();
        //菜单呼出
        change();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void change() {


        lookOnClick.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                 setAnimator2(lookLin11,lookDibu,lookLin11.getVisibility() == View.GONE);
            return false;
        });

        lookLin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("添加成功");
                instance.addBookFrame(bookInfo);
                //发布事件   刷新书架
                EventBus.getDefault().post(new DataSynEvent(1));
                EventBus.getDefault().post(new DataSynEvent(2));

            }
        });




    }

    //加载小说内容
    @SuppressLint("ClickableViewAccessibility")
    private void loadContent() {

       /* lookRecycler.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    *//*LookBookActivity.this.showToast("响应点击1");*//*
                    Log.i("test_t","按下事件");
                    break;
                case MotionEvent.ACTION_UP:
                   *//* LookBookActivity.this.showToast("响应点击2");*//*
                    Log.i("test_t","抬起事件");
                    break;
                case MotionEvent.ACTION_MOVE:
                  *//*  LookBookActivity.this.showToast("响应点击3");*//*
                    Log.i("test_t","移动事件");
                    break;
            }
            return false;

        });*/

        List<String> strings = new ArrayList<>();
        lookBookRecyclerAdapter = new LookBookRecyclerAdapter(strings);
        lookRecycler.setAdapter(lookBookRecyclerAdapter);
        lookRecycler.setLayoutManager(new LinearLayoutManager(this));

        new LoadUrl(this, bookInfo.getKnotConnectUrl(), BookHost.getHostCoding(bookInfo.getApiHost()), new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {

            }

            @Override
            public void finish(String str) {
                String str1 = BookHost.hostStr(bookInfo.getApiHost(), str);
                if (str1 == null) str1 = "";
                strings.add(str1);
                lookBookRecyclerAdapter.notifyDataSetChanged();
            }
        });


    }


    //倒序
    private void loadDao() {

        if (bookInfo.getApiOrder() == 1) {
            lookText4.setText("到序");
        } else {
            lookText4.setText("正序");
        }

        lookText4.setOnClickListener(v -> {
            //showToast("3");
            if (bookChapterFragment != null && bookChapterFragment.isPositiveOrder()) {
                lookText4.setText("正序");
                bookChapterFragment.setOrder(false);
            } else if (bookChapterFragment != null) {
                lookText4.setText("到序");
                bookChapterFragment.setOrder(true);
            }
        });

    }

    //加载列表
    private void loadList() {
        bookChapterFragment = new BookChapterFragment(bookInfo, true, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.look_lin10, bookChapterFragment).commit();
        Glide.with(this).load(bookInfo.getKnotImageUrl()).into(lookQmui1);
        lookText1.setText(bookInfo.getApiTitle());
        lookText2.setText(bookInfo.getApiAuthor());


    }


    //设置主题
    private void setTheme(ThemeInfo theme) {
        lookRecycler.setBackgroundColor(theme.getBgColor());
        lookView1.setBackgroundColor(theme.getHandColor());
        lookLin9.setBackgroundColor(theme.getHandColor());
        lookDrawerBg.setBackgroundColor(theme.getBgColor());

        lookText1.setTextColor(theme.getHandTitleTextColor());
        lookText2.setTextColor(theme.getHandZTextColor());
        lookText4.setTextColor(theme.getHandDTextColor());
    }

    //设置主题点击
    private void setThemeClick() {
        int bootTheme = bookInfo.getBootTheme();
        if (bootTheme == -1) {
            //判断是否晚上 是 设置暗主题
            if(Tools.isNight())
                setTheme(themeInfos[3]);
            else {
                if (is_boy) setTheme(themeInfos[0]);
                else setTheme(themeInfos[1]);
            }
        } else {
            setTheme(themeInfos[bootTheme]);
        }
        lookLin5.setOnClickListener(v -> setTheme(themeInfos[0]));
        lookLin6.setOnClickListener(v -> setTheme(themeInfos[1]));
        lookLin7.setOnClickListener(v -> setTheme(themeInfos[2]));
        lookLin8.setOnClickListener(v -> setTheme(themeInfos[3]));
    }


    //实力控件
    private void initView() {
        lookRecycler = findViewById(R.id.look_recycler);

        lookView = findViewById(R.id.look_view);
        lookView1 = findViewById(R.id.look_view1);
        lookToolbar = findViewById(R.id.look_toolbar);
        lookLin11 = findViewById(R.id.look_lin11);


        //字体/亮度
        lookZiti = findViewById(R.id.look_ziti);
        //亮度
        lookImg1 = findViewById(R.id.look_img1);
        lookSeekBar1 = findViewById(R.id.look_seekBar1);
        lookImg2 = findViewById(R.id.look_img2);
        //字体
        lookImg3 = findViewById(R.id.look_img3);
        lookSeekBar2 = findViewById(R.id.look_seekBar2);
        lookImg4 = findViewById(R.id.look_img4);


        //主题
        lookZhiti = findViewById(R.id.look_zhiti);
        lookLin5 = findViewById(R.id.look_lin5);
        lookLin6 = findViewById(R.id.look_lin6);
        lookLin7 = findViewById(R.id.look_lin7);
        lookLin8 = findViewById(R.id.look_lin8);


        //bottom
        lookLin1 = findViewById(R.id.look_lin1);
        lookLin2 = findViewById(R.id.look_lin2);
        lookLin3 = findViewById(R.id.look_lin3);
        lookLin4 = findViewById(R.id.look_lin4);

        //侧滑
        lookDrawer = findViewById(R.id.look_drawer);
        lookQmui1 = findViewById(R.id.look_qmui1);
        lookText1 = findViewById(R.id.look_text1);
        lookText2 = findViewById(R.id.look_text2);
        lookText4 = findViewById(R.id.look_text4);//倒序
        lookDibu = findViewById(R.id.look_dibu);
        lookDrawerBg = findViewById(R.id.look_drawer_bg);//设置背景
        lookLin9 = findViewById(R.id.look_lin9);
        lookLin10 = findViewById(R.id.look_lin10);
        lookOnClick = findViewById(R.id.look_onClick);
        lookOnClick1 = findViewById(R.id.look_onClick1);
        lookOnClick2 = findViewById(R.id.look_onClick2);
    }


    //显示  字体/亮度
    @SuppressLint("ClickableViewAccessibility")
    private void openBrightness() {
        lookLin1.setOnClickListener(v -> {
            //侧滑
            lookDrawer.openDrawer(GravityCompat.START);
        });

        lookLin2.setOnClickListener(v -> {
            if (lookZhiti.getVisibility() == View.VISIBLE) {
                setAnimator(lookZhiti,lookOnClick2, false);
            }
            setAnimator(lookZiti,lookOnClick1, lookZiti.getVisibility() == View.GONE);
        });
        lookLin3.setOnClickListener(v -> {
            if (lookZiti.getVisibility() == View.VISIBLE) {
                setAnimator(lookZiti,lookOnClick1, false);
            }
            setAnimator(lookZhiti,lookOnClick2, lookZhiti.getVisibility() == View.GONE);
        });


        lookOnClick2.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
                setAnimator(lookZhiti,v, false);
            return false;

        });
        lookOnClick1.setOnTouchListener((v, event) -> {
            if (event.getAction() ==  MotionEvent.ACTION_DOWN)
                setAnimator(lookZiti,v, false);
            return false;
        });



    }


    //设置动画
    private void setAnimator(View v,View v1, boolean isStart) {

        float y = 0f, y1 = 1f;
        float ty = 100f, ty1 = 0f;

        if (!isStart) {
            y = 1f;
            y1 = 0f;
            ty = 0f;
            ty1 = 100f;
        }

        PropertyValuesHolder alphaProper = PropertyValuesHolder.ofFloat("alpha", y, y1);
        PropertyValuesHolder scaleYProper = PropertyValuesHolder.ofFloat("translationY", ty, ty1);
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, alphaProper, scaleYProper);
        animator.setDuration(200);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                if (isStart) {
                    v1.setVisibility(View.VISIBLE);
                    v.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if (!isStart){
                    v.setVisibility(View.GONE);
                    v1.setVisibility(View.GONE);
                }
            }
        });
        animator.start();
    }

    //设置动画2
    private void setAnimator2(View v,View v1, boolean isShow) {
        float y = 0f, y1 = 1f;
        float ty2 = -100f, ty3 = 0f;
        float ty = 100f, ty1 = 0f;
        if (!isShow) {
            y = 1f;
            y1 = 0f;
            ty = 0f;
            ty1 = 100f;
            ty2 = 0f;
            ty3 = -100f;
        }

        PropertyValuesHolder alphaProper1 = PropertyValuesHolder.ofFloat("alpha", y, y1);
        PropertyValuesHolder scaleYProper1 = PropertyValuesHolder.ofFloat("translationY", ty2, ty3);
        ValueAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(v, alphaProper1, scaleYProper1);
        animator1.setDuration(200);

        PropertyValuesHolder alphaProper = PropertyValuesHolder.ofFloat("alpha", y, y1);
        PropertyValuesHolder scaleYProper = PropertyValuesHolder.ofFloat("translationY", ty, ty1);
        ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v1, alphaProper, scaleYProper);
        animator.setDuration(200);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                if (isShow) {
                    /*v3.setVisibility(View.VISIBLE);*/
                    v1.setVisibility(View.VISIBLE);
                    v.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                if (!isShow){
                    v.setVisibility(View.GONE);
                    v1.setVisibility(View.GONE);
                    /*v3.setVisibility(View.GONE);*/
                }
            }
        });
        animator.start();
        animator1.start();
    }



    //设置亮度/字体
    private void steLig() {

        int brightnessMax = Tools.getBrightnessMax();
        lookImg1.setOnClickListener(v -> {
            //减亮度
            int progress = (int) (lookSeekBar1.getProgress() - brightnessMax * 0.1F);
            if (progress <= 0)
                progress = 0;
            lookSeekBar1.setProgress(progress);
            Tools.setScreenBrightness(LookBookActivity.this, progress);
        });
        lookSeekBar1.setMax(brightnessMax);
        int bookBrightness = bookInfo.getBookBrightness();
        if (bookBrightness == -1) {
            bookBrightness = Tools.getScreenBrightness(this);
        }
        lookSeekBar1.setProgress(bookBrightness);
        lookSeekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Tools.setScreenBrightness(LookBookActivity.this, seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lookImg2.setOnClickListener(v -> {
            //加亮度
            int progress = (int) (lookSeekBar1.getProgress() + brightnessMax * 0.1F);
            if (progress >= brightnessMax)
                progress = brightnessMax;
            lookSeekBar1.setProgress(progress);
            Tools.setScreenBrightness(LookBookActivity.this, progress);
        });




        lookImg3.setOnClickListener(v -> {
            //减字体
            int progress = lookSeekBar2.getProgress() - 1;
            if (progress < 0)
                progress = 0;
            if (lookBookRecyclerAdapter != null ){
                lookSeekBar2.setProgress(progress);
                lookBookRecyclerAdapter.setTextSize(progress+10);
            }

        });
        lookSeekBar2.setMax(20);
        int textSize = bookInfo.getBookFontSize();
        if (lookBookRecyclerAdapter != null ){
            lookBookRecyclerAdapter.setTextSize(textSize);
            lookSeekBar2.setProgress(textSize-10);
        }

        lookSeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (lookBookRecyclerAdapter != null )
                    lookBookRecyclerAdapter.setTextSize(seekBar.getProgress()+10);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lookImg4.setOnClickListener(v -> {
            //加字体
            int progress = lookSeekBar2.getProgress() + 1;
            if (progress > 20)
                progress = 20;
            if (lookBookRecyclerAdapter != null ){
                lookSeekBar2.setProgress(progress);
                lookBookRecyclerAdapter.setTextSize(progress+10);
            }
        });









    }


    //列表点击
    @Override
    public void onClickChapter(View v, int position, JsonBookChapterCls.DataBean.ChaptersBean listBean) {
    }



    //不用
    @Override
    public void onClickComment(View v, int position, JsonBookCommentsCls.DataBean.ListBean listBean) { }


}