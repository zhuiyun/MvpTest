package xyz.zhuiyun.cloud.viewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=findViewById(R.id.view);
        Log.e("gao", "onCreate: "+view.getHeight());
        view.post(new Runnable() {
            @Override
            public void run() {
                Log.e("gao", "run: "+view.getHeight());
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("gao", "onResume: "+view.getHeight());
    }
}
