package com.ruoxue.basic.netConfig;

/**
 * Created by ruoxue_ye on 2015/12/11.
 */
public interface Config {
    class Time {
        public static int launcherTime = 2000;

    }
    class  Launcher{
        public  static  String isFirst="isFirst";
    }

    interface NetStatus {
        String DATA = "data";
        String STATUS = "ret";
        int ZERO = 0;
        int FIRST = 200;
        int SECOND = 2;
        int THIRD = 3;
        String MSG = "msg";


    }


}
