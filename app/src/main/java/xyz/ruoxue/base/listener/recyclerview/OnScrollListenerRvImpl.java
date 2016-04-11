package xyz.ruoxue.base.listener.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ruoxue_ye on 2015/12/30.
 */
public abstract class OnScrollListenerRvImpl extends RecyclerView.OnScrollListener {
    private int lastVisibleItem;
    private int firstVisibleItem;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {// GridLayoutManager 是 LinearLayoutManager 子类
            lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
//        } else if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
//            lastVisibleItem = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
//            firstVisibleItem = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                if (lastVisibleItem == recyclerView.getAdapter().getItemCount() - 1) {
                    loadMore();
                } else if (recyclerView.getTop() == 0 && firstVisibleItem == 0) {
                    refresh();
                }
                break;
            default:
        }

    }

    public abstract void loadMore();

    public abstract void refresh();
}
