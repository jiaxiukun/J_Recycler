package com.xzz.draw.j_recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @param <T>
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 布局id
     */
    protected int mLayoutID;

    /**
     * 数据集合
     */
    protected List<T> mData;

    public CommonAdapter(Context context, int layoutID,List<T> data){
        this.mData = data;
        this.mContext = context;
        this.mLayoutID = layoutID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //实例化ViewHolder
        ViewHolder viewHolder = ViewHolder.getViewHolder(mContext,mLayoutID,parent);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //具体赋值逻辑留给用户处理
        conver(holder,mData.get(position));
    }

    //转换类
    public abstract void conver(ViewHolder viewHodler,T o);

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void setData(List<T> data){
        this.mData = data;
        notifyDataSetChanged();
    }
}
