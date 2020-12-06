package com.pikachu.book.book.info.comments;

/**
 *
 * 评论详情   界面      没完成
 *
 */


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pikachu.book.R;
import com.pikachu.book.cls.json.JsonBookCommentsCls;
import com.pikachu.book.tools.base.BaseActivity;

public class CommentsInfoActivity extends BaseActivity implements BaseActivity.OnBackEvent {

    private JsonBookCommentsCls.DataBean.ListBean listBean;
    private boolean is_boy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_info);


        initBar();


    }

    private void initBar() {
        //反序列化对象
        listBean = (JsonBookCommentsCls.DataBean.ListBean) getIntent().getSerializableExtra("USER_INFO");
        is_boy = getIntent().getBooleanExtra("IS_BOY", true);
        setTheme(is_boy);
        setHead(true,listBean.getSm_name(),null,this);
    }


    @Override
    public void backEvent() {
        finish();
    }
}