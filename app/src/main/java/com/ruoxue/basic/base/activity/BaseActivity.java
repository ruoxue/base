package com.ruoxue.basic.base.activity;


import android.os.Bundle;

/**
 *
 */
public abstract class BaseActivity extends BasicFullActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTranslation();//沉浸式布局
    }
}
