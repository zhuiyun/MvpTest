package com.jbstore.cloud.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jbstore.cloud.mvp.presenter.IPresenter;

/**
 * Created by gwy on 2018/3/1.
 *
 * @author:zhuiyun
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView {

    private P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter==null){
            presenter=createPresenter();
        }
        presenter.attachView(this);

    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter==null){
            presenter.detach();
        }
    }

    public P getPresenter(){
        return presenter;
    }
}
