package com.pikachu.book.book.search;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentManager;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.pikachu.book.R;
import com.pikachu.book.home.fragment.one.fragment.fragment.TabFragment;
import com.pikachu.book.tools.base.BaseActivity;
import com.pikachu.book.tools.untli.Tools;

public class SearchActivity extends BaseActivity {


    private boolean is_boy;
    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
    }

    private void addActionBarView() {


        //获取是否男生
        is_boy = getIntent().getBooleanExtra("IS_BOY", true);

        int mActionBarOptions = getSupportActionBar().getDisplayOptions();
        // 设置DisplayOptions，显示ActionBar自定义的View
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM | mActionBarOptions | ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setCustomView(R.layout.ui_m_search);
        setHead(true, null, null, this::finish);


        setTheme(is_boy);

        supportFragmentManager = getSupportFragmentManager();

        //收索
        EditText editText = getSupportActionBar().getCustomView().findViewById(R.id.s_edit);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String s = editText.getText().toString();
                if (s.equals("")) {
                    Tools.showToast(SearchActivity.this, "不能为空");
                    return false;
                }
                //载入 fragment
                TabFragment tabFragment = new TabFragment(s, is_boy);
                supportFragmentManager.beginTransaction().replace(R.id.s_fragment, tabFragment).commit();
            }

            return false;
        });


    }

    private void init() {
        addActionBarView();
    }
}