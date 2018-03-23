package xyz.zhuiyun.cloud.classtest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by gwy on 2018/3/23.
 *
 * @author:zhuiyun
 */

/**
 * @Target 代表annoation的位置 FIELD属性 TYPE类上ConStructor 用在构造方法上
 */
@Target(ElementType.FIELD)
public @interface ViewById {
    int value();
}