package com.pikachu.book.frament.main_one;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.pikachu.book.R;
import com.pikachu.book.tools.PKStatusBarTools;


public class PagerMainOneFragment extends Fragment {

    private final String url;
    private final boolean isBoy;
    private View inflate;
    private LinearLayout lin;
    private TabLayout tab;
    private ViewPager pager;
    private FragmentActivity activity;
    private View view;

    public PagerMainOneFragment(String url,boolean isBoy) {
        // Required empty public constructor
        this.isBoy = isBoy;
        this.url = url;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_pager_list, container, false);
        activity = getActivity();
        findView();
        init();
        return inflate;
    }

    private void init() {
        /*LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height = PKStatusBarTools.getSTSHeight(activity);*/
        lin.setBackgroundColor(activity.getResources().getColor(isBoy?R.color.purple_700:R.color.purge_800));


    }

    private void findView() {
        lin = inflate.findViewById(R.id.fragment_pager_list_linear);
        tab = inflate.findViewById(R.id.fragment_pager_list_tab);
        pager = inflate.findViewById(R.id.fragment_pager_list_pager);
        view = inflate.findViewById(R.id.fragment_pager_list_view);

    }
}