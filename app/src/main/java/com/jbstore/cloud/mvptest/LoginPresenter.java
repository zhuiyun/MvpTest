package com.jbstore.cloud.mvptest;

import com.jbstore.cloud.mvptest.mvpbase.BasePresenter;

/**
 * Created by gwy on 2018/1/19.
 *
 * @author:zhuiyun
 */

public class LoginPresenter extends BasePresenter<LoginContract.LoginView> {
    private  LoginModelImpl model;
    public LoginPresenter(){
        model=new LoginModelImpl();
    }

    public void checkFormat(String name,String pwd){
        model.login(name, pwd, new LoginContract.LoginCallBack() {
            @Override
            public void onSuccess() {
                getView().onLoginSuccess();
            }

            @Override
            public void onFail(String errorInfo) {
                getView().onLoginFail(errorInfo);
            }
        });
    }


}
