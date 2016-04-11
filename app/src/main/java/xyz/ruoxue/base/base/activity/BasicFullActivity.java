package xyz.ruoxue.base.base.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import xyz.ruoxue.base.R;
import xyz.ruoxue.base.application.RuoxueApp;
import xyz.ruoxue.base.net.interfaces.INet;
import xyz.ruoxue.base.ruoxueUtil.AppUtils;
import xyz.ruoxue.base.ruoxueUtil.L;


/**
 * Created by ruoxue_ye on 2016/2/1.
 */
public abstract class BasicFullActivity extends AppCompatActivity implements INet {
    private TextView mTvTitle;
    private ImageView ivBack;
    protected ProgressBar pd;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(loadRes());
        setBack();
        toolbar = $(R.id.tb);
        initView();
        pd = $(R.id.pb);
        pd.setVisibility(View.GONE);
        loadDate();
        setSupportActionBar(toolbar);
        RuoxueApp.putContext(this);

    }

    /**
     * 加载数据
     */
    protected abstract void loadDate();

    protected abstract
    @LayoutRes
    int loadRes();

    protected boolean isDebug() {
        return AppUtils.isDubug(this);
    }


    protected abstract void initView();

    protected void setBackRes(@DrawableRes int backImgRes) {
        ivBack = $(R.id.iv_back);
        ivBack.setImageResource(backImgRes);
    }

    protected void setBackRes(Drawable backImgRes) {
        ivBack = $(R.id.iv_back);
        ivBack.setImageDrawable(backImgRes);
    }

    protected void setActionBarTranslation() {
        setActionBarTranslation(true);
    }

    protected void setActionBarTranslation(boolean flag) {
        if (flag) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    /**
     * 网络回调处理null
     *
     * @param tag
     * @param o
     * @see {@link @responseImpl(tag,o)}
     */

    @Override
    public void response(int tag, Object o) {
        if (pd != null && pd.getVisibility() != View.GONE) {
            pd.setVisibility(View.GONE);
        }
        if (o == null) return;
        responseImpl(tag, o);
    }

    public abstract void responseImpl(int tag, Object o);

    /**
     * 设置标题
     *
     * @param resId
     */
    protected void setCustomText(@StringRes int resId) {
        mTvTitle.setText(resId);
    }

    protected void setCustomText(String title) {
        mTvTitle = $(R.id.tv_title);
        mTvTitle.setText(title);
    }

    protected void setCustomText(CharSequence title) {
        mTvTitle = $(R.id.tv_title);
        mTvTitle.setText(title);
    }

    /**
     * 设置标题颜色
     *
     * @param colorId
     */
    protected void setCustomColor(@ColorInt int colorId) {
        mTvTitle.setTextColor(colorId);
    }

    protected void setCustomColor(String color) {
        mTvTitle.setTextColor(Color.parseColor(color));
    }


    protected void setBack() {
        setBack(true);
    }

    /*
    返回
     */
    protected void setBack(final boolean isBack) {
        ivBack = $(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBack)
                    onBackPressed();
            }
        });

    }

    protected <T extends View> T $(@IdRes int resId) {
        return (T) findViewById(resId);
    }

    protected <T extends View> T $$(@IdRes int resId, View view) {
        return (T) view.findViewById(resId);
    }

    protected <T extends View> T $$(View view, @IdRes int resId) {
        return $$(resId, view);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle("");
        setCustomText(title);
    }

    @Override
    public final void setTitle(int titleId) {
        super.setTitle("");
        setCustomText(titleId);
    }

    /**
     * 获取TextView中的文本信息
     *
     * @param tv
     * @return
     */
    protected String mGetTextViewContent(TextView tv) {
        return tv.getText().toString().trim();
    }

    /**
     * 获取EditText中的文本信息
     *
     * @param et
     * @return
     */
    protected String mGetEditTextContent(EditText et) {
        return et.getText().toString().trim();
    }

    protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onBackPressed();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("ExitApp");
        this.registerReceiver(this.broadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.broadcastReceiver);
    }


    protected void exit() {
        Intent intent = new Intent();
        intent.setAction("ExitApp");
        this.sendBroadcast(intent);
        onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    protected void logger(String s) {
        if (isDebug())
            L.i(s);
    }
}
