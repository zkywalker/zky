package org.zky.zky.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RotateDrawable;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import org.zky.zky.BaseThemeActivity;
import org.zky.zky.R;
import org.zky.zky.activities.introduction.DefaultIntroActivity;
import org.zky.zky.activities.introduction.IntroductionActivity;
import org.zky.zky.activities.splash.SplashActivity;
import org.zky.zky.utils.GetRes;
import org.zky.zky.utils.PreferenceUtils;

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

    int level = 1000;

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
        View other =View.inflate(this,R.layout.view_pager_other,null);
        other.setTag(GetRes.getString(R.string.other));
        List<View> list = new ArrayList<>();
        list.add(intro);
        list.add(splash);
        list.add(other);
        viewPager.setAdapter(new ViewPagerAdapter(list));
//        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < list.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText((String) list.get(i).getTag());
            tab.setTag(i);
            tabLayout.addTab(tab);
        }


        TabLayout.TabLayoutOnPageChangeListener changeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        viewPager.addOnPageChangeListener(changeListener);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem((Integer) tab.getTag());

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
        startActivity(new Intent(this, DefaultIntroActivity.class));
    }


    /**
     * 参考：
     * https://www.youtube.com/?gl=HK&tab=w1
     * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0914/3449.html
     * https://github.com/GoogleChrome/custom-tabs-client.git
     *
     */
    private static final String EXTRA_CUSTOM_TABS_SESSION = "android.support.customtabs.extra.SESSION";
    private static final String EXTRA_CUSTOM_TABS_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    public void chrome(View view) {
        String url = "http://zhangkun.site";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Bundle bundle = new Bundle();
        bundle.putBinder(EXTRA_CUSTOM_TABS_SESSION,null);
        intent.putExtras(bundle);
        intent.putExtra(EXTRA_CUSTOM_TABS_TOOLBAR_COLOR,getResources().getColor(R.color.colorPrimary));
        startActivity(intent);

    }

    /**
     * https://material.io/guidelines/patterns/launch-screens.html
     * 新的md指南里添加了launch screens章节，即splash screen
     */
    public void splash(View view) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putBoolean(GetRes.getString(R.string.key_splash), true)
                .putBoolean(GetRes.getString(R.string.key_intro),false)
                .apply();
        startActivity(new Intent(this, SplashActivity.class));
    }
}
