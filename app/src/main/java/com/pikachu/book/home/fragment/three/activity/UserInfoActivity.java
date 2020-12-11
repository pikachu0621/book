package com.pikachu.book.home.fragment.three.activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.pikachu.book.R;
import com.pikachu.book.tools.base.BaseActivity;
import com.pikachu.book.tools.untli.AppInfo;

public class UserInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);


        setHead(true, getStringExtra(AppInfo.APP_SA_USER_INFO), null, this::finish);
        int intExtra = getIntExtra(AppInfo.APP_SA_IS_BOY, 0);
        Fragment fragment =  new AppInfoFragment();
        if (intExtra == 1)
            fragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.userInfo,fragment).commit();
    }
}