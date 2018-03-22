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

public class PathMeasureView extends View {
    Paint paint;
    Path path;
    PathMeasure pathMeasure;
    ValueAnimator valueAnimator;
    float value=0;
    public PathMeasureView(Context context) {
        this(context, null);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        startAnim();
    }

    private void init() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        path=new Path();
        pathMeasure=new PathMeasure();
        pathMeasure.setPath(path,false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.addCircle(400,400,300, Path.Direction.CCW);
        Path path1=new Path();
        pathMeasure.setPath(path,false);
        pathMeasure.getSegment(0,pathMeasure.getLength()*value,path1,false);
        canvas.drawPath(path1,paint);


    }

    private void startAnim(){
        valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//               pathMeasure.getPosTan(animation.getAnimatedValue(), );
                value= (float) animation.getAnimatedValue();
                invalidate();
                Log.e("gao", "onAnimationUpdate: "+animation.getAnimatedValue().toString());
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setDuration(4000);
        valueAnimator.start();
    }

}
