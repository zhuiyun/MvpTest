package com.jbstore.cloud.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jbstore.cloud.mvp.bean.Tranlation;
import com.jbstore.cloud.mvp.presenter.WeatherPresenter;
import com.jbstore.cloud.mvp.view.WeatherView;

public class MainActivity extends AppCompatActivity implements WeatherView {

WeatherPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new WeatherPresenter();
        presenter.attachView(this);
        presenter.getData("wuhan");
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
