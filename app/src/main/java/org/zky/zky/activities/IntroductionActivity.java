package org.zky.zky.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import org.zky.zky.R;

import java.util.List;

public abstract class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vp_slide;

    private PagerAdapter adapter;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //请求窗口特色没有actionbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_introduction);
        initView();
    }

    private   void initView(){
        final Button btn_skip = (Button) findViewById(R.id.btn_skip);
        final Button btn_done = (Button) findViewById(R.id.btn_done);
        final ImageButton btn_next = (ImageButton) findViewById(R.id.ib_next);
        final FrameLayout container = (FrameLayout) findViewById(R.id.indicator_container);

        btn_skip.setOnClickListener(this);
        btn_done.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        vp_slide = (ViewPager) findViewById(R.id.vp_slide);
        adapter = new PagerAdapter(this.getSupportFragmentManager(),fragments);
        vp_slide.setAdapter(adapter);
        vp_slide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    };

    abstract void init(Bundle saveInstanceState);
    abstract void onSkip();
    abstract void onDone();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_skip:
                onSkip();
                break;
            case R.id.btn_done:
                onDone();
                break;
            case R.id.ib_next:
                //圆滑切换
                vp_slide.setCurrentItem(vp_slide.getCurrentItem()+1,true);
                break;
        }
    }
}
