package com.xzz.draw.j_recycler.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * @param <T>
 */

public abstract class MulitItemTypeaAdapter<T> extends CommonAdapter<T> {

    private MulitItemTypeSupport mulitItemTypeSupport;

    public MulitItemTypeaAdapter(Context context,List<T> data,
                                 MulitItemTypeSupport mulitItemTypeSupport) {
        super(context, -1, data);
        this.mulitItemTypeSupport = mulitItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mulitItemTypeSupport.getItemViewType(position,mData.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutID = mulitItemTypeSupport.getLayoutID(viewType);
        ViewHolder viewHolder = ViewHolder.getViewHolder(mContext,layoutID,parent);

        return viewHolder;
    }
}
