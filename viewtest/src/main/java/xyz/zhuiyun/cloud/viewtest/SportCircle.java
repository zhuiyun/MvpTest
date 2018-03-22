package xyz.zhuiyun.cloud.viewtest;

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

public class SportCircle extends View {
    PathMeasure pathMeasure=new PathMeasure();
    Path path=new Path();
    Path tempPath=new Path();
    Paint paint=new Paint();
    ValueAnimator valueAnimator;
    float value;
    public SportCircle(Context context) {
        this(context, null);
    }

    public SportCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SportCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        path.addArc(-300,-300,300,300,0,359);
        pathMeasure.setPath(path,true);
        valueAnimator=ValueAnimator.ofFloat(1,0);
        valueAnimator.setDuration(4000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(4);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value= (float) animation.getAnimatedValue();
                Log.e("gao", "onAnimationUpdate: "+value);
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tempPath.reset();
        canvas.translate(600,600);
        pathMeasure.getSegment(pathMeasure.getLength()*(1-value),pathMeasure.getLength(),tempPath,true);
        canvas.drawPath(tempPath,paint);

    }
}
