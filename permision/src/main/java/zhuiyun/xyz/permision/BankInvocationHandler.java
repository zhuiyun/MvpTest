package zhuiyun.xyz.permision;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by gwy on 2018/4/4.
 *
 * @author:zhuiyun
 */

public class BankInvocationHandler implements InvocationHandler {
    private Object mObject;

    public BankInvocationHandler(Object mObject) {
        this.mObject = mObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("gao", "invoke: 开始代理");
        Object invoke = method.invoke(mObject, args);
        Log.e("gao", "invoke: 代理结束");
        return invoke;
    }
}
