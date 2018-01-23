package com.jbstore.cloud.mvptest;

/**
 * Created by gwy on 2018/1/19.
 *
 * @author:zhuiyun
 */

public class LoginContract {
    public interface  LoginView{
        void onCheckFormatSuccess();
        void onCheckFormatFail(String info);
        void onLoginFail(String errorInfo);
        void onLoginSuccess();
    }

    public interface  LoginModel{
        void login(String name,String password,LoginCallBack callBack);
        void saveData();
    }

    public interface  LoginCallBack{
        void onSuccess();
        void onFail(String errorInfo);
    }




}
