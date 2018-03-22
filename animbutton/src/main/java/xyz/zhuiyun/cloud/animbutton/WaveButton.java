package xyz.zhuiyun.cloud.animbutton;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by gwy on 2018/3/19.
 *
 * @author:zhuiyun
 */

public class WaveButton extends Button {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float progress;
    int color = android.graphics.Color.RED;
    float radius;
    float x, y;
    ValueAnimator valueAnimator;
    float currentRadius;


    public WaveButton(Context context) {
        this(context, null);
    }

    public WaveButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);

        Log.e("gao", "init: "+radius);
        valueAnimator=ValueAnimator.ofFloat(0,1).setDuration(1000);
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress= (float) animation.getAnimatedValue();
                invalidate();
                Log.e("gao", "onAnimationUpdate: "+progress);
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()),getWidth()/2,getHeight()/2,paint);
        radius = (float) Math.hypot(getWidth(), getHeight());
//        canvas.drawColor(Color.BLUE);
        Log.e("gao", "onDraw: " + getWidth());

        canvas.drawCircle(x, y, radius*progress, paint);
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                Log.e("gao", "onTouchEvent: "+x);
                Log.e("gao", "onTouchEvent: "+y);
                currentRadius = 0f;
                startAnimation();
                return true;
        }
        return false;
    }


    public void startAnimation(){
        valueAnimator.start();
    }
}
