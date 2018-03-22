package xyz.zhuiyun.cloud.viewtest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by gwy on 2018/3/19.
 *
 * @author:zhuiyun
 */

public class SView extends ListView {
    public SView(Context context) {
        this(context, null);
    }

    public SView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
