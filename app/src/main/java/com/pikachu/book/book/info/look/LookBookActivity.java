package com.pikachu.book.book.info.look;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.pikachu.book.R;
import com.pikachu.book.tools.base.BaseActivity;
import com.pikachu.book.tools.untli.BookHost;
import com.pikachu.book.tools.untli.Tools;
import com.pikachu.book.tools.url.LoadUrl;

public class LookBookActivity extends BaseActivity {

    private TextView lookText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_book);
        initView();
        init();

    }


    private void init() {

        String stringExtra = getStringExtra("URL");
        String host = getStringExtra("HOST");
        String stringExtra2 = getStringExtra("NAME");


        new LoadUrl(this, stringExtra,stringExtra.contains("630shu.net")?"gbk":null, new LoadUrl.OnCall() {
            @Override
            public void error(Exception e) {

            }

            @Override
            public void finish(String str) {

                String str1 = Tools.cutStr(str, "<body", "</body>");
                /*String str2 = Tools.cutStr(str1, stringExtra2,"上一页");*/
                lookText1.setText(Html.fromHtml(str1));
            }
        });

    }


    private void initView() {
        lookText1 = findViewById(R.id.look_text1);
    }
}