package xyz.ruoxue.base.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruoxue.basic.net.interfaces.INet;


/**
 * Created by ruoxue_ye on 2015/12/23.
 * <p/>
 * <p/>
 * basic fragment
 */
public abstract class BasicFragment extends Fragment implements INet {
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.context instanceof FragmentActivity) {
            this.context = context;

        } else {
            throw new ClassCastException("传入的ctx 不是标准的 FragmentActivity   不能进行其他的操作 ");
        }
    }
    //**数据界面的加载

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(loadRes(), container, false);
    }


    protected abstract
    @LayoutRes
    int loadRes();


    //初始化控件
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        initView();
        loaddate();
    }


    protected abstract void initView();

    protected abstract void loaddate();


    protected <T> T $(@IdRes int id) {
        return (T) ((FragmentActivity) context).findViewById(id);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
