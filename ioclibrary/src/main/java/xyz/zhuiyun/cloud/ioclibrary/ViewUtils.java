package xyz.zhuiyun.cloud.ioclibrary;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by gwy on 2018/3/23.
 *
 * @author:zhuiyun
 */

public class ViewUtils {
    static long lastClickTime;

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
        Class<?> claz = object.getClass();
        Method[] declaredMethods = claz.getDeclaredMethods();
        for (Method method : declaredMethods
                ) {
            ClickEvent clickEvent = method.getAnnotation(ClickEvent.class);
            if (clickEvent != null) {
                int[] viewIds = clickEvent.value();
                for (int id :
                        viewIds) {
                    View viewById = viewFinder.findViewById(id);
                    CheckNet annotation = method.getAnnotation(CheckNet.class);
                    RepeatClick repeatClick = method.getAnnotation(RepeatClick.class);
                    boolean isCheckRepeat = false;
                    int repeatDuration = 0;
                    if (repeatClick != null) {
                        isCheckRepeat = true;
                        repeatDuration = repeatClick.value();
                    }
                    boolean isCheckNet = false;
                    String status = "";
                    if (annotation != null) {
                        isCheckNet = true;
                        status = annotation.value();
                    }

                    if (viewById != null) {
                        viewById.setOnClickListener(new DeclaredOnClickListener(method, object, isCheckNet, status, isCheckRepeat, repeatDuration));
                    }
                }
            }
        }
    }

    public static class DeclaredOnClickListener implements View.OnClickListener {
        private Object object;
        private Method method;
        private boolean isCheckNet;
        private String status;
        private boolean isCheckRepeat;
        private int repeatDuration;

        public DeclaredOnClickListener(Method method, Object object, boolean isCheckNet, String status, boolean isCheckRepeat, int repeatDuration) {
            this.method = method;
            this.object = object;
            this.isCheckNet = isCheckNet;
            this.status = status;
            this.isCheckRepeat = isCheckRepeat;
            this.repeatDuration = repeatDuration;
        }

        @Override
        public void onClick(View v) {
            if (System.currentTimeMillis() - lastClickTime < repeatDuration) {
                return;
            }
            if (isCheckNet) {
                if (!netWorkAvailable(v.getContext())) {
                    Toast.makeText(v.getContext(), TextUtils.isEmpty(status) ? "无网络" : status, Toast.LENGTH_LONG).show();
                    return;
                }

            }

            method.setAccessible(true);
            try {
                lastClickTime=System.currentTimeMillis();
                method.invoke(object, v);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    method.invoke(object, new Object[]{null});
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        }


    }

    private static boolean netWorkAvailable(Context mContext) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

                if (view != null) {
                    field.setAccessible(true);

                    try {
                        field.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
