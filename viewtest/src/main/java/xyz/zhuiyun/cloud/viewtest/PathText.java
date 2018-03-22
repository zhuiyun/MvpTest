package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gwy on 2018/3/19.
 *
 * @author:zhuiyun
 */

public class PathText extends View {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    PathMeasure pathMeasure=new PathMeasure();
    float progress;
    Path path=new Path();
    Path tempPath=new Path();
    public PathText(Context context) {
        this(context, null);
    }

    public PathText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
