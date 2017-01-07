package org.zky.zky.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * 邮票视图
 * Created by zhangkun on 2017/1/7.
 */

public class StampView extends FrameLayout{
    private Context mContext;

    private Paint mPaint;

    private int defaultWidth = 300;

    private int defaultHeight = 120;

    private int mWidth;

    private int mHeight;

    //半圆间隔
    private int space = 10;

    //半圆半径
    private int radius = 6;

    private int circleNumH ;

    private int circleNumV ;

    private int remainV;


    public StampView(@NonNull Context context) {
        super(context);
        initialize(context,null,0);
    }

    public StampView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs,0);
    }

    public StampView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context,attrs,defStyleAttr);
    }

    private void initialize(Context context,AttributeSet attrs,int defStyleAttr){

        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setAlpha(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
//        mPaint.setStrokeWidth(4);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        mWidth = MeasureWidth(widthMeasureSpec);
//        mHeight = MeasureHeight(heightMeasureSpec);
//        setMeasuredDimension(mWidth,mHeight);
//    }

    private int MeasureWidth(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int width = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY){
            return width;
        }else if (mode == MeasureSpec.AT_MOST){
            return Math.min(width, dp2px(mContext, defaultWidth));
        }
        return defaultWidth;
    }

    private int MeasureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int height = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY){
            return height;
        }else if (mode == MeasureSpec.AT_MOST){
            return Math.min(height, dp2px(mContext, defaultHeight));
        }
        return defaultHeight;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth =w;
        mHeight = h;
        if (remainV==0){
            remainV = (w-space)%(2*radius+space);
        }
        circleNumV =((w-space)/(2*radius+space));
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.RED);
        super.onDraw(canvas);
//        canvas.drawRect(0,0,mWidth,mHeight,mPaint);

//        Paint p = new Paint();
//        p.setColor(Color.TRANSPARENT);
//        p.setStyle(Paint.Style.FILL);
//        p.setAntiAlias(true);
//        for (int i=0;i<circleNumV;i++){
//            float x = space+radius+remainV/2+((space+radius*2)*i);
//            canvas.drawCircle(x,0,radius,p);
//            canvas.drawCircle(x,getHeight(),radius,p);
//        }

        Path path = new Path();
        path.moveTo(0,0);
        for (int i = 0; i < circleNumV; i++) {
            float startX = space+radius+remainV/2+(space+radius*2)*i;
            path.lineTo(startX,0);
            path.arcTo(startX,-radius,startX+2*radius,radius,0,180,true);
            path.moveTo(startX+2*radius,0);
            if (i==circleNumV-1){
                path.lineTo(mWidth,0);
                path.moveTo(mWidth,0);
            }

        }
        path.lineTo(mWidth,mHeight);
        path.moveTo(0,0);
        path.lineTo(0,mHeight);
        path.moveTo(0,mHeight);
        for (int i = 0; i < circleNumV; i++) {
            float startX = space+radius+remainV/2+(space+radius*2)*i;
            path.lineTo(startX,mHeight);
            path.arcTo(startX,mHeight-radius,startX+2*radius,mHeight+radius,0,-180,true);
            path.moveTo(startX+2*radius,mHeight);
            if (i==circleNumV-1)
                path.lineTo(mWidth,mHeight);
        }
        path.close();

        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        canvas.drawPath(path,mPaint);
    }

    public static int dp2px(Context context, float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


}
