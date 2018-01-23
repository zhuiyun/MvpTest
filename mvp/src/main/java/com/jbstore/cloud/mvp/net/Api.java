package com.jbstore.cloud.mvp.net;

import com.google.gson.JsonObject;
import com.jbstore.cloud.mvp.bean.WeatherBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public interface Api {
    //baseUrl
    String API_SERVER_URL = "http://apistore.baidu.com/microservice/";

    //加载天气
    @GET("weather")
    Observable<WeatherBean> loadDataByRetrofitRxjava(@Query("citypinyin") String cityId);

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hi%20world")
    Observable<JsonObject> getCall();


}
