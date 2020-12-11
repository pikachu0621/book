package com.pikachu.book.home.fragment.three.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.pikachu.book.R;
import com.pikachu.book.book.info.BookInfoActivity;
import com.pikachu.book.tools.untli.AppInfo;
import com.pikachu.book.tools.untli.Tools;

public class AppInfoFragment extends Fragment {


    private View inflate;
    private LinearLayout fragmentAppinfoLin1;
    private LinearLayout fragmentAppinfoLin2;
    private FragmentActivity activity;

    public AppInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_app_info, container, false);
        activity = getActivity();
        initView();
        init();
        return inflate;
    }

    private void init() {
        fragmentAppinfoLin1.setOnClickListener(v -> {
            new AlertDialog.Builder(activity)
                    .setTitle("欢迎使用!")
                    .setMessage("1.本应用为网络小说阅读器\n" +
                            "2.APP内的小说均归网友上传，神马网站。\n" +
                            "3.项目只用于学习交流，毕业设计，已开源。不得用于任何商业用途，一切后果自负。")
                    .setPositiveButton("确定", null).show();

        });
        fragmentAppinfoLin2.setOnClickListener(v -> Tools.jumpURl(activity, "https://github.com/2825436553/book"));
    }

    private void initView() {
        fragmentAppinfoLin1 = inflate.findViewById(R.id.fragment_appinfo_lin1);
        fragmentAppinfoLin2 = inflate.findViewById(R.id.fragment_appinfo_lin2);
    }
}