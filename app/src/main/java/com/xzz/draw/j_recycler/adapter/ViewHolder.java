package com.xzz.draw.j_recycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 *
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    /**
     * 使用集合来存储item上的控件
     */
    private SparseArray<View> mViewList;

    /**
     * 加载item的布局
     */
    private View mConvertView;


    private ViewHolder(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        mViewList = new SparseArray<View>();
    }

    /**
     *
     * 获取ViewHolder的方法
     *
     * @param context 上下文
     * @param layoutID 布局ID
     * @param parent 父控件
     * @return 返回当前的布局ID所对应的ViewHolder的实例
     */
    public static ViewHolder getViewHolder(Context context, int layoutID, ViewGroup parent){
        //将布局ID转化为视图
        View itemView = LayoutInflater.from(context).inflate(layoutID,parent,false);
        //实例化当前ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemView);
        //返回
        return viewHolder;
    }

    /**
     *
     * 通过ID取控件的方法 对ItemView的重用已经进行了缓存
     *
     * @param viewID 控件的ID
     * @return 返回id所对应的控件
     */
    public <T extends View>T getView(int viewID){
        //从集合中取控件
        View view = mViewList.get(viewID);
        //如果没有就通过findViewById创建一个,并缓存到集合中
        if(view == null){
            view = mConvertView.findViewById(viewID);
            mViewList.put(viewID,view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewID,String string){
        TextView textView = getView(viewID);
        textView.setText(string);
        return this;
    }

    public ViewHolder setimageResouse(int viewID,int resID){
        ImageView imageView = getView(viewID);
        imageView.setImageResource(resID);
        return this;
    }



    public ViewHolder setOnItemClickLisenter(final OnItemClickLisenter listener){
         this.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 listener.onItemClickLisenter(itemView,getLayoutPosition());
             }
         });
        return this;
    }

    public ViewHolder setOnClickLisenter(int viewID,View.OnClickListener listener){
         View view = getView(viewID);
        view.setOnClickListener(listener);
        return this;
    }

    public RecyclerView.ViewHolder setImageResouseUrl(int viewID,String url,Context context){
        ImageView imageView = getView(viewID);
        Glide.with(context).load(url).into(imageView);
        return this;
    }

    public interface OnItemClickLisenter{
        void onItemClickLisenter(View view, int position);
    }
}
