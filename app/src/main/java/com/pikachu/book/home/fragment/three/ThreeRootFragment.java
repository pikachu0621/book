package com.pikachu.book.home.fragment.three;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.pikachu.book.R;
import com.pikachu.book.home.MainActivity;
import com.pikachu.book.home.fragment.three.activity.UserInfoActivity;
import com.pikachu.book.tools.base.BaseFragment;
import com.pikachu.book.tools.untli.AppInfo;


public class ThreeRootFragment extends BaseFragment {

    private final MainActivity mainActivity;
    private View inflate;
    private FragmentActivity activity;
    private TextView mainF3Text1;
    private TextView mainF3Text2;
    private LinearLayout mainF3Lin1;
    private LinearLayout mainF3Lin2;
    private LinearLayout mainF3Lin3;
    private LinearLayout mainF3Lin4;

    public ThreeRootFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_h_root3, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    @Override
    protected void initData() {

    }


    private void init() {
        mainF3Lin1.setOnClickListener(v -> mainActivity.setPager(1,0));
        mainF3Lin2.setOnClickListener(v -> mainActivity.setPager(1,1));
        mainF3Lin3.setOnClickListener(v -> {
            Intent intent = new Intent(activity, UserInfoActivity.class);
            intent.putExtra(AppInfo.APP_SA_IS_BOY,1);
            intent.putExtra(AppInfo.APP_SA_USER_INFO,"更多设置");
            startActivity(intent);
        });
        mainF3Lin4.setOnClickListener(v -> {
            Intent intent = new Intent(activity, UserInfoActivity.class);
            intent.putExtra(AppInfo.APP_SA_IS_BOY,2);
            intent.putExtra(AppInfo.APP_SA_USER_INFO,"关于软件");
            startActivity(intent);
        });
    }


    private void initView() {
        mainF3Text1 = inflate.findViewById(R.id.main_f3_text1);
        mainF3Text2 = inflate.findViewById(R.id.main_f3_text2);
        mainF3Lin1 = inflate.findViewById(R.id.main_f3_lin1);
        mainF3Lin2 = inflate.findViewById(R.id.main_f3_lin2);
        mainF3Lin3 = inflate.findViewById(R.id.main_f3_lin3);
        mainF3Lin4 = inflate.findViewById(R.id.main_f3_lin4);
    }
}