package org.zky.zky.activities;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zkywalker on 2017/1/6.
 * package:org.zky.zky.activities
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;

    public ViewPagerAdapter(List<View> views){
        this.views = views;
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (String) views.get(position).getTag();
    }

    public List<View> getViews(){
        return views;
    }
}
