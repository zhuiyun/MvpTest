package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gwy on 2018/3/15.
 *
 * @author:zhuiyun
 */

public class PictureView extends View {
    Picture mPicture=new Picture();
    public PictureView(Context context) {
        this(context, null);
    }

    public PictureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PictureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initData();
        recording();
    }

    private void recording() {
        Canvas canvas=mPicture.beginRecording(500,500);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100,paint);

        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        PictureDrawable pictureDrawable=new PictureDrawable(mPicture);
//        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),500));
        pictureDrawable.setBounds(0,0,mPicture.getWidth(),250);
        pictureDrawable.draw(canvas);
    }

    private void initData() {
    }
}
