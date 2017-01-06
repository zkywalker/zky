package org.zky.zky.activities.splash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.zky.zky.MainActivity;
import org.zky.zky.R;
import org.zky.zky.widget.Indicator.TimeIndicatorController;
import org.zky.zky.widget.Indicator.TimeIndicatorControllerImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        FrameLayout indicator_container = (FrameLayout) findViewById(R.id.fl_indicator_container);
        TimeIndicatorController controller = new TimeIndicatorControllerImpl();
        indicator_container.addView(controller.newInstance(this));
        controller.initialize(5900);
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
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, iv, "ImageView");
        startActivity(new Intent(SplashActivity.this, MainActivity.class),options.toBundle());
//        finish();
    }
}
