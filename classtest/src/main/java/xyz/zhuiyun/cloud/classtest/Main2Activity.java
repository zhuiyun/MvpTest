package xyz.zhuiyun.cloud.classtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import xyz.zhuiyun.cloud.ioclibrary.CheckNet;
import xyz.zhuiyun.cloud.ioclibrary.ClickEvent;
import xyz.zhuiyun.cloud.ioclibrary.ViewById;
import xyz.zhuiyun.cloud.ioclibrary.ViewUtils;


public class Main2Activity extends AppCompatActivity {

    @ViewById(R.id.tv11)
    private Button mTextView;
    @ViewById(R.id.gifView)
    GiftView giftView;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewUtils.inject(this);
        mTextView.setText("IOC");
        btn1.setText("fsdgsdg");
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.e("gao", "onPostExecute: "+Thread.currentThread());
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                Log.e("gao", "onProgressUpdate: "+Thread.currentThread());
            }

            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
        };
       asyncTask.execute();


    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics());
    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    @ClickEvent(R.id.tv11)
    @CheckNet("网络错误")
    private void onClick(View view){
        Log.e("gao", "onClick: ");
        giftView.add();
//        Intent intent=new Intent(this,MainActivity3.class);
//        startActivity(intent);
    }
}
