package org.zky.zky.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import org.zky.zky.BaseThemeActivity;
import org.zky.zky.R;
import org.zky.zky.utils.GetRes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivitiesActivity extends BaseThemeActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        toolbar.setTitle(GetRes.getString(R.string.activity_fragment));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View intro = View.inflate(this, R.layout.view_pager_intro, null);
        intro.setTag(GetRes.getString(R.string.pref_title_intro));
        View splash = View.inflate(this, R.layout.view_pager_splash, null);
        splash.setTag(GetRes.getString(R.string.pref_title_splash));
        List<View> list = new ArrayList<>();
        list.add(intro);
        list.add(splash);
        viewPager.setAdapter(new ViewPagerAdapter(list));
//        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("1");
        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText("2");
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabLayout.TabLayoutOnPageChangeListener changeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        viewPager.addOnPageChangeListener(changeListener);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void introduction(View view){

    }
}
