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
 * Created by gwy on 2018/3/19.
 *
 * @author:zhuiyun
 */

public class LoveView extends View{
    private static final float C = 0.551915024494f;
    private long duaration=5000;
    private int selectColor= Color.RED;

    private float strokeWidth=10;
    private Paint paint;
    private Path path;
    boolean isSeclect;
    private float width, height, radius=0;

    private float tempR;
    float progress;
    PathMeasure pathMeasure=new PathMeasure();
    Path temp_Path=new Path();
    ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1).setDuration(duaration);

    public LoveView(Context context) {
        this(context, null);
    }

    public LoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setClickable(true);
        init();
    }

    private void init() {
        radius=150;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(selectColor);
        paint.setStrokeWidth(strokeWidth);
        path.moveTo(0, -radius + radius / 3 * 2);
        path.cubicTo(-radius * C, -radius, -radius, -radius * C, -radius, 0);
        path.cubicTo(-radius, radius * C, -radius * C + radius / 3 * 2, radius, 0, radius);
        path.cubicTo(radius * C - radius / 3 * 2, radius, radius, radius * C, radius, 0);
        path.cubicTo(radius, -radius * C, radius * C, -radius, 0, -radius + radius / 3 * 2);
        path.close();
        pathMeasure.setPath(path,false);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress= ((float) animation.getAnimatedValue());
                invalidate();
            }
        });
        valueAnimator.start();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("gao", "onMeasure: " + getWidth());

        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if(widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightSize);
        }else if(heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,200);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        Log.e("gao", "onDraw: "+getWidth());
        height = getHeight();
        radius = Math.min(width, height) / 3;
        tempR = radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        canvas.translate(width/2, height/2);
        pathMeasure.getSegment(0,pathMeasure.getLength()*progress,temp_Path,true);
        canvas.drawPath(temp_Path, paint);

    }




}
