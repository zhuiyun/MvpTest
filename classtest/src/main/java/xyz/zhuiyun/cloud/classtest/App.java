package xyz.zhuiyun.cloud.classtest;

import android.app.Application;



/**
 * Created by gwy on 2018/3/29.
 *
 * @author:zhuiyun
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionCrashHandler.getInstance().init(this);
    }
}
