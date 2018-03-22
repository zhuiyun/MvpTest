package xyz.zhuiyun.cloud.classtest;

import android.util.Log;

/**
 * Created by gwy on 2018/3/21.
 *
 * @author:zhuiyun
 */

public class F {
    {
        Log.e("gao", "instance initializer: F代码块");

    }

    static {
        Log.e("gao", "static initializer: F代码块");
    }

    public F() {
        Log.e("gao", "F: F构造方法");
    }
}
