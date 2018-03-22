package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by gwy on 2018/3/19.
 *
 * @author:zhuiyun
 */

public class ChangeTextView extends android.support.v7.widget.AppCompatTextView {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    int TextSize=200;
    int normalColor= Color.BLACK;
    int changeColor= Color.RED;
    String text="zhuiyun";
    int width,height;
    Rect rectF=new Rect();
    float progress=0.5f;

    public ChangeTextView(Context context) {
        this(context, null);
    }

    public ChangeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setDither(true);
        paint.setTextSize(TextSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.save();
        canvas.translate(width/2,height/2);
        paint.getTextBounds(text,0,text.length(),rectF);
        drawNormalText(canvas);
//        drawChangeText(canvas);
        canvas.restore();

    }

    private void drawChangeText(Canvas canvas) {
        paint.setColor(changeColor);
        canvas.drawText(text,0,text.length()*progress,paint);
        Log.e("gao", "drawChangeText: "+text.length()*progress);
    }

    private void drawNormalText(Canvas canvas) {
        paint.setColor(normalColor);

        canvas.drawText(text,(int)text.length()*progress,text.length(),paint);
        Log.e("gao", "drawNormalText: "+text.length()*progress);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;

    }



}
