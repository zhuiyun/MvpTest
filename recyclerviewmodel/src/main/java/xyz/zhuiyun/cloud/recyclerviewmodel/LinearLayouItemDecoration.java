package xyz.zhuiyun.cloud.recyclerviewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by gwy on 2018/3/20.
 *
 * @author:zhuiyun
 */

public class LinearLayouItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDrawable;
    private Paint paint;

    public LinearLayouItemDecoration(Context mContext, int res) {
        mDrawable = ContextCompat.getDrawable(mContext, res);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position != 0) {
            outRect.top = mDrawable.getIntrinsicHeight();
        }


    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        Rect rect = new Rect();
        Rect rect1 = new Rect();
        rect.left = parent.getPaddingLeft();
        rect.right = parent.getWidth() - parent.getPaddingRight();
//        rect1.left=0;
//        rect1.right=50;

        for (int i = 0; i < childCount - 1; i++) {
            rect.bottom = parent.getChildAt(i).getTop();
            rect.top = parent.getBottom() - mDrawable.getIntrinsicHeight();
//            rect1.top=parent.getChildAt(i).getTop();
            Log.e("gao", "onDraw: " + rect1.top);
//            rect1.bottom=parent.getChildAt(i).getBottom();
            Log.e("gao", "onDraw: " + rect1.bottom);
            mDrawable.setBounds(rect);
            mDrawable.draw(c);
//            c.drawRect(rect1,paint);
        }
    }
}
