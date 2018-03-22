package xyz.zhuiyun.cloud.recyclerviewmodel;

import android.content.Context;

import java.util.List;

/**
 * Created by gwy on 2018/3/20.
 *
 * @author:zhuiyun
 */

public class MyAdapter extends RecyclerViewCommonAdapter<String>{


    public MyAdapter(int mLayoutId, List<String> mData, Context mContext) {
        super(mLayoutId, mData, mContext);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv,s);
    }
}
