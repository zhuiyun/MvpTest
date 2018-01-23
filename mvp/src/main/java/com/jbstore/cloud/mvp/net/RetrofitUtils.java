package com.jbstore.cloud.mvp.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public class RetrofitUtils {
    public static Api getRequest(){
        Retrofit retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("http://fy.iciba.com/").build();
        Api api=retrofit.create(Api.class);

        return api;
    }



}
