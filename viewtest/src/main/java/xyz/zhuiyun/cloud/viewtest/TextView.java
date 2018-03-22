package xyz.zhuiyun.cloud.viewtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class TextView extends View {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint1=new Paint(Paint.ANTI_ALIAS_FLAG);
    String text="gaowenyun";
    float progress=0.6f;
    Rect rect=new Rect();
    ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1).setDuration(5000);
    public TextView(Context context) {
        this(context, null);
    }

    public TextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setTextSize(100);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(5);
        paint1.setTextSize(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawText("dfsggs",594,1037,paint);
        paint.getTextBounds(text,0,text.length(),rect);
        Log.e("gao", "onDraw: "+getWidth()*progress);
        int index= (int) (getWidth()*progress);
        drawLeft(canvas,index);
        drawRight(canvas,index);
    }

    private void drawRight(Canvas canvas, int index) {
        drawText(canvas,index,getWidth(),paint1);
    }

    private void drawLeft(Canvas canvas, int index) {
        drawText(canvas,0,index,paint);
    }

    private void drawText(Canvas canvas, int start, int end, Paint paint) {
        canvas.save();
        canvas.clipRect(start,0,end,getHeight());

        canvas.drawText(text,getMeasuredWidth()/2-rect.width()/2,getMeasuredHeight()/2-rect.height()/2,paint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("gao", event.getX()+"onTouchEvent: "+event.getY());
        return super.onTouchEvent(event);
    }
}
