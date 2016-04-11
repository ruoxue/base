package com.ruoxue.basic.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.ruoxue.basic.Main3Activity;
import com.ruoxue.basic.R;
import com.ruoxue.basic.base.activity.BaseActivity;
import com.ruoxue.basic.netConfig.Config;
import com.ruoxue.basic.ruoxueUtil.AppUtils;
import com.ruoxue.basic.ruoxueUtil.SPUtils;

public class LauncherActivity extends BaseActivity {
    private final int ONE = 1;
    private final int TWO = 2;
    private final String VERSION = "version";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ONE:
                    /*
                    AppUtils.getVersionCode(LauncherActivity.this)> Integer.parseInt(SPUtils.get(LauncherActivity.this, VERSION, 0).toString())
                            &&
                     */
                    if ( Boolean.valueOf(SPUtils.get(LauncherActivity.this, Config.Launcher.isFirst, true).toString())) {
                        startActivity(new Intent(LauncherActivity.this, SplashActivity.class));
                    } else {
                        startActivity(new Intent(LauncherActivity.this, Main3Activity.class));
                    }
                    finish();
                    break;
                default:
                    startActivity(new Intent(LauncherActivity.this, Main3Activity.class));
                    finish();
                    break;

            }
        }
    };

    @Override
    protected void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Config.Time.launcherTime);
                    handler.sendEmptyMessage(ONE);
                } catch (InterruptedException e) {
                    handler.sendEmptyMessage(TWO);
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    protected int loadRes() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void loadDate() {
        SPUtils.put(LauncherActivity.this, VERSION, AppUtils.getVersionCode(LauncherActivity.this));
    }

    @Override
    public void responseImpl(int tag, Object o) {

    }

    @Override
    public void request(int tag) {

    }
}
