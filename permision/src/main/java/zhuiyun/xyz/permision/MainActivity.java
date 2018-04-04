package zhuiyun.xyz.permision;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Proxy;

import xyz.zhuiyun.cloud.ioclibrary.ClickEvent;
import xyz.zhuiyun.cloud.ioclibrary.ViewById;
import xyz.zhuiyun.cloud.ioclibrary.ViewUtils;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.call)
    TextView call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
      Man man=new Man();
//        BankWorker bankWorker=new BankWorker(man);
//        bankWorker.applyBank();

        Bank bank= (Bank) Proxy.newProxyInstance(Bank.class.getClassLoader(), new Class[]{Bank.class}, new BankInvocationHandler(man)
        );
        bank.applyBank();

    }


    @ClickEvent(R.id.call)
    public void callPhone(View view) {
        callP();

    }

    private void callP() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:18871477477");
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},0);
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0){
            if(grantResults!=null&&grantResults.length>0){
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    callP();
                }
            }

        }
    }
}
