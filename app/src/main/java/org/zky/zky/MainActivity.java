package org.zky.zky;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.squareup.picasso.Picasso;

import org.zky.zky.activities.ActivitiesActivity;
import org.zky.zky.activities.splash.SplashActivity;
import org.zky.zky.utils.GetRes;
import org.zky.zky.utils.SplashImage;
import org.zky.zky.utils.ZhihuDailyService;
import org.zky.zky.widget.Indicator.IndicatorControllerImpl;
import org.zky.zky.widget.WidgetActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseThemeActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ZhihuDailyService service = retrofit.create(ZhihuDailyService.class);
        Observable<SplashImage> ob = service.getUrl("1080*1776");
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SplashImage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: "+e);
                    }

                    @Override
                    public void onNext(SplashImage splashImage) {
                        Log.i(TAG, "onNext: ");
                    }
                });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_widget) {
            startActivity(new Intent(this, WidgetActivity.class));
        } else if (id == R.id.nav_view) {

        } else if (id == R.id.nav_activity) {
            startActivity(new Intent(this, ActivitiesActivity.class));
        } else if (id == R.id.nav_sdk) {

        } else if (id == R.id.nav_share) {
            //            intent.setType("image/*");
            /*
             * 图片分享 it.setType("image/png"); 　//添加图片 File f = new
             * File(Environment.getExternalStorageDirectory()+"/name.png");
             *
             * Uri uri = Uri.fromFile(f); intent.putExtra(Intent.EXTRA_STREAM,
             * uri); 　
             */
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain"); //纯文本
            intent.putExtra(Intent.EXTRA_SUBJECT, GetRes.getString(R.string.share));
            intent.putExtra(Intent.EXTRA_TEXT, GetRes.getString(R.string.share_content));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(Intent.createChooser(intent, GetRes.getString(R.string.share)));

        } else if (id == R.id.nav_send) {

            try {

//                Intent.ACTION_SENDTO 无附件的发送
//                Intent.ACTION_SEND 带附件的发送
//                Intent.ACTION_SEND_MULTIPLE 带有多附件的发送
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + GetRes.getString(R.string.email)));
                intent.putExtra(Intent.EXTRA_SUBJECT, GetRes.getString(R.string.email_subject));
                startActivity(intent);

            } catch (ActivityNotFoundException e) {

                Snackbar.make(findViewById(R.id.content_main), GetRes.getString(R.string.no_email_app), Snackbar.LENGTH_LONG).setAction(GetRes.getString(R.string.i_know), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();

            }

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
