package xyz.zhuiyun.cloud.classtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gwy on 2018/3/23.
 *
 * @author:zhuiyun
 */

public class DeView extends View {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    float little_radius=50;
    float big_radius=100;
    int color= Color.RED;
    public DeView(Context context) {
        this(context, null);
    }

    public DeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.drawCircle(-100,0,little_radius,paint);
        canvas.drawCircle(+100,0,big_radius,paint);
    }
}
