package xyz.ruoxue.base.listener.listView;

import android.widget.AbsListView;

/**
 * Created by ruoxue_ye on 2015/12/30.
 *
 * 实现  listview 的加载 刷新
 *
 */
public abstract class OnScrollListenerImpl implements AbsListView.OnScrollListener {
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (view.getCount() - 1 == view.getLastVisiblePosition()) {
            loadMore();
        } else if (view.getFirstVisiblePosition() == 0) {
            refresh();
        }
    }

    public abstract void loadMore();

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    public abstract void refresh();

}
