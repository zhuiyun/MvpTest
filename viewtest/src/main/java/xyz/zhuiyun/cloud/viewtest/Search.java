package xyz.zhuiyun.cloud.viewtest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class Search extends View {
    boolean isFirst=true;
    PathMeasure pathMeasure=new PathMeasure();
    Path path=new Path();
    Path search=new Path();
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    float[] pos=new float[2];
    float x,y;
    float value=1;
    ValueAnimator valueAnimator;
    public Search(Context context) {
        this(context, null);
    }

    public Search(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Search(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        search.addArc(-60,-60,60,60,45,-359.9f);
        pathMeasure.setPath(search,true);
        pathMeasure.getPosTan(0,pos,null);
        search.lineTo(pos[0]+60,pos[1]+60);
        pathMeasure.setPath(search,true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        valueAnimator=ValueAnimator.ofFloat(1,0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value= (float) animation.getAnimatedValue();
                isFirst=false;
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                pathMeasure.nextContour();
                if(pathMeasure.getLength()>0){
                    valueAnimator.start();
                    invalidate();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(4000);
        valueAnimator.start();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x=w;
        y=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(x/2,y/2);
        canvas.drawColor(Color.BLUE);
        path.reset();
//        if(isFirst){
//            canvas.drawPath(search,paint);
//        }else{
            pathMeasure.getSegment(pathMeasure.getLength()*(1-value),pathMeasure.getLength(),path,true);
            canvas.drawPath(path,paint);
//        }


    }
}
