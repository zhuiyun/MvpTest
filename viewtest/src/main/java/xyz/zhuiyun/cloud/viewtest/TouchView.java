package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class TouchView extends View {

    Paint paint;
    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();

    }

    private void initPaint() {
        paint=new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("gao", "onTouchEvent: "+event.getRawX()+"----"+event.getRawY());
        Log.e("gao", "onTouchEvent: "+event.getX()+"-----"+event.getY());
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(200,500,paint);
        canvas.drawPoints(new float[]{200,400,200,300},paint);
        canvas.drawLine(300,200,300,600,paint);
        canvas.drawLines(new float[]{400,200,600,500},paint);
//        canvas.drawRect(100,100,400,500,paint);
//        canvas.drawRect(new Rect(100,100,400,500),paint);
        canvas.drawRect(new RectF(100,100,400,500),paint);

        canvas.drawRoundRect(new RectF(100,600,500,800),30,30,paint);
        canvas.drawOval(100,1000,500,1200,paint);
        canvas.drawArc(100,1300,500,1700,0,90,true,paint);
        canvas.drawOval(100,1800,500,2200,paint);
    }
}
