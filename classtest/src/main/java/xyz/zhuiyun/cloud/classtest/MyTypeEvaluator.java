package xyz.zhuiyun.cloud.classtest;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by gwy on 2018/3/28.
 *
 * @author:zhuiyun
 */

public class MyTypeEvaluator implements TypeEvaluator<PointF> {
    PointF point1,point2;

    public MyTypeEvaluator(PointF point1, PointF point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public PointF evaluate(float t, PointF point0, PointF point3) {
        PointF pointF=new PointF();
        pointF.x=point0.x*(1-t)*(1-t)*(1-t)+3*point1.x*t*(1-t)*(1-t)+3*point2.x*t*t*(1-t)+point3.x*t*t*t;
        pointF.y=point0.y*(1-t)*(1-t)*(1-t)+3*point1.y*t*(1-t)*(1-t)+3*point2.y*t*t*(1-t)+point3.y*t*t*t;
        return pointF;
    }
}
