package xyz.ruoxue.base.exception;

import android.content.Context;

/**
 * Created by ruoxue_ye on 2015/12/25.
 */
public class UnKnowException implements Thread.UncaughtExceptionHandler {
    // 需求是 整个应用程序 只有一个
    private static UnKnowException INSTANCE;
    private Context context;

    //1.私有化构造方法
    private UnKnowException() {

    }

    public static synchronized UnKnowException getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UnKnowException();
        return INSTANCE;
    }

    public void init(Context context) {
        this.context = context;
    }


    public void uncaughtException(Thread arg0, Throwable arg1) {
        // 在此可以把用户手机的一些信息以及异常信息捕获并上传
        //干掉当前的程序
        android.os.Process.killProcess(android.os.Process.myPid());
        //gotohome

    }
}
