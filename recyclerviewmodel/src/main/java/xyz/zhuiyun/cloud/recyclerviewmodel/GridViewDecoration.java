package xyz.zhuiyun.cloud.recyclerviewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by gwy on 2018/3/20.
 *
 * @author:zhuiyun
 */

public class GridViewDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDrawable;
    private Paint paint;

    public GridViewDecoration(Context mContext, int res) {
        mDrawable = ContextCompat.getDrawable(mContext, res);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);


//       parent.getLayoutManager().getRowCountForAccessibility(,state);
//        if(position%parent.getLayoutManager().get)
        int bottom=mDrawable.getIntrinsicHeight();
        int right=mDrawable.getIntrinsicWidth();
        if(isLastCloumn(view,parent)){
            right=0;
        }
        if(isLastRow(view,parent)){
            bottom=0;
        }

        outRect.right=right;
        outRect.bottom=bottom;
        Log.e("gao", "getItemOffsets: "+bottom);
//        if (position % 3 != 0) {
//            outRect.right = mDrawable.getIntrinsicWidth();
//            Log.e("gao", "getItemOffsets: " + position);
//        }
//        outRect.bottom = mDrawable.getIntrinsicHeight();
    }

    private boolean isLastRow(View view, RecyclerView parent) {
        int position= ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int count=0;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager= (GridLayoutManager) layoutManager;
            count=gridLayoutManager.getSpanCount();

        }else {
            count=1;
        }
        int rowNumber=parent.getAdapter().getItemCount()%count==0?(parent.getAdapter().getItemCount()/count):(parent.getAdapter().getItemCount()/count+1);

       return (position+1)>(rowNumber-1)*count;

    }

    private boolean isLastCloumn(View view, RecyclerView parent) {
        int position= ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int count=0;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager= (GridLayoutManager) layoutManager;
            count=gridLayoutManager.getSpanCount();
        }else {
            count=1;
        }

        if((position+1)%count!=0){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        drawHorizontal(c, parent, childCount);
        drawVertival(c, parent, childCount);
    }

    private void drawVertival(Canvas c, RecyclerView parent, int childCount) {
        for (int i = 0; i < childCount; i++) {

            View childView = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getRight() + params.rightMargin;
            int right = left + mDrawable.getIntrinsicWidth();
            int top = childView.getTop() - params.topMargin;
            int bottom = childView.getBottom() + params.bottomMargin;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent, int childCount) {
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            if(isLastRow(childView,parent)){
                return;
            }
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getLeft() - params.leftMargin;
            int right = childView.getRight() + mDrawable.getIntrinsicWidth() + params.rightMargin;
            int top = childView.getBottom() + params.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }

    }
}
