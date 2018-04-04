package com.jbstore.cloud.mvp.presenter;

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

           }

           @Override
           public void onNext(JsonObject value) {
               if(getView()!=null){

               }
           }

           @Override
           public void onError(Throwable e) {

           }

           @Override
           public void onComplete() {

           }
       },city);
    }


}
