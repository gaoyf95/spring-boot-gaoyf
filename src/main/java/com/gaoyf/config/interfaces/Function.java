package com.gaoyf.config.interfaces;

import java.lang.annotation.*;

/**
 * Created by 高宇飞 on 2018/10/7 14:54:35
 *
 * @Description: 用于记录系统操作日志的注解（具体操作描述） -- 方法注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Function {

    /**
     * @return 操作描述
     */
    String value();
}
