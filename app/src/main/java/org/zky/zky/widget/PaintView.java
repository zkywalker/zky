package org.zky.zky.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * Created by zhangkun on 2016/12/22.
 */

public class PaintView extends View {
    private Paint mPaint;

    public PaintView(Context context) {
        this(context,null);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        mPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(100,100,100,300,mPaint);

        Paint p1 = new Paint();
        p1.setAntiAlias(true);
        p1.setStyle(Paint.Style.FILL);
        p1.setStrokeWidth(50);
        p1.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(200,100,200,200,p1);

        Paint p2 = new Paint();
        p2.setAntiAlias(true);
        p2.setStyle(Paint.Style.STROKE);
        p2.setColor(Color.RED);
        p2.setStrokeWidth(50);
        p2.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(200,200,200,300,p2);
    }
}
