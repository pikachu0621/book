package com.pikachu.book.book.info.comments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pikachu.book.R;
import com.pikachu.book.tools.base.BaseActivity;

public class CommentsInfoActivity extends BaseActivity implements BaseActivity.OnBackEvent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_info);
        setHead(true,"",null,this);
    }


    @Override
    public void backEvent() {
        finish();
    }
}