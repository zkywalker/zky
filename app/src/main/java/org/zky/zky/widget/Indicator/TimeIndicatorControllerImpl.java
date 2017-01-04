package org.zky.zky.widget.Indicator;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.zky.zky.R;

/**
 * Created by zkywalker on 2017/1/4.
 * package:org.zky.zky.widget.Indicator
 */

public class TimeIndicatorControllerImpl implements TimeIndicatorController {
    private TextView tv_skip;

    private ProgressBar pb;

    private float mills;

    private CountDownTimer timer;

    private OnCountDownListener listener;

    @Override
    public View newInstance(@NonNull Context context) {
        View view = View.inflate(context, R.layout.time_indicator, null);
        tv_skip = (TextView) view.findViewById(R.id.tv_skip);
        pb = (ProgressBar) view.findViewById(R.id.pb);
        return view;
    }

    @Override
    public void initialize(long countMillis) {
        pb.setMax((int) countMillis);
        if (timer==null){
            timer = new CountDownTimer(countMillis,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    pb.setProgress((int) millisUntilFinished);

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

    public void setOnCountDownListener(OnCountDownListener listener){
        this.listener = listener;
    }

    interface OnCountDownListener{
        void onTick();
        void onFinish();
    }
}
