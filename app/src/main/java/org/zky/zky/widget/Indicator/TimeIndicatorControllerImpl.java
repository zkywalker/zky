package org.zky.zky.widget.Indicator;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.zky.zky.R;

/**
 * Created by zkywalker on 2017/1/4.
 * package:org.zky.zky.widget.Indicator
 */

public class TimeIndicatorControllerImpl implements TimeIndicatorController {
    private static final String TAG = "TimeIndicatorController";
    private TextView tv_skip;

    private ProgressBar pb;

    private CountDownTimer timer;

    private OnCountDownListener listener;

    private long millis;

    @Override
    public View newInstance(@NonNull Context context) {
        View view = View.inflate(context, R.layout.time_indicator, null);
        tv_skip = (TextView) view.findViewById(R.id.tv_skip);
        pb = (ProgressBar) view.findViewById(R.id.pb);
        return view;
    }

    @Override
    public void initialize(long countMillis) {
        millis = countMillis;
//        pb.setMax((int) countMillis);
        if (timer==null){
            timer = new CountDownTimer(countMillis,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    pb.setProgress((int) ((millis -millisUntilFinished)/millis*100));
                    Log.e(TAG, "onTick: "+millisUntilFinished);
                    if (listener!=null)
                        listener.onTick();
                }

                @Override
                public void onFinish() {
                    if (listener!=null)
                        listener.onFinish();
                }
            };
        }
    }

    @Override
    public void start(){
        if (timer!=null)
            timer.start();
    }

    @Override
    public void setOnCountDownListener(OnCountDownListener listener){
        this.listener = listener;
    }

    public interface OnCountDownListener{
        void onTick();
        void onFinish();
    }
}
