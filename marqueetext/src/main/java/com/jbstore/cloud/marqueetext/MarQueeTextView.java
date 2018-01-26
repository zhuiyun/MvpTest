package com.jbstore.cloud.marqueetext;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by gwy on 2018/1/26.
 *
 * @author:zhuiyun
 */

public class MarQueeTextView extends TextView {
    public MarQueeTextView(Context context) {
        this(context, null);
    }

    public MarQueeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarQueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
