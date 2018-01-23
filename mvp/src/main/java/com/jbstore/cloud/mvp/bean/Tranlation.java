package com.jbstore.cloud.mvp.bean;

import android.util.Log;

/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public class Tranlation {
    private int status;

    public content content;
    public static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

    //定义 输出返回数据 的方法
    public void show() {
        Log.d("gao", content.out );
    }
}
