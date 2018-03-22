package xyz.zhuiyun.cloud.recyclerviewmodel;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gwy on 2018/3/20.
 *
 * @author:zhuiyun
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mView;
    public <T extends View> T getView(int viewId){
        View view=mView.get(viewId);
        if(view==null){
            view=itemView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return (T)view;
    }
    public ViewHolder(View itemView) {
        super(itemView);
        mView=new SparseArray<>();//用于缓存已找到的界面
    }

    public ViewHolder setText(int viewId,CharSequence text){
        TextView tv=getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageRes(int viewId,int resId){
        ImageView iv=getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public ViewHolder loadImagePath(int viewId,HolderImageLoader imageLoader){
        ImageView iv=getView(viewId);
        imageLoader.loadImage(iv,imageLoader.getmPath());
        return this;
    }

    public abstract static class HolderImageLoader{
        private String mPath;
        public HolderImageLoader(String path){
            this.mPath=path;
        }

        public abstract void loadImage(ImageView iv,String path);
        public String getmPath(){
            return mPath;
        }
    }
}
