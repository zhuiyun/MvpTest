package com.jbstore.cloud.mvp.presenter;

import com.jbstore.cloud.mvp.view.IView;

import java.lang.ref.WeakReference;

/**
 * Created by gwy on 2018/1/23.
 *
 * @author:zhuiyun
 */

public abstract class IPresenter<T extends IView> {
    private WeakReference<T> mView;

    public T getView() {
        if(mView!=null){
            return mView.get();
        }
        return null;
    }

    public void attachView(T view){
        mView=new WeakReference<T>(view);
    }

    public void detach(){
        mView=null;
        mView.clear();
    }







}
