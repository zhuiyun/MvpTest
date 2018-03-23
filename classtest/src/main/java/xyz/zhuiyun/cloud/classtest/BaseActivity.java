package xyz.zhuiyun.cloud.classtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by gwy on 2018/3/22.
 *
 * @author:zhuiyun
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflater.Factory2() {
            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs) {
                Log.e("gao", "onCreateView: "+name);
                return null;
            }

            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.e("gao1", "onCreateView: "+name);
                Log.e("gao1", "onCreateView: "+parent);
                if(name.equals("Button")){
                    Button button=new Button(BaseActivity.this);
                    button.setText("fgsdgsdg");

                    button.setBackgroundColor(Color.RED);
                    return button;
                }
                return null;
            }
        });
        super.onCreate(savedInstanceState);
    }
}
