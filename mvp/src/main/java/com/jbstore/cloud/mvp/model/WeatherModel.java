package com.jbstore.cloud.mvp.model;

import com.google.gson.JsonObject;
import com.jbstore.cloud.mvp.net.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public class WeatherModel {
    public void getWeather(Observer<JsonObject> observer, String city){
        RetrofitUtils.getRequest().getCall().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(observer);

    }

}
