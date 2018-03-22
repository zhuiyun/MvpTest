package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class CanvasView extends View {
    Paint linePaint = new Paint();
    Paint mPaint = new Paint();
    int weight, hight;


    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();
    }

    private void initPaint() {
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.RED);
        linePaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        weight = w;
        hight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(weight/2,0,weight/2,hight,linePaint);
        canvas.drawLine(0,hight/2,weight,hight/2,linePaint);
        canvas.translate(weight/2,hight/2);
        RectF rect = new RectF(0,-400,400,0);
        canvas.drawRect(rect,mPaint);
        canvas.scale(-0.5f,-0.5f);
        canvas.drawRect(rect,mPaint);
        RectF rect1 = new RectF(-400,-400,400,400);   // 矩形区域

        for (int i=0; i<=20; i++)
        {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect1,mPaint);
        }
    }
}
