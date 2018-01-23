package com.jbstore.cloud.mvp.model;

import com.jbstore.cloud.mvp.net.RetrofitUtils;

/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public class WeatherModel {
    public void getWeather(String city){
        RetrofitUtils.getRequest().loadDataByRetrofitRxjava(city);
    }

}
