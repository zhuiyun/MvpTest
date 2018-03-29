package xyz.zhuiyun.cloud.ioclibrary;

import android.content.Context;
import android.util.Log;

/**
 * Created by gwy on 2018/3/29.
 *
 * @author:zhuiyun
 */

public class ExceptionCrashHandler implements  Thread.UncaughtExceptionHandler{
    static ExceptionCrashHandler crashHandler;
    Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    public static ExceptionCrashHandler getInstance(){
       if(crashHandler==null){
           synchronized (ExceptionCrashHandler.class){
            if(crashHandler!=null){
                crashHandler=new ExceptionCrashHandler();
            }
           }
       }
       return crashHandler;
   }

   Context mContext;
   public void init(Context mContext){
       this.mContext=mContext;
       Thread.currentThread().setUncaughtExceptionHandler(this);
      uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
   };

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("gao", "uncaughtException: "+e.toString());
        uncaughtExceptionHandler.uncaughtException(t,e);//系统默认继续处理

    }
}
