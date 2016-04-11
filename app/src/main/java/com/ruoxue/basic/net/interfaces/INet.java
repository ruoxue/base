package com.ruoxue.basic.net.interfaces;

/**
 * Created by ruoxue_ye on 2015/12/11.
 */
public interface INet {

    void request(int tag);

    void response(int tag, Object o);

}
