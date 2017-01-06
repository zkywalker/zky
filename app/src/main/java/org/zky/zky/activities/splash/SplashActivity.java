package org.zky.zky.activities.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.zky.zky.MainActivity;
import org.zky.zky.R;
import org.zky.zky.activities.introduction.DefaultIntroActivity;
import org.zky.zky.utils.GetRes;
import org.zky.zky.widget.Indicator.TimeIndicatorController;
import org.zky.zky.widget.Indicator.TimeIndicatorControllerImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    //防止计时器二次跳转
    private boolean flag = true;

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        check();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        FrameLayout indicator_container = (FrameLayout) findViewById(R.id.fl_indicator_container);
        TimeIndicatorController controller = new TimeIndicatorControllerImpl();
        indicator_container.addView(controller.newInstance(this));
        controller.initialize(2900);
        controller.start();
        controller.setOnCountDownListener(new TimeIndicatorControllerImpl.OnCountDownListener() {
            @Override
            public void onTick() {

            }

            @Override
            public void onFinish() {
                loadMainActivity();
            }
        });
    }

    public void skip(View view) {
        loadMainActivity();
    }

    public void loadMainActivity() {
        if (flag){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            flag = !flag;
            finish();
        }
    }

    public void check() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String key = GetRes.getString(R.string.key_intro);
        boolean showIntro = sp.getBoolean(key, true);
        if (showIntro) {
            //如果显示介绍页就跳转介绍页面，修改sp，只有第一次打开app才调介绍页
            sp.edit().putBoolean(key, false).apply();
            startActivity(new Intent(this, DefaultIntroActivity.class));
            flag = !flag;
            finish();
        } else if (!sp.getBoolean(GetRes.getString(R.string.key_splash), true)) {
            //不显示介绍页、闪屏页就直接跳到主页
            loadMainActivity();
        }

    }

}
