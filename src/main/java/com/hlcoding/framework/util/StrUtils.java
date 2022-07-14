package com.hlcoding.framework.util;

import java.util.UUID;

/**
 *
 */
public class StrUtils {
    private StrUtils() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
