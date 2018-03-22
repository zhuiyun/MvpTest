package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class SkewView extends View {
    Paint paint;
    int width,height;
    Paint linePaint;
    public SkewView(Context context) {
        this(context, null);
    }

    public SkewView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkewView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();

    }

    private void initPaint() {
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        linePaint=new Paint();
        linePaint.setStrokeWidth(5);
        linePaint.setColor(Color.RED);
        linePaint.setStyle(Paint.Style.STROKE);
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
        canvas.drawLine(width/2,0,width/2,height,linePaint);
        canvas.drawLine(0,height/2,width,height/2,linePaint);
        canvas.translate(width/2,height/2);
        Rect rect=new Rect(0,-400,400,0);
        canvas.drawRect(rect,paint);
        canvas.skew(1,0);                       // 水平错切
        canvas.skew(0,1);
        canvas.drawRect(rect,paint);

    }
}
