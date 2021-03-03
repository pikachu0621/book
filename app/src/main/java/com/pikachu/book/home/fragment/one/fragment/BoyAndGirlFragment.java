package com.pikachu.book.home.fragment.one.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.pikachu.book.R;
import com.pikachu.book.tools.adapter.PagerAdapter;
import com.pikachu.book.cls.json.JsonTabCls;
import com.pikachu.book.home.fragment.one.fragment.fragment.TabFragment;
import com.pikachu.book.tools.untli.Tools;

import java.util.ArrayList;
import java.util.List;


public class BoyAndGirlFragment extends Fragment {

    private boolean isBoy;
    private List<JsonTabCls.Bean> beans;
    private View inflate;
    private LinearLayout lin;
    private TabLayout tab;
    private ViewPager pager;
    private FragmentActivity activity;
    private View view;




    public static  BoyAndGirlFragment newInstance(List<JsonTabCls.Bean> beans, boolean isBoy) {
        /* Bundle args = new Bundle();
        args.put(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        return new  BoyAndGirlFragment(beans ,  isBoy);
    }


    public BoyAndGirlFragment() {

    }



    public BoyAndGirlFragment(List<JsonTabCls.Bean> beans, boolean isBoy) {
        this.isBoy = isBoy;
        this.beans = beans;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_h_root1_boy, container, false);
        activity = getActivity();
        findView();
        init();
        return inflate;
    }

    private void init() {
        lin.setBackgroundColor(activity.getResources().getColor(isBoy ? R.color.purple_700 : R.color.purge_800));

        Tools.setNonHigh(activity, view);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabTextColors(getResources().getColor(R.color.white_50), getResources().getColor(R.color.white));
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        for (JsonTabCls.Bean bean : beans) {
            strings.add(bean.getTag());
            fragments.add(new TabFragment(bean.getTag(),isBoy));
        }

        pager.setAdapter(new PagerAdapter(getChildFragmentManager(), fragments, strings));
        pager.getAdapter().notifyDataSetChanged();
        tab.setupWithViewPager(pager);

    }


    private void findView() {
        lin = inflate.findViewById(R.id.fragment_pager_list_linear);
        tab = inflate.findViewById(R.id.fragment_pager_list_tab);
        pager = inflate.findViewById(R.id.fragment_pager_list_pager);
        view = inflate.findViewById(R.id.fragment_pager_list_view);

    }










}