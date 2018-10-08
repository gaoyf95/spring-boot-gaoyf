package com.gaoyf.config.interfaces;

import java.lang.annotation.*;

/**
 * Created by 高宇飞 on 2018/10/7 14:54:35
 *
 * @Description: 用于记录系统操作日志的注解（模块名） -- 类注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Module {

    /**
     * @return 模块名
     */
    String value();
}