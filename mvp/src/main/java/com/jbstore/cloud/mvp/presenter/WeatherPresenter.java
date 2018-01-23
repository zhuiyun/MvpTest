package com.jbstore.cloud.mvp.presenter;

import android.util.Log;

import com.google.gson.JsonObject;
import com.jbstore.cloud.mvp.model.WeatherModel;
import com.jbstore.cloud.mvp.view.WeatherView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public class WeatherPresenter extends IPresenter<WeatherView> {
    interface RequestListener {
        void onSuccess();
    }


    WeatherModel model;

    public WeatherPresenter() {
        model = new WeatherModel();

    }

    public void getData(String city) {
       model.getWeather(new Observer<JsonObject>() {
           @Override
           public void onSubscribe(Disposable d) {
               Log.e("gao", "onSubscribe: ");
           }

           @Override
           public void onNext(JsonObject value) {
               Log.e("gao", "onNext: "+value);
           }

           @Override
           public void onError(Throwable e) {
               Log.e("gao", "onError: "+e.toString());
           }

           @Override
           public void onComplete() {
               Log.e("gao", "onComplete: ");
           }
       },city);
    }


}
