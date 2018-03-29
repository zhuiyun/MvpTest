package xyz.zhuiyun.cloud.classtest;

/**
 * Created by gwy on 2018/3/26.
 *
 * @author:zhuiyun
 */

//public class HookStartActivityUtils {
//    public void hookStartActivity() throws ClassNotFoundException {
//        Class<?> clz=Class.forName("android.app.IActivityManager");
//        Proxy.newProxyInstance(HookStartActivityUtils.class.getClassLoader(), new Class[]{clz},
//                //InvocationHandler 必须执行者,谁去执行
//                new InvocationHandler() {
//        })
//    }
//
//    private class StartActivityInvocationHandler implements InvocationHandler{
//
//        private Object mObject;
//
//        public StartActivityInvocationHandler(Object mObject) {
//            this.mObject = mObject;
//        }
//
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            return ;
//        }
//    }
//}
