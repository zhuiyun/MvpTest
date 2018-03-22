package xyz.zhuiyun.cloud.recyclerviewmodel;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gwy on 2018/3/20.
 *
 * @author:zhuiyun
 */

public class WrapRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter adapter;
    SparseArray<View> headers = new SparseArray<>();
    SparseArray<View> footers = new SparseArray<>();
    private static int BASE_HEADER_KEY = 0;
    private static int BASE_FOOTER_KEY = 0;

    public void addHeaders(View header) {
        if (headers.indexOfValue(header) == -1) {
            headers.put(BASE_HEADER_KEY++, header);
        }
    }

    public void addFoooter(View footer) {
        if (footers.indexOfValue(footer) == -1) {
            footers.put(BASE_FOOTER_KEY--, footer);
        }
    }
    public void removeHeaderView(View header){
        if(headers.indexOfValue(header)>=0){
            headers.removeAt(headers.indexOfValue(header));
        }
        notifyDataSetChanged();
    }
    public void removeFooterView(View footer){
        if(footers.indexOfValue(footer)>=0){
            footers.removeAt(footers.indexOfValue(footer));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int numHeaders=headers.size();
        if (position<numHeaders){
            return headers.keyAt(position);
        }

        final  int adjPosition=position-numHeaders;
        int adapterCount=0;
        if(adapter!=null){
            adapterCount=adapter.getItemCount();
            if(adjPosition<adapterCount){
                return adapter.getItemViewType(adjPosition);
            }
        }

        int footPosition=adjPosition-adapterCount;
        return footers.keyAt(position);
    }

    public WrapRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(headers.indexOfKey(viewType)>=0){
            return createHeaderFooter(headers.indexOfKey(viewType));
        }else if(footers.indexOfKey(viewType)>=0){
            return createHeaderFooter(footers.indexOfKey(viewType));
        }
        return adapter.onCreateViewHolder(parent,viewType);
    }

    private RecyclerView.ViewHolder createHeaderFooter(int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int numHeaders=headers.size();
        if(position<numHeaders){
            return;
        }
        final  int adjPosition=position-numHeaders;
        int adapterCount=0;
        if(adapter!=null){
            adapterCount=adapter.getItemCount();
            if(adjPosition<adapterCount){
                adapter.onBindViewHolder(holder,position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount()+headers.size()+footers.size();
    }
}
