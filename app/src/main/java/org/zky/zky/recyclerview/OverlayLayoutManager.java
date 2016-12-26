package org.zky.zky.recyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;


/**
 *一层层覆盖的recycler view
 * Created by zhangkun on 2016/12/26.
 */

public class OverlayLayoutManager extends RecyclerView.LayoutManager {
    public static int MAX_SHOW_COUNT = 4;
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

        //从可见的最底层View开始layout，依次层叠上去
        for (int position = bottomPosition; position < itemCount; position++) {
            View view = recycler.getViewForPosition(position);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
            //我们在布局时，将childView居中处理，这里也可以改为只水平居中
            layoutDecorated(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));

            //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
            int level = itemCount - position - 1;
            //除了顶层不需要缩小和位移
            if (level > 0 /*&& level < mShowCount - 1*/) {
                //每一层都需要X方向的缩小
                view.setScaleX(1 - mScale* level);
                //前N层，依次向下位移和Y方向的缩小
                if (level < mMaxShowCount - 1) {
                    view.setTranslationY(mTranslationY * level);
                    view.setScaleY(1 - mScale * level);
                } else {//第N层在 向下位移和Y方向的缩小的成都与 N-1层保持一致
                    view.setTranslationY(mTranslationY * (level - 1));
                    view.setScaleY(1 - mScale * (level - 1));
                }
            }
        }
    }

    public static int dp2px(Context context,int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

}
