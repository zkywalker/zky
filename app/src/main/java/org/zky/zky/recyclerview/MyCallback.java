package org.zky.zky.recyclerview;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

/**
 * callback
 * Created by zhangkun on 2016/12/26.
 */

public class MyCallback extends ItemTouchHelper.SimpleCallback {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List mData;

    public MyCallback(RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        this(0, ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                rv, adapter, datas);
    }

    public MyCallback(int dragDirs, int swipeDirs
            , RecyclerView rv, RecyclerView.Adapter adapter, List datas) {
        super(dragDirs, swipeDirs);
        mRecyclerView = rv;
        mAdapter = adapter;
        mData = datas;
    }

    //水平方向是否可以被回收掉的阈值
    public float getThreshold(RecyclerView.ViewHolder viewHolder) {
        return mRecyclerView.getWidth() * getSwipeThreshold(viewHolder);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Object remove = mData.remove(viewHolder.getLayoutPosition());
        mData.add(0, remove);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
