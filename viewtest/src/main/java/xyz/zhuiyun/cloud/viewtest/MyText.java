package xyz.zhuiyun.cloud.viewtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.*;

/**
 * Created by gwy on 2018/3/19.
 *
 * @author:zhuiyun
 */

public class MyText extends android.widget.TextView {
    int textSize=100;
    int normalTextColor= Color.RED;
    int changeTextColor= Color.BLACK;
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint1=new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect=new Rect();
    float progress=0;
    Rect rect1=new Rect();
    ValueAnimator valueAnimator;
    String text="zhuiyun";
    public MyText(Context context) {
        this(context, null);
    }

    public MyText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setTextSize(textSize);
        paint.setColor(normalTextColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);
        paint1.setTextSize(textSize);
        paint1.setColor(changeTextColor);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setDither(true);
        valueAnimator=ValueAnimator.ofFloat(0,1).setDuration(5000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int middle= (int) (getWidth()*progress);
        Log.e("gao", "onDraw: "+middle);
//        canvas.drawText(text,0,text.length(),getMeasuredWidth()/2,getMeasuredHeight()/2,paint);
        drawLeft(canvas,middle);
        drawRight(canvas,middle);


    }

    private void drawRight(Canvas canvas, int middle) {
        drawText(canvas,middle,getWidth(),paint1);
    }

    private void drawText(Canvas canvas, int middle, int width, Paint paint1) {
        canvas.save(); // 截取绘制的内容，待会就只会绘制clipRect设置的参数部分
        canvas.clipRect(middle, 0, width, getHeight()); // 获取文字的范围
        Rect bounds = new Rect();
       paint.getTextBounds(text, 0, text.length(), bounds); // 获取文字的Metrics 用来计算基线
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt(); // 获取文字的宽高
        int fontTotalHeight = fontMetrics.bottom - fontMetrics.top; // 计算基线到中心点的位
        int offY = fontTotalHeight / 2 - fontMetrics.bottom; // 计算基线位置
        int baseline = (getMeasuredHeight() + fontTotalHeight) / 2 - offY;
        canvas.drawText(text, getMeasuredWidth() / 2 - bounds.width() / 2, baseline, paint1); // 释放画笔状态
        canvas.restore();



    }

    private void drawLeft(Canvas canvas, int middle) {
        drawText(canvas,0,middle,paint);
    }
}
