package com.mao.tools.utils;

import java.util.Map;

/**
 * @author myseital
 * @date 2021/10/7 10:41
 */
public class MapUtil {

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
}