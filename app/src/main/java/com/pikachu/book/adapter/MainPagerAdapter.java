// STOPSHIP: 2020/11/19 pikachu.org.cn
package com.pikachu.book.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.List;

//背  in..Time   d..Time
// c.addView(data.get(pos)); return data.get(pos)     c.remView((View)Object)
// addOn



public class MainPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    private final List<View> data;

    public MainPagerAdapter(List<View> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(data.get(position));
        return data.get(position);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }



}
