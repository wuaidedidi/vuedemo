package com.lichen.lichenblog.admin.annotation;

/**
 * @author lichen
 * @version 1.0.0
 * @className OptLog.java
 * @Description TODO
 * @createTime 2022年09月19日 16:56:00
 */

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * @return 操作类型
     */
    String optType() default "";

}