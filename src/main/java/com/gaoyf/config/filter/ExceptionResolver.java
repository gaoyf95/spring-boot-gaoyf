package com.gaoyf.config.filter;

import com.alibaba.fastjson.JSON;
import com.gaoyf.common.VO.HttpCode;
import com.gaoyf.common.VO.JsonResult;
import com.gaoyf.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by 高宇飞 on 2018/9/29 10:47:55
 * 全局异常拦截器
 */

@Order(-500)
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger("exceptionLog");

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        JsonResult jsonResult = new JsonResult();
        StringBuilder sb = new StringBuilder();
        // 拦截的方法名称
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        logger.error("【实体类】:" + handlerMethod.getBeanType().toString());
        logger.error("【方法名】:" + method.getName());
        if (ex instanceof BindException) {
            resolverBindException(ex, sb, jsonResult);
        } else if (ex instanceof BusinessException) {
            resolverBusinessException(ex, sb, jsonResult);
        } else if (ex instanceof ConstraintViolationException) {
            constraintViolationException(ex, jsonResult);
        } else {
            if (methodParameters.length > 0) {
                for (MethodParameter methodParameter : methodParameters) {
                    logger.error("【参数名】：" + methodParameter.getParameterName());
                }
            }
            logger.error("【异常信息】：" + ex.getMessage());
            resolverOtherException(ex, sb, jsonResult);
        }

        jsonResult.setData(sb);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(JSON.toJSONString(jsonResult));
        } catch (IOException e) {
            logger.error("与客户端通讯异常：" + e.getMessage(), e);
        }
        return new ModelAndView();
    }

    /**
     * 处理参数验证异常
     */
    private void constraintViolationException(Exception ex, JsonResult jsonResult) {
        ConstraintViolationException cve = (ConstraintViolationException) ex;
        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            logger.error("【异常信息】：" + constraintViolation.getMessage());
        }
        addResult(jsonResult, HttpCode.PARAM_ERROR.getCode(), HttpCode.PARAM_ERROR.getMessage());
    }

    /**
     * 处理业务层异常
     */
    private void resolverBusinessException(Throwable e, StringBuilder sb, JsonResult jsonResult) {
        BusinessException businessException = (BusinessException) e;
        logger.error("【异常信息】：" + businessException.getBaseEnum().getMessage());
        sb.append(businessException.getBaseEnum().getMessage());
        addResult(jsonResult, businessException.getBaseEnum().getCode(), businessException.getBaseEnum().getMessage());
    }

    /**
     * 处理参数绑定异常
     */
    private void resolverBindException(Throwable e, StringBuilder sb, JsonResult jsonResult) {
        BindException be = (BindException) e;
        List<FieldError> errorList = be.getBindingResult().getFieldErrors();
        for (FieldError error : errorList) {
            sb.append(error.getObjectName());
            sb.append("对象的");
            sb.append(error.getField());
            sb.append("字段");
            sb.append(error.getDefaultMessage());
            logger.error("【异常信息】：" + error.getObjectName() + "中" + error.getField() + error.getDefaultMessage());
        }
        addResult(jsonResult, HttpCode.PARAM_ERROR.getCode(), HttpCode.PARAM_ERROR.getMessage());
    }

    /**
     * 处理其他异常
     */
    private void resolverOtherException(final Throwable e, StringBuilder sb, JsonResult jsonResult) {
        sb.append(e.getMessage());
        addResult(jsonResult, HttpCode.FAILURE.getCode(), HttpCode.FAILURE.getMessage());
    }

    /**
     * 封装code和msg
     */
    private void addResult(JsonResult jsonResult, final String code, final String msg) {
        jsonResult.setCode(code);
        jsonResult.setMessage(msg);
    }
}