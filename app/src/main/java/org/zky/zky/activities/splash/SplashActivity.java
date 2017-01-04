package org.zky.zky.activities.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import org.zky.zky.MainActivity;
import org.zky.zky.R;
import org.zky.zky.widget.Indicator.TimeIndicatorController;
import org.zky.zky.widget.Indicator.TimeIndicatorControllerImpl;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

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
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
    }
}
