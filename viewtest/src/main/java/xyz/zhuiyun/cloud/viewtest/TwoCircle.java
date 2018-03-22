package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class TwoCircle extends View {
    int mWeight,mHeight;
    Paint paint;
    public TwoCircle(Context context) {
        this(context, null);
    }

    public TwoCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();

    }

    private void initPaint() {
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight=h;
        mWeight=w;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWeight/2,mHeight/2);
        canvas.drawCircle(0,0,400,paint);
        canvas.drawCircle(0,0,450,paint);
        for (int i = 0; i < 36; i++) {
            canvas.drawLine(0,400,0,450,paint);
            canvas.rotate(10);
        }
    }
}
