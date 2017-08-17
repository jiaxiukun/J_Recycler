package com.xzz.draw.j_recycler.adapter;

/**
 * @param <T>
 */

public interface MulitItemTypeSupport<T> {
    int getLayoutID(int ItemType);
    int getItemViewType(int position, T o);
}
