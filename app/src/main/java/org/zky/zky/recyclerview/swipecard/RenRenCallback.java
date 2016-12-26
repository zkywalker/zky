package org.zky.zky.recyclerview.swipecard;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import org.zky.zky.R;

import java.util.List;


import static org.zky.zky.recyclerview.swipecard.CardConfig.MAX_SHOW_COUNT;
import static org.zky.zky.recyclerview.swipecard.CardConfig.SCALE_GAP;
import static org.zky.zky.recyclerview.swipecard.CardConfig.TRANS_Y_GAP;

/**
 * 介绍：人人影视效果的Callback
 * 作者：zhangxutong
 * 邮箱：mcxtzhang@163.com
 * 主页：http://blog.csdn.net/zxt0601
 * 时间： 16/12/18.
 */

public class RenRenCallback extends ItemTouchHelper.SimpleCallback {
    private static final String TAG = "RenRenCallback";

    protected RecyclerView mRv;
    protected List mDatas;
    protected RecyclerView.Adapter mAdapter;

    public RenRenCallback(RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        this(0,
                ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                rv, adapter, datas);
    }

    public RenRenCallback(int dragDirs, int swipeDirs
            , RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        super(dragDirs, swipeDirs);
        mRv = rv;
        mAdapter = adapter;
        mDatas = datas;
    }

    //水平方向是否可以被回收掉的阈值
    public float getThreshold(RecyclerView.ViewHolder viewHolder) {
        return mRv.getWidth() * getSwipeThreshold(viewHolder);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //Log.e("swipecard", "onSwiped() called with: viewHolder = [" + viewHolder + "], direction = [" + direction + "]");
        //rollBack(viewHolder);
        //★实现循环的要点
        Log.e(TAG, "onSwiped: "+viewHolder.getLayoutPosition());
        Object remove = mDatas.remove(viewHolder.getLayoutPosition());
        mDatas.add( remove);
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        //先根据滑动的dxdy 算出现在动画的比例系数fraction
        double swipValue = Math.sqrt(dX * dX + dY * dY);
        double fraction = swipValue / getThreshold(viewHolder);
        //边界修正 最大为1
        if (fraction > 1) {
            fraction = 1;
        }
        //对每个ChildView进行缩放 位移
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);

            //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
            Log.e(TAG, "onChildDraw: childCount"+childCount+",i:"+i);
            int level = childCount-i-1;
//            if (level==2){
//                child.findViewById(R.id.tv_test).setBackgroundColor(Color.GREEN);
//            }
            if (level > 0) {
                child.setScaleX((float) (1 - SCALE_GAP * level + fraction * SCALE_GAP));

                if (level < MAX_SHOW_COUNT ) {
                    child.setScaleY((float) (1 - SCALE_GAP * level + fraction * SCALE_GAP));
                    child.setTranslationY((float) (TRANS_Y_GAP * level - fraction * TRANS_Y_GAP));
                } else {
//                    child.setTranslationY((float) (TRANS_Y_GAP * level - fraction * TRANS_Y_GAP));
                }
            }

        }
    }
}
