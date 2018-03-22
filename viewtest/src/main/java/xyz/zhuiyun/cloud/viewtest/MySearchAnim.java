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
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class MySearchAnim extends View {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Path circle_path=new Path();
    Path circle_path1=new Path();
    float value;
    Path tempPath=new Path();
    float width,height;
    float radius=60;
    ValueAnimator endAnimator;
    PathMeasure pathMeasure=new PathMeasure();
    public MySearchAnim(Context context) {
        this(context, null);
    }

    public MySearchAnim(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySearchAnim(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        for (int i = 0; i < 1; i++) {
            RectF rect=new RectF(-radius+i*40,-radius+i*40,radius-i*40,radius-i*40);
            circle_path.addArc(rect,45f,-359.9f);
        }
        float[] pos=new float[2];

        Log.e("gao", "init: "+pathMeasure.getLength());

        pathMeasure.setPath(circle_path,false);
        pathMeasure.getPosTan(0,pos,null);
        circle_path.lineTo(pos[0]+radius,pos[1]+radius);
        Log.e("gao", "init: "+pos[0]+"---"+pos[1]);
        Log.e("gao", "init: "+pathMeasure.getLength());
        pathMeasure.setPath(circle_path,false);
        startAnim();

    }
    private  void startAnim(){
//        endAnimator=ValueAnimator.ofFloat(1,0);
//        endAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                value= (float) animation.getAnimatedValue();
//                Log.e("gao", "endonAnimationUpdate: "+value);
//                invalidate();
//            }
////        });
//        endAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        endAnimator.setDuration(4000);
//        endAnimator.setRepeatCount(0);

        final ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(4000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("gao", "onAnimationStart: ");

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("gao", "onAnimationEnd: ");

                pathMeasure.nextContour();
                if(pathMeasure.getLength()>0){
                    value=0;
                    valueAnimator.start();
                    Log.e("gao", "onAnimationEnd: "+pathMeasure.getLength());
                    invalidate();
                }else{

                    pathMeasure.setPath(circle_path,false);
                    isRe=true;
                    value=0;
                    valueAnimator.start();

                    Log.e("gao", "1111onAnimationEnd: "+pathMeasure.getLength());
//                    invalidate();
                }


            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e("gao", "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                circle_path.reset();
                Log.e("gao", "onAnimationRepeat: ");

            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    boolean isRe=false;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tempPath.reset();
        canvas.drawColor(Color.BLUE);
        canvas.translate(width/2,height/2);
//        pathMeasure.setPath(circle_path,false);
        if(isRe){
            Log.e("gao", "onDraw: "+value);
            pathMeasure.getSegment(pathMeasure.getLength()*(1-value),pathMeasure.getLength(),tempPath,true);
        }else{
            Log.e("gao", "onDraw: 1111");
            pathMeasure.getSegment(0,pathMeasure.getLength()*value,tempPath,true);
        }
        canvas.drawPath(tempPath,paint);
//        canvas.drawPath(circle_path,paint);
    }
}
