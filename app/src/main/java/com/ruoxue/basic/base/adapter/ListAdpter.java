package com.ruoxue.basic.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ruoxue_ye on 2015/12/23.
 */
public abstract class ListAdpter<T> extends BaseAdapter {
    protected Context context;
    private List<T> mMode;

    public ListAdpter(Context context, List<T> mMode) {
        this.context = context;
        this.mMode = mMode;
    }

    @Override
    public boolean isEmpty() {
        return mMode.isEmpty();
    }

    @Override
    public int getCount() {
        return mMode != null ? mMode.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mMode.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return createView(position, convertView, parent, context);
    }
    /**
     * 为了侧滑
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return 1;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }


    protected abstract View createView(int position, View convertView, ViewGroup parent, Context context);
}
