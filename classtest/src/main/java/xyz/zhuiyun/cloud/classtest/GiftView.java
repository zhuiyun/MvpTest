package xyz.zhuiyun.cloud.classtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by gwy on 2018/3/23.
 *
 * @author:zhuiyun
 */

public class GiftView extends RelativeLayout {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Drawable drawable;
    int width,height;
    public GiftView(Context context) {
        this(context, null);
    }

    public GiftView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GiftView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        drawable=getResources().getDrawable(R.mipmap.ic_launcher);
        ImageView imageView=new ImageView(context);
        imageView.setImageDrawable(drawable);
        RelativeLayout.LayoutParams params=new LayoutParams(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        params.addRule(CENTER_HORIZONTAL, TRUE);
        // 父容器的底部
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        addView(imageView,params);
        Log.e("gao", "init: "+getHeight());

//        Log.e("gao", "init: "+);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("gao", "onDraw: "+getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("gao", "onMeasure: "+getWidth());
        Log.e("gao", "onMeasure: "+getHeight());

    }
}
