package com.hlcoding.framework.annos;

import java.lang.annotation.*;

/**
 * 需要超管权限
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequireSuperAdmin {

}
