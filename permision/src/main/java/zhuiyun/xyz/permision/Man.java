package zhuiyun.xyz.permision;

import android.util.Log;

/**
 * Created by gwy on 2018/4/4.
 *
 * @author:zhuiyun
 */

public class Man implements Bank {
    
    @Override
    public void applyBank() {
        Log.e("gao", "applyBank: 代理中");
    }
}
