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
import android.util.Log;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class SearchAnim extends View implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    Path tempPath=new Path();//绘制时保存path线段的path
    PathMeasure pathMeasure=new PathMeasure();
    float radius=60;//搜索图标的圆圈半径
    Path seach=new Path();//绘制搜索图标的path
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);//画笔
    float[] pos=new float[2];//搜索图标中的圆与线相交的点
    float width,height;
    float value;//绘制进度
    ValueAnimator valueAnimator;//控制绘制进度的属性动画
    ValueAnimator valueAnimator1;//控制绘制进度的属性动画
    boolean hide=false;
    public SearchAnim(Context context) {
        this(context, null);
    }

    public SearchAnim(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchAnim(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        seach.addArc(-radius,-radius,radius,radius,45,359.99f);//画圆
        pathMeasure.setPath(seach,true);//把path加入到pathmeasure中,拿到起点
        pathMeasure.getPosTan(0,pos,null);
        seach.lineTo(pos[0]+radius,pos[1]+radius);//绘制手柄
        pathMeasure.setPath(seach,true);
        valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.addListener(this);
        valueAnimator.addUpdateListener(this);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setDuration(4000);
        valueAnimator.start();
        valueAnimator1=ValueAnimator.ofFloat(1,0);

        valueAnimator1.addUpdateListener(this);
        valueAnimator1.setRepeatCount(0);
        valueAnimator1.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator1.setDuration(4000);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width/2,height/2);
        tempPath.reset();
        if(hide){
            Log.e("gao", "onDraw: "+hide);
            pathMeasure.getSegment(pathMeasure.getLength()*(1-value),pathMeasure.getLength(),tempPath,true);
        }else{
            Log.e("gao", "onDraw: ");
            pathMeasure.getSegment(0,pathMeasure.getLength()*value,tempPath,true);
        }

        canvas.drawPath(tempPath,paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        value= (float) animation.getAnimatedValue();
        Log.e("gao", "onAnimationUpdate: "+value);
        invalidate();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

      if(!hide){
          hide=true;
          value=0;
          valueAnimator1.start();
      }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
