package com.jbstore.cloud.mvp.presenter;

import android.util.Log;

import com.google.gson.JsonObject;
import com.jbstore.cloud.mvp.model.WeatherModel;
import com.jbstore.cloud.mvp.net.RetrofitUtils;
import com.jbstore.cloud.mvp.view.WeatherView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        RetrofitUtils.getRequest().getCall().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {
                ((WeatherView) getView()).showLoading();
            }

            @Override
            public void onNext(JsonObject value) {
                Log.e("gao", "onNext: "+value);
//                getView().setData(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("gao", "onError: "+e.toString());

            }

            @Override
            public void onComplete() {
                getView().hideLoading();
            }
        });
    }


}
