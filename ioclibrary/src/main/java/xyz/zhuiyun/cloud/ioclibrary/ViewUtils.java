package xyz.zhuiyun.cloud.ioclibrary;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by gwy on 2018/3/23.
 *
 * @author:zhuiyun
 */

public class ViewUtils {
    public static void inject(Activity mActivity) {
        inject(new ViewFinder(mActivity), mActivity);
    }

    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);

    }

    //兼容上面三个方法
    public static void inject(ViewFinder viewFinder, Object object) {
        injectField(viewFinder, object);
        injectEvent(viewFinder, object);
    }

    /**
     * 注入事件
     *
     * @param viewFinder
     * @param object
     */
    private static void injectEvent(ViewFinder viewFinder, Object object) {

    }

    /**
     * 注入属性
     *
     * @param viewFinder
     * @param object
     */
    private static void injectField(ViewFinder viewFinder, Object object) {
        Class<?> aClass = object.getClass();
        //获取所有属性,包括私有和公有
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null) {
                int viewId = viewById.value();
                View view = viewFinder.findViewById(viewId);
                Log.e("gao", "injectField: ");
                if (view != null) {
                    field.setAccessible(true);
                    Log.e("gao", "injectField1: ");
                    try {
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        Log.e("gao", "injectField: "+e.toString());
                    }
                }
            }

        }
    }
}
