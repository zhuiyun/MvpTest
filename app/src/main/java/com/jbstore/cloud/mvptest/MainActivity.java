package com.jbstore.cloud.mvptest;

import android.os.Bundle;
import android.util.Log;

import com.jbstore.cloud.mvptest.mvpbase.BaseActivity;

public class MainActivity extends BaseActivity<LoginContract.LoginView,LoginPresenter> implements LoginContract.LoginView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkFormat("gao","wen");
    }

    @Override
    protected void initActivityView(Bundle savedInstanceState) {

    }

    @Override
    protected void findViewById() {

    }

    @Override
    public void onCheckFormatSuccess() {

    }

    @Override
    public void onCheckFormatFail(String info) {

    }

    @Override
    public void onLoginFail(String errorInfo) {
        Log.e("gao", "onLoginFail: "+errorInfo);

    }

    @Override
    public void onLoginSuccess() {
        Log.e("gao", "onLoginSuccess: ");

    }
}
