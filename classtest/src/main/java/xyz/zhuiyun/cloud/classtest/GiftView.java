package xyz.zhuiyun.cloud.classtest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by gwy on 2018/3/23.
 *
 * @author:zhuiyun
 */

public class GiftView extends RelativeLayout {
    float mWidth,mHeight;
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Drawable drawable;
    Random random=new Random();

    public GiftView(Context context) {
        this(context, null);
    }

    public GiftView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GiftView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void add() {
        Log.e("gao", "add1: ");
        drawable=getResources().getDrawable(R.mipmap.ic_launcher);
        final ImageView imageView=new ImageView(getContext());
        imageView.setImageDrawable(drawable);
        RelativeLayout.LayoutParams params=new LayoutParams(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        params.addRule(CENTER_HORIZONTAL, TRUE);
        // 父容器的底部
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);
        addView(imageView,params);
        Log.e("gao", "add: 2");
        PointF point0=new PointF(mWidth/2-drawable.getIntrinsicWidth()/2,mHeight-drawable.getIntrinsicHeight());
        PointF point1=new PointF(random.nextInt((int) (mWidth-drawable.getIntrinsicWidth())),random.nextInt((int) (mHeight/2)));
        PointF point2=new PointF(random.nextInt((int) (mWidth-drawable.getIntrinsicWidth())),random.nextInt((int) (mHeight/2))+mHeight/2);
        PointF point3=new PointF(random.nextInt((int) (mWidth-drawable.getIntrinsicWidth())),0);
        Log.e("gao", "add: 3");
        MyTypeEvaluator typeEvaluator=new MyTypeEvaluator(point1,point2);
        ValueAnimator valueAnimator=ValueAnimator.ofObject(typeEvaluator,point0,point3);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("gao", "onAnimationUpdate: "+animation.getAnimatedValue());
                PointF pointF= (PointF) animation.getAnimatedValue();
                Log.e("gao", "onAnimationUpdate: "+pointF.y);
                Log.e("gao", "onAnimationUpdate: "+pointF.x);
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setDuration(4000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
        Log.e("gao", "add: ");

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
        mWidth=MeasureSpec.getSize(widthMeasureSpec);
        mHeight=MeasureSpec.getSize(heightMeasureSpec);
        Log.e("gao", "onMeasure: "+getWidth());
        Log.e("gao", "onMeasure: "+getHeight());

    }
}
