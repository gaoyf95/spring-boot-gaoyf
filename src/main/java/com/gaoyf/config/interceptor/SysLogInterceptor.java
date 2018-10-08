package com.gaoyf.config.interceptor;

import com.gaoyf.config.interfaces.Function;
import com.gaoyf.config.interfaces.Module;
import com.gaoyf.resultful.controller.UserController;
import com.gaoyf.util.ip.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by 高宇飞 on 2018/10/8 14:54:35
 *
 * @Description: 系统日志拦截器
 */
public class SysLogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger("handledLog");

    /**
     * 当Controller的业务方法里出现了异常，此方法就不会被执行。
     * <p>
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前）
     * 此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理
     * modelAndView也可能为null。
     *
     * @param request      request
     * @param response     response
     * @param handler      handler
     * @param modelAndView modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //获取类注解
        Class<?> clazz = handlerMethod.getBean().getClass();
        Module module = clazz.getAnnotation(Module.class);
        if (null == module) {
            return;
        }
        String moduleValue = module.value();

        //获取方法注解
        Method method = handlerMethod.getMethod();
        Function descLog = method.getAnnotation(Function.class);
        if (null == descLog) {
            return;
        }
        String descLogValue = descLog.value();

        //记录日志
        logger.info("操作模块：" + moduleValue);
        logger.info("操作方法：" + descLogValue);
        logger.info("IP：" + IpUtils.getIp(request));
        if (clazz.equals(UserController.class) && "login".equals(method.getName())) {
            logger.info("操作用户：" + "没有登陆");
        } else {
            // TODO: 2018/10/8 从缓存中或者其他途径获取登陆用户信息
            logger.info("操作用户：" + "已登录");
        }
    }
}
