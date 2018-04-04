package com.jbstore.cloud.mvp.presenter;

import com.jbstore.cloud.mvp.view.IView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public  class IPresenter<T extends IView> {
    private T mView,mProxyView;

    public T getView() {

        return  mProxyView;
    }

    public void attachView(T view){
        this.mView= view;
        mProxyView = (T)Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(mView==null){
                    return null;
                }
                return method.invoke(mView,args);
            }
        });
//        mView=new WeakReference<T>(view);
    }

    public void detach(){
        mView=null;
        mProxyView=null;
    }







}
