package xyz.zhuiyun.cloud.classtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LetterSideView sideView;
    TextView tv;
    TextView textView;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        sideView=findViewById(R.id.slide);
        Log.e("gao", "onCreate: "+tv.getMeasuredHeight());
        tv.post(new Runnable() {
            @Override
            public void run() {
                Log.e("gao", "onCreate1: "+tv.getMeasuredHeight());
            }
        });
        sideView.setTouchListener(new LetterSideView.LetterTouchListener() {
            @Override
            public void onTouchLetter(CharSequence letter, int position) {
                if(TextUtils.isEmpty(letter)){

                    tv.setVisibility(View.GONE);
                }else {
                    tv.setVisibility(View.VISIBLE);
                    tv.setText(letter);
                }

            }
        });


        Log.e("gao", "run: "+Thread.currentThread().getName());

        S s=new S();

        sideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("gao", "onCreate:2 "+tv.getMeasuredHeight());
    }
}
