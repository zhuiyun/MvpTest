package com.jbstore.cloud.mvp;

import android.os.Bundle;
import android.util.Log;

import com.jbstore.cloud.mvp.bean.Tranlation;
import com.jbstore.cloud.mvp.presenter.IPresenter;
import com.jbstore.cloud.mvp.presenter.WeatherPresenter;
import com.jbstore.cloud.mvp.view.BaseActivity;
import com.jbstore.cloud.mvp.view.WeatherView;

public class MainActivity extends BaseActivity implements WeatherView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((WeatherPresenter) getPresenter()).getData("wuhan");
    }

    @Override
    protected IPresenter createPresenter() {
        return new WeatherPresenter();
    }


    @Override
    public void setData(Tranlation bean) {
        Log.e("gao", "setData: ");

    }

    @Override
    public void showLoading() {
        Log.e("gao", "showLoading: ");

    }

    @Override
    public void hideLoading() {
        Log.e("gao", "hideLoading: ");

    }
}
