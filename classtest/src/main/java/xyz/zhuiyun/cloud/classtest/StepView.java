package xyz.zhuiyun.cloud.classtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by gwy on 2018/3/21.
 *
 * @author:zhuiyun
 */

public class StepView extends View {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    int radius=200;
    int color= Color.RED;
    Rect rect=new Rect();
    int width,height;
    String text="12222";
    Paint textPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    ValueAnimator valueAnimator;
    int step;
    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setColor(color);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(100);
        valueAnimator=ValueAnimator.ofInt(0,10000).setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                step= (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("gao", "onDraw: "+getMeasuredWidth());
        Log.e("gao", "onDraw: "+getMeasuredHeight());
        Log.e("gao", "onDraw: "+getWidth());
        Log.e("gao", "onDraw: "+getHeight());
//        canvas.translate(getWidth()/2,getHeight()/2);
//        RectF rect=new RectF(-width/2+20,-height/2+20,width/2-20,height/2-20);
        RectF rectF=new RectF(20,20,width-20,height-20);
        canvas.drawArc(rectF,135,270*step/10000,false,paint);
        Rect rect=new Rect();
        textPaint.getTextBounds(step+"",0,(step+"").length(),rect);
        int x=(width-40)/2-rect.width()/2;
        Paint.FontMetricsInt metricsInt=textPaint.getFontMetricsInt();
        int baseLine=(metricsInt.bottom-metricsInt.top)/2-metricsInt.bottom;
        int y=height/2+baseLine;
        canvas.drawText(step+"",x,y,textPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
       width=MeasureSpec.getSize(widthMeasureSpec);
        height=MeasureSpec.getSize(heightMeasureSpec);

        if(width<height){
            height=width;
        }

        if(width>height){
            width=height;
        }
        Log.e("gao", "onMeasure: "+width);
        Log.e("gao", "onMeasure: "+height);
        setMeasuredDimension(width,height);



    }
}
