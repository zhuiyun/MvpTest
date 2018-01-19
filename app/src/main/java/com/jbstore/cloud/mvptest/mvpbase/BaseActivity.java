package com.jbstore.cloud.mvptest.mvpbase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by gwy on 2018/1/19.
 *
 * @author:zhuiyun
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends FragmentActivity {

    protected  T mPresenter;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityView(savedInstanceState);
        mContext=BaseActivity.this;
        mPresenter=createPresenter();
    }

    protected abstract T createPresenter();

    protected abstract void initActivityView(Bundle savedInstanceState);

    @Override
    protected void onResume() {
        super.onResume();
        if(null!=mPresenter){
            mPresenter.attach((V)this);
        }
    }

    //加载页面元素
    protected  abstract void findViewById();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null!=mPresenter){
            mPresenter.detachView();
        }
    }
}
