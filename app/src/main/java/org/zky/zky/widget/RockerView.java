package org.zky.zky.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import org.zky.zky.R;
import org.zky.zky.utils.ScreenUtils;

/**
 * rocker
 * Created by zhan9 on 2016/12/16.
 */

public class RockerView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "RockerView";
    //绘制间隔
    private static int DEFAULT_INTERVAL = 50;
    //默认宽高
    private static  int DEFAULT_WIDTH = 200;
    private static  int DEFAULT_HEIGHT = 200;
    //默认按钮宽高
    private static  int DEFAULT_BUTTON_WIDTH = 36;


    private Point mStartPoint;
    private Point mEndPoint;
    private Point mCenterPoint;
    private Point mCurrentPoint;
    private int width;
    private int height;

    private boolean mIsDrawing;
    private Context mContext;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Paint mPaint;
    private int id_button;
    private int id_background;
    private float buttonWidth;
    private float buttonHeight;
    private Bitmap bm_button;
    private Bitmap bm_background;


    public RockerView(Context context) {
        super(context);
        mContext = context;
        init();
    }


    public RockerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RockerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(attrs);
        init();

    }

    private void initAttrs(AttributeSet attrs) {
        Log.e(TAG, "RockerView: initatrs");

        TypedArray array =mContext.obtainStyledAttributes(attrs, R.styleable.RockerView);
        id_button = array.getResourceId(R.styleable.RockerView_button, 0);
        id_background = array.getResourceId(R.styleable.RockerView_mBackground,0);
        buttonWidth = array.getDimension(R.styleable.RockerView_buttonWidth, ScreenUtils.dip2px(mContext,DEFAULT_BUTTON_WIDTH));
        buttonHeight = array.getDimension(R.styleable.RockerView_buttonHeight, ScreenUtils.dip2px(mContext,DEFAULT_BUTTON_WIDTH));
        array.recycle();
    }


    private void init() {
        if (id_button!=0) {

            bm_button = BitmapFactory.decodeResource(mContext.getResources(), id_button);
            bm_button = ScreenUtils.zoomImg(bm_button,buttonWidth,buttonHeight);
            Log.e(TAG, "init: decode bitmap" +bm_button);
        }
        if (id_background!=0){
            bm_background = BitmapFactory.decodeResource(mContext.getResources(), id_background);
        }


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
        //绘制背景
        drawBackground();
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
            drawButton();
            long end = System.currentTimeMillis();
            if (end-start< DEFAULT_INTERVAL){
                try {
                    Thread.sleep(DEFAULT_INTERVAL -(end-start));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void drawButton() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            if (bm_button!=null){
                mCanvas.drawBitmap(bm_button,mCurrentPoint.x-buttonWidth/2,mCurrentPoint.y-buttonHeight/2,mPaint);
            }else {
                mCanvas.drawCircle(mCurrentPoint.x,mCurrentPoint.y, ScreenUtils.dip2px(mContext,DEFAULT_BUTTON_WIDTH),mPaint);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (mCanvas!=null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    private void drawBackground() {
        try {

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
                break;
            case MotionEvent.ACTION_MOVE:
                mEndPoint.x = (int) event.getX();
                mEndPoint.y = (int) event.getY();

                mCurrentPoint.x = mCenterPoint.x + (mEndPoint.x - mStartPoint.x);
                mCurrentPoint.y = mCenterPoint.y + (mEndPoint.y - mStartPoint.y);
                Log.i(TAG, "drawButton: start("+mStartPoint.x+","+mStartPoint.y+") end("+mEndPoint.x+","+mEndPoint.y+")"+",current("+mCurrentPoint.x+","+mCurrentPoint.y+") , center("+mCenterPoint.x+","+mCenterPoint.y+")");

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

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode==MeasureSpec.EXACTLY){
            result = size;
        }else{
            result =  ScreenUtils.dip2px(mContext,DEFAULT_HEIGHT);
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
            result =  ScreenUtils.dip2px(mContext,DEFAULT_WIDTH);
            if (mode ==MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }
        return result;
    }



}
