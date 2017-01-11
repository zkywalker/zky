package org.zky.zky.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.FrameLayout;

import org.zky.zky.R;

/**
 * 邮票视图
 * Created by zhangkun on 2017/1/7.
 */

public class StampView extends FrameLayout{
    private static final String TAG = "StampView";
    private Context mContext;

    private Paint mPaint;

    private int mWidth;

    private int mHeight;

    //半圆间隔
    private int space = 3;

    //半圆半径
    private int radius = 2;

    private int circleNumH ;

    private int circleNumV ;

    private int remainV;

    private int remainH;


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
        mPaint.setColor(getResources().getColor(R.color.three));
//        mPaint.setStrokeWidth(4);
        radius = dp2px(context,radius);
        space = dp2px(context,space);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth =w;
        mHeight = h;
        if (remainV==0){
            remainV = (w-space)%(2*radius+space)+2*radius;
        }
        if (remainH==0){
            remainH = (h-space)%(2*radius+space)+2*radius;
            Log.e(TAG, "onSizeChanged: remainH:"+remainH+",h:"+h);
        }
        circleNumV =((w-space)/(2*radius+space))-1;
        circleNumH =((h-space)/(2*radius+space))-1;
        Log.e(TAG, "onSizeChanged: remainV:"+remainV+",w:"+w+",circleNumV:"+circleNumV);


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
        //stamp上方的path
        path.moveTo(0,0);
        for (int i = 0; i < circleNumV; i++) {
            float startX = space+radius+remainV/2+(space+radius*2)*i;
            path.lineTo(startX,0);
            path.arcTo(startX,-radius,startX+2*radius,radius,0,180,true);
            path.moveTo(startX+2*radius,0);
            if (i==circleNumV-1){
                Log.e(TAG, "onDraw: startX:"+startX );
                path.lineTo(mWidth,0);
                path.moveTo(mWidth,0);
            }

        }
        //绘制右侧
//        path.lineTo(mWidth,mHeight);
        for (int i = 0;i<circleNumH;i++){
            float startY = space+radius+remainH/2+(space+radius*2)*i;
            path.lineTo(mWidth,startY);
            path.arcTo(mWidth-radius,startY,mWidth+radius,startY+2*radius,90,180,true);
            path.moveTo(mWidth,startY+2*radius);
            if (i==circleNumH-1){
                path.lineTo(mWidth,mHeight);
                path.moveTo(mWidth,mHeight);
            }
        }
        //绘制左侧
        path.moveTo(0,0);
        Log.e(TAG, "onDraw: radius:"+radius+",remain:"+remainH );
        for (int i = 0;i<circleNumH;i++){
            float startY = space+radius+remainH/2+(space+radius*2)*i;
            path.lineTo(0,startY);
            path.arcTo(0-radius,startY,radius,startY+2*radius,90,-180,true);
            path.moveTo(0,startY+2*radius);
            if (i==circleNumH-1){
                path.lineTo(0,mHeight);
                path.moveTo(0,mHeight);
            }
        }
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
//        path.close();
        path.moveTo(0,0);
        path.lineTo(mWidth,0);
        path.lineTo(mWidth,mHeight);
        path.lineTo(0,mHeight);
        path.lineTo(0,0);

        path.setFillType(Path.FillType.EVEN_ODD);
        canvas.drawPath(path,mPaint);
    }

    public static int dp2px(Context context, float value){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


}
