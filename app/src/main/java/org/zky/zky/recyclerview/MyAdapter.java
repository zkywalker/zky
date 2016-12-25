package org.zky.zky.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkywalker on 2016/12/25.
 * package:org.zky.zky.recyclerview
 */

public abstract class MyAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected ViewGroup mRv;
    private OnItemClickListener mOnItemClickListener;

    public MyAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    public MyAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = datas;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(this.mContext, (View)null, parent, this.mLayoutId);
        if(null == this.mRv) {
            this.mRv = parent;
        }

        return viewHolder;
    }

    protected int getPosition(android.support.v7.widget.RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    /** @deprecated */
    @Deprecated
    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        if(this.isEnabled(viewType)) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(MyAdapter.this.mOnItemClickListener != null) {
                        int position = MyAdapter.this.getPosition(viewHolder);
                        MyAdapter.this.mOnItemClickListener.onItemClick(parent, v, MyAdapter.this.mDatas.get(position), position);
                    }

                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if(MyAdapter.this.mOnItemClickListener != null) {
                        int position = MyAdapter.this.getPosition(viewHolder);
                        return MyAdapter.this.mOnItemClickListener.onItemLongClick(parent, v, MyAdapter.this.mDatas.get(position), position);
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        this.setListener(position, holder);
        this.convert(holder, this.mDatas.get(position));
    }

    protected void setListener(final int position, final ViewHolder viewHolder) {
        if(this.isEnabled(this.getItemViewType(position))) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if(MyAdapter.this.mOnItemClickListener != null) {
                        MyAdapter.this.mOnItemClickListener.onItemClick(MyAdapter.this.mRv, v, MyAdapter.this.mDatas.get(position), position);
                    }

                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    if(MyAdapter.this.mOnItemClickListener != null) {
                        int position = MyAdapter.this.getPosition(viewHolder);
                        return MyAdapter.this.mOnItemClickListener.onItemLongClick(MyAdapter.this.mRv, v, MyAdapter.this.mDatas.get(position), position);
                    } else {
                        return false;
                    }
                }
            });
        }
    }

    public abstract void convert(ViewHolder var1, T var2);

    public int getItemCount() {
        return this.mDatas != null?this.mDatas.size():0;
    }

    public void setDatas(List<T> list) {
        if(this.mDatas != null) {
            if(null != list) {
                ArrayList temp = new ArrayList();
                temp.addAll(list);
                this.mDatas.clear();
                this.mDatas.addAll(temp);
            } else {
                this.mDatas.clear();
            }
        } else {
            this.mDatas = list;
        }

        this.notifyDataSetChanged();
    }

    public void remove(int i) {
        if(null != this.mDatas && this.mDatas.size() > i && i > -1) {
            this.mDatas.remove(i);
            this.notifyDataSetChanged();
        }

    }

    public void addDatas(List<T> list) {
        if(null != list) {
            ArrayList temp = new ArrayList();
            temp.addAll(list);
            if(this.mDatas != null) {
                this.mDatas.addAll(temp);
            } else {
                this.mDatas = temp;
            }

            this.notifyDataSetChanged();
        }

    }

    public List<T> getDatas() {
        return this.mDatas;
    }

    public T getItem(int position) {
        return position > -1 && null != this.mDatas && this.mDatas.size() > position?this.mDatas.get(position):null;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(ViewGroup var1, View var2, T var3, int var4);

        boolean onItemLongClick(ViewGroup var1, View var2, T var3, int var4);
    }
}
