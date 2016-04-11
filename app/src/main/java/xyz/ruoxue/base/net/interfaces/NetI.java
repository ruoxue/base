package xyz.ruoxue.base.net.interfaces;

/**
 * Created by ruoxue_ye on 2016/1/21.
 */
public interface NetI<T> {

    void success(int tag, T t);

    void fail(int tag, T t);
}
