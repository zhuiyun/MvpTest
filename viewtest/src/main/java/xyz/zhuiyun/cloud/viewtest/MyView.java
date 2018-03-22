package xyz.zhuiyun.cloud.viewtest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by gwy on 2018/3/16.
 *
 * @author:zhuiyun
 */

public class MyView extends View {
    float value;
    Path path_temp=new Path();
    Path search=new Path();
    PathMeasure pathMeasure=new PathMeasure();
    float[] pos=new float[2];
    float width,height;
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    float radius=50;
    ValueAnimator startAnimator;
    boolean isEnd=false;
    ValueAnimator endAnimator;
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        RectF rectF=new RectF(-radius,-radius,radius,radius);
        search.addArc(rectF,45,359.9f);
        pathMeasure.setPath(search,false);
        pathMeasure.getPosTan(0,pos,null);
        search.lineTo(pos[0]+radius,pos[1]+radius);
        pathMeasure.setPath(search,false);
        startAnimator=ValueAnimator.ofFloat(0,1).setDuration(5000);
        startAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        startAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isEnd){
                    isEnd=true;
                    endAnimator.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        startAnimator.start();
        endAnimator=ValueAnimator.ofFloat(1,0).setDuration(5000);
        endAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value= (float) animation.getAnimatedValue();
                Log.e("gao", "onAnimationUpdate: "+value);
                invalidate();
            }
        });
        endAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(isEnd){
                    isEnd=false;
                    startAnimator.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.translate(width/2,height/2);
        path_temp.reset();

        if(isEnd){
            Log.e("gao", "onDraw: ");
            pathMeasure.getSegment(pathMeasure.getLength()*(1-value),pathMeasure.getLength(),path_temp,true);
        }else{
            Log.e("gao", "onDraw: 11111");
            pathMeasure.getSegment(0,pathMeasure.getLength()*value,path_temp,true);

        }
        canvas.drawPath(path_temp,paint);
    }
}
