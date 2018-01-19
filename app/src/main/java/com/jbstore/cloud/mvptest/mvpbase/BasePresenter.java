package com.jbstore.cloud.mvptest.mvpbase;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * Created by gwy on 2018/1/19.
 *
 * @author:zhuiyun
 */

public abstract  class  BasePresenter<T> {

    //View接口类型的软引用
    protected Reference<T> mViewRef;

    //和view建立联系
    public void attach(T view){
        mViewRef=new SoftReference<T>(view);

    }

    protected  T getView(){
        return  mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef!=null&&mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
        }
    }




}
