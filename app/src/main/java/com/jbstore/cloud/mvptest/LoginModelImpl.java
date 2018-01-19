package com.jbstore.cloud.mvptest;

/**
 * Created by gwy on 2018/1/19.
 *
 * @author:zhuiyun
 */

public class LoginModelImpl implements LoginContract.LoginModel {
    @Override
    public void login(String name, String password, LoginContract.LoginCallBack callBack) {
        callBack.onFail("密码错误");
    }
}
