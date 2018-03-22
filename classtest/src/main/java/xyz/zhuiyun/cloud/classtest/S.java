package xyz.zhuiyun.cloud.classtest;

import android.util.Log;

/**
 * Created by gwy on 2018/3/21.
 *
 * @author:zhuiyun
 */

public class S extends F {
    static {
        Log.e("gao", "static initializer: S静态代码块");
    }
    {
        Log.e("gao", "instance initializer: S代码块");
    }

    public S() {
        Log.e("gao", "S: S构造方法");
    }
}
