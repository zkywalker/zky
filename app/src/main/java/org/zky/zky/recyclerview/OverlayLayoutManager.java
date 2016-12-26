package org.zky.zky.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import org.zky.zky.R;


/**
 *一层层覆盖的recycler view
 * Created by zhangkun on 2016/12/26.
 */

public class OverlayLayoutManager extends RecyclerView.LayoutManager {
    public static int MAX_SHOW_COUNT = 3;
    public static float SCALE = 0.05f;
    public static int TRANSLATION_Y = 15;    //单位dp


    //view偏移距离
    private int  mTranslationY ;
    //view缩小量
    private float mScale = SCALE ;
    //显示的view数量
    private int mMaxShowCount = MAX_SHOW_COUNT;

    private Context mContext;

    public OverlayLayoutManager(Context context){
        super();
        mContext = context;
        mTranslationY = dp2px(mContext,TRANSLATION_Y);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //将子视图detach（分离）放入scrap(废料池)中缓存，目的是循环利用子视图
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        //没有就不需要布局
        if (state.getItemCount() ==0){
            removeAndRecycleAllViews(recycler);
            return;
        }

        int bottomPosition;
        //边界处理
        if (itemCount < mMaxShowCount) {
            bottomPosition = 0;
        } else {
            bottomPosition = itemCount - mMaxShowCount;
        }

        for (int i = mMaxShowCount; i >-1; i--) {
            View view = recycler.getViewForPosition(i);
//            if (i==3)
//                view.findViewById(R.id.tv_test).setBackgroundColor(Color.RED);
            addView(view);
            measureChildWithMargins(view,0,0);
            int marginWidth = getWidth() - getDecoratedMeasuredWidth(view);
            int marginHeight = getHeight() - getDecoratedMeasuredHeight(view);
            //设置view到布局中间
            layoutDecorated(view,marginWidth/2,marginHeight/2,marginWidth/2+getDecoratedMeasuredWidth(view),marginHeight/2+getDecoratedMeasuredHeight(view));
            //第一层不需要偏移
            if (i>0){
                view.setScaleX(1-mScale*i);
                if (i < mMaxShowCount ) {
                    view.setTranslationY(mTranslationY * i);
                    view.setScaleY(1 - mScale * i);
                } else {//第N层在 向下位移和Y方向的缩小的成都与 N-1层保持一致
                    view.setTranslationY(mTranslationY * (i - 1));
                    view.setScaleY(1 - mScale * (i - 1));
                }
            }
        }
    }

    public static int dp2px(Context context,int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

}
