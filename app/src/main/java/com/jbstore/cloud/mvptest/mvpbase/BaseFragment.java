package com.jbstore.cloud.mvptest.mvpbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gwy on 2018/1/19.
 *
 * @author:zhuiyun
 */

public  abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;
    protected View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
    }

    protected abstract T createPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=initFragment(inflater);
        return view;
    }

    protected abstract View initFragment(LayoutInflater inflater);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentChildView(view);
    }

    protected abstract void initFragmentChildView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentData(savedInstanceState);
    }

    protected abstract void initFragmentData(Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
        if(null!=mPresenter){
            mPresenter.attach((V)this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null!=mPresenter){
            mPresenter.detachView();
        }
    }
}
