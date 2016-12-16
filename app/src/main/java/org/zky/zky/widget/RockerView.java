package org.zky.zky.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * rocker
 * Created by zhan9 on 2016/12/16.
 */

public class RockerView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "RockerView";
    //绘制间隔
    private static int INTERVAL= 50;

    private Point mStartPoint;
    private Point mEndPoint;
    private Point mCenterPoint;
    private Point mCurrentPoint;
    private int width;
    private int height;

    private boolean mIsDrawing;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint;


    public RockerView(Context context) {
        super(context);
        init();
    }


    public RockerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RockerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);

        mStartPoint = new Point(0, 0);
        mEndPoint = new Point(0, 0);
        mCenterPoint = new Point(0, 0);
        mCurrentPoint = new Point(0, 0);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing){
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            if (end-start<INTERVAL){
                try {
                    Thread.sleep(INTERVAL-(end-start));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawCircle(mCurrentPoint.x,mCurrentPoint.y,100,mPaint);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (mCanvas!=null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mStartPoint.x = (int) event.getX();
                mStartPoint.y = (int) event.getY();
                Log.e(TAG, "draw: start("+mStartPoint.x+","+mStartPoint.y+")");
                break;
            case MotionEvent.ACTION_MOVE:
                mEndPoint.x = (int) event.getX();
                mEndPoint.y = (int) event.getY();

                mCurrentPoint.x = mCenterPoint.x + (mEndPoint.x - mStartPoint.x);
                mCurrentPoint.y = mCenterPoint.y + (mEndPoint.y - mStartPoint.y);
                Log.e(TAG, "draw: start("+mStartPoint.x+","+mStartPoint.y+") end("+mEndPoint.x+","+mEndPoint.y+")"+",current("+mCurrentPoint.x+","+mCurrentPoint.y+") , center("+mCenterPoint.x+","+mCenterPoint.y+")");

                break;
            case MotionEvent.ACTION_UP:
                mCurrentPoint.x = width/2;
                mCurrentPoint.y = height / 2;
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width=measureWidth(widthMeasureSpec);
        height=measureHeight(heightMeasureSpec);
        mCenterPoint.x = width/2;
        mCenterPoint.y = height / 2;
        mCurrentPoint.x = width/2;
        mCurrentPoint.y = height / 2;
        setMeasuredDimension(width,height);
    }

    //TODO 修改默认值
    private int measureHeight(int heightMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode==MeasureSpec.EXACTLY){
            result = size;
        }else{
            result =  300;
            if (mode ==MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode==MeasureSpec.EXACTLY){
            result = size;
        }else{
            result =  300;
            if (mode ==MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
        return result;
    }


}
