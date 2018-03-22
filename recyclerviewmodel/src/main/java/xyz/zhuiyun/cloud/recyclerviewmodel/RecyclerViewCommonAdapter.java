package xyz.zhuiyun.cloud.recyclerviewmodel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by gwy on 2018/3/20.
 *
 * @author:zhuiyun
 */

public abstract class RecyclerViewCommonAdapter<DATA> extends RecyclerView.Adapter<ViewHolder> {
    private int mLayoutId;//布局id
    private List<DATA> mData;
    private  LayoutInflater layoutInflater;
    private MulitiTypeSupport mulitiTypeSupport;

    public RecyclerViewCommonAdapter(int mLayoutId, List<DATA> mData, Context mContext) {
        this.mLayoutId = mLayoutId;
        this.mData = mData;
        this.layoutInflater=LayoutInflater.from(mContext);

    }

    @Override
    public int getItemViewType(int position) {
        if(mulitiTypeSupport!=null){
            return mulitiTypeSupport.getLayoutId(mData.get(position));
        }
        return super.getItemViewType(position);
    }

    public RecyclerViewCommonAdapter(List<DATA> mData, Context mContext, MulitiTypeSupport mulitiTypeSupport) {
        this(-1,mData,mContext);

        this.mulitiTypeSupport=mulitiTypeSupport;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mulitiTypeSupport!=null){
            mLayoutId=viewType;
        }
        View view= layoutInflater.inflate(mLayoutId,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder,mData.get(position),position);
    }

    protected abstract void convert(ViewHolder holder, DATA data, int position);


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
