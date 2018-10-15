package com.gaoyf.config.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by 高宇飞 on 2018/10/15 10:37:15
 * 后台验证异常拦截器
 */
@Configuration
public class ViolationFilter {

    /**
     * 拦截验证之后的异常信息  可以写到公共拦截器中
     *
     * @param cve
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Map handleConstraintViolationException(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> set = cve.getConstraintViolations();
        Map map = new HashMap();
        for (ConstraintViolation<?> constraintViolation : set) {
            map.put("message", constraintViolation.getMessage());
        }
        return map;
    }
}
