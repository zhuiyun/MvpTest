package xyz.zhuiyun.cloud.classtest;

import android.util.Log;

/**
 * Created by gwy on 2018/3/21.
 *
 * @author:zhuiyun
 */

public class Test {
    int a=7;
    public void sat(){
        new Inner().say();


        int c = new Inner2().c;
        Log.e("gao", "sat: sat");
    }


    class Inner{
        int b=3;
        public void say(){

            Log.e("gao", "say: "+a);
            sat();
        }
    }

    static  class  Inner2{
        int c=8;
        public void run(){

            Log.e("gao", "run: ");
        }
    }
}
