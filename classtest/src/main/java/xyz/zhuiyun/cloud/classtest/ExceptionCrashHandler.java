package xyz.zhuiyun.cloud.classtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;

/**
 * Created by gwy on 2018/3/29.
 *
 * @author:zhuiyun
 */

public class ExceptionCrashHandler implements  Thread.UncaughtExceptionHandler{
    static ExceptionCrashHandler crashHandler;
    Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
    public static ExceptionCrashHandler getInstance(){
       if(crashHandler==null){
           synchronized (ExceptionCrashHandler.class){
            if(crashHandler==null){
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

      defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
   };

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("gao", "uncaughtException: 报错");
        Log.e("gao", "uncaughtException: "+e.toString());
        Log.e("gao", "uncaughtException: "+e.getLocalizedMessage());
//        String crashFileName=saveInfoToSd(e);
//        cacheCrashFile(crashFileName);
        try {
            getMobileInfo();
        } catch (Exception e1) {
            e1.printStackTrace();
            Log.e("gao", "uncaughtException: "+e1.toString());
        }

        StringWriter stringWriter=new StringWriter();
        PrintWriter printWriter=new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        printWriter.close();
        Log.e("gao", "uncaughtException: "+stringWriter.toString());
        defaultUncaughtExceptionHandler.uncaughtException(t,e);//系统默认继续处理

    }

    private void getMobileInfo() throws Exception {
        Class<?> clz= Build.class;
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field field:declaredFields
             ) {
            field.setAccessible(true);
            Log.e("gao", "getMobileInfo: "+field.getName());
            Log.e("gao", "getMobileInfo: "+field.get(null).toString());
        }
    }

    private String saveInfoToSd(Throwable e) {

        return  "";
    }

    private void cacheCrashFile(String crashFileName) {
        SharedPreferences sp=mContext.getSharedPreferences("crash",Context.MODE_PRIVATE);
        sp.edit().putString("CRASH_FILE_NAME",crashFileName).commit();
    }
}
