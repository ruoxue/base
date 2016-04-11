package com.ruoxue.basic.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ruoxue.basic.Main3Activity;
import com.ruoxue.basic.R;
import com.ruoxue.basic.base.activity.BasicFullActivity;
import com.ruoxue.basic.base.adapter.BasicPagerAdapter;
import com.ruoxue.basic.netConfig.Config;
import com.ruoxue.basic.ruoxueUtil.SPUtils;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BasicFullActivity {

    private ViewPager vpSplash;
    private List<Integer> list = new ArrayList<>();

    @Override
    protected void loadDate() {
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);

        vpSplash.setAdapter(new BasicPagerAdapter<Integer>(list, SplashActivity.this) {
            @Override
            protected Object getView(Context ctx, ViewGroup container, int position) {

                View v = LayoutInflater.from(ctx).inflate(R.layout.item_iv, container, false);
                ImageView iv = $$(v, R.id.iv_img);
                iv.setImageResource(list.get(position));
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                if (position == list.size() - 1) {
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SPUtils.put(SplashActivity.this, Config.Launcher.isFirst, false);
                            startActivity(new Intent(SplashActivity.this, Main3Activity.class));
                            finish();
                        }
                    });
                }
                container.addView(iv);
                return v;
            }
        });
    }

    @Override
    protected int loadRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        vpSplash = $(R.id.vp_splash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
    }

    @Override
    public void responseImpl(int tag, Object o) {

    }

    @Override
    public void request(int tag) {

    }
}
