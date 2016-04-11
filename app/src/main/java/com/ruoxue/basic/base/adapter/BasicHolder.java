package com.ruoxue.basic.base.adapter;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by ruoxue_ye on 2015/12/23.
 */
public class BasicHolder {
    public static <T extends View> T get(View view, @IdRes int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = $$(view, id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }


    private static View $$(View view, @IdRes int id) {
        return view.findViewById(id);


    }


}
