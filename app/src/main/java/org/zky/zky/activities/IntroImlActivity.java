package org.zky.zky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.zky.zky.MainActivity;
import org.zky.zky.R;

/**
 * Created by zkywalker on 2017/1/3.
 * package:org.zky.zky.activities
 */

public class IntroImlActivity extends IntroductionActivity {
    @Override
    void init(Bundle saveInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.introduction_1));
        addSlide(SampleSlide.newInstance(R.layout.introduction_2));
        addSlide(SampleSlide.newInstance(R.layout.introduction_3));
        addSlide(SampleSlide.newInstance(R.layout.introduction_4));
    }

    @Override
    void onSkip() {
    loadMainActivity();
    }

    @Override
    void onDone() {
    loadMainActivity();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
