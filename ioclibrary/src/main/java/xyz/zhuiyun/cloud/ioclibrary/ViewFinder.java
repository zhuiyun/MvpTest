package xyz.zhuiyun.cloud.ioclibrary;

import android.app.Activity;
import android.view.View;

/**
 * Created by gwy on 2018/3/23.
 *
 * @author:zhuiyun
 * view的findviewbyid的辅助类
 */

public class ViewFinder {
    private Activity mActivity;
    private View mView;
    public ViewFinder(Activity mActivity) {
        this.mActivity=mActivity;
    }

    public ViewFinder(View view) {
        this.mView=view;
    }

    public View findViewById(int viewId){
        return mActivity!=null?mActivity.findViewById(viewId):mView.findViewById(viewId);
    }
}
