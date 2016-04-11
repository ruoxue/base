package com.ruoxue.basic.base.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ruoxue_ye on 2016/2/1.
 */
public abstract class BasicPagerAdapter<E> extends PagerAdapter {
    protected List<E> mMode;
    protected Context context;

    public BasicPagerAdapter(List<E> mMode, Context context) {
        this.mMode = mMode;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mMode.isEmpty()?0:mMode.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 子类实现
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return getView(context,container, position);
    }

    protected abstract Object getView(Context ctx,ViewGroup container, int position);
}
