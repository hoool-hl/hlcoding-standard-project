package com.hlcoding.framework.annos;

import java.lang.annotation.*;

/**
 * 不需要登录
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SkipUserAuth {
}
