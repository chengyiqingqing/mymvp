package com.sww.basemvp.utils;

import java.lang.reflect.ParameterizedType;

/**
 * @author ShaoWenWen
 * @date 2019-08-15
 */
public class InstanceUtils {

    public static final int POSITION_FIRST = 0;

    /**
     * 初始化某类的第一个泛型类
     */
    public static <T> T getInstance(Object object) {
        try {
            return ((Class<T>) ((ParameterizedType) object.getClass()
                    .getGenericSuperclass())
                    .getActualTypeArguments()[POSITION_FIRST]) // 该方法属于ParameterizedType泛型类
                    .newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
