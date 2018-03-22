package xyz.zhuiyun.cloud.classtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gwy on 2018/3/22.
 *
 * @author:zhuiyun
 */

public class LetterSideView extends View {
    Paint mPaint;
    Rect rect = new Rect();
    Rect rect1 = new Rect();

    String[] s = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    int itemHeight;
    String currentLetter = "";

    public LetterSideView(Context context) {
        this(context, null);
    }

    public LetterSideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public LetterSideView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
//        setClickable(true);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(sp2px(16));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画26个字母
        int x = getMeasuredWidth() / 2;
        itemHeight = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom()) / s.length;
        for (int i = 0; i < s.length; i++) {
            mPaint.getTextBounds(s[i], 0, 1, rect);
            int centerY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
            int y = centerY + (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
            if (s[i].equals(currentLetter)) {
                mPaint.setColor(Color.RED);
                canvas.drawText(s[i], x - rect.width() / 2, y, mPaint);
            } else {
                mPaint.setColor(Color.BLUE);
                canvas.drawText(s[i], x - rect.width() / 2, y, mPaint);
            }

        }

//        if (!TextUtils.isEmpty(currentLetter)) {
//            mPaint.setColor(Color.BLUE);
//            canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, mPaint);
//            mPaint.setColor(Color.WHITE);
//            mPaint.getTextBounds(currentLetter, 0, 1, rect1);
//            Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
//            int y = getHeight() / 2 + (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
//
//            canvas.drawText(currentLetter, getWidth() / 2 - rect1.width() / 2, y, mPaint);
//        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float currentMoveY = event.getY();
                int currentPosition = (int) (currentMoveY / itemHeight);
                currentLetter = s[currentPosition];
                touchListener.onTouchLetter(currentLetter,currentPosition);
                invalidate();
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                float currentMoveY = event.getY();
                int currentPosition = (int) (currentMoveY / itemHeight);
                if(currentPosition<0){
                    currentPosition=0;
                }
                if(currentPosition>s.length-1){
                    currentPosition=s.length-1;
                }
                currentLetter = s[currentPosition];
                touchListener.onTouchLetter(currentLetter,currentPosition);
                invalidate();
            }
            break;
            case MotionEvent.ACTION_UP:
                currentLetter = "";
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touchListener.onTouchLetter(currentLetter,-1);
                        invalidate();
                    }
                }, 500);


                break;
        }
        return true;
    }

    private float sp2px(int i) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, i, getResources().getDisplayMetrics());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float a = mPaint.measureText("A");
        int width = (int) (getPaddingLeft() + getPaddingRight() + a);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    interface LetterTouchListener{
        void onTouchLetter(CharSequence letter,int position);
    }
    LetterTouchListener touchListener;

    public void setTouchListener(LetterTouchListener touchListener){
        this.touchListener=touchListener;
    }
}
