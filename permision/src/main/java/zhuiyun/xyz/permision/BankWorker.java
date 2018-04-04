package zhuiyun.xyz.permision;

import android.util.Log;

/**
 * Created by gwy on 2018/4/4.
 *
 * @author:zhuiyun
 */

public class BankWorker implements Bank {
    private Man man;

    public BankWorker(Man man) {
        this.man = man;
    }

    @Override
    public void applyBank() {
        Log.e("gao", "applyBank: 开始代理");
        man.applyBank();
        Log.e("gao", "applyBank: 代理结束");
    }
}
