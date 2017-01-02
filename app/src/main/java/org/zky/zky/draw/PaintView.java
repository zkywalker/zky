package org.zky.zky.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
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
        canvas.drawLine(500,500,500,800,mPaint);

        Paint p1 = new Paint();
        p1.setColor(Color.GRAY);
        p1.setAntiAlias(true);
        p1.setStyle(Paint.Style.FILL);
        p1.setStrokeWidth(50);
        p1.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(600,500,600,800,p1);

        Paint p2 = new Paint();
        p2.setAntiAlias(true);
        p2.setStyle(Paint.Style.FILL);
        p2.setColor(Color.RED);
        p2.setStrokeWidth(2);
        p2.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawLine(200,200,200,300,p2);
        RectF rect = new RectF(0 , 0, 400, 400) ;
        canvas.drawArc(rect,0,180,true,p1);
        canvas.drawArc(rect,45,90,false,p2);

        Paint p3 = new Paint();
        p3.setAntiAlias(true);
        p3.setStyle(Paint.Style.FILL);
        p3.setStrokeWidth(400);
        p3.setColor(Color.GRAY);
        canvas.drawLine(200,0,200,200,p3);
    }
}
