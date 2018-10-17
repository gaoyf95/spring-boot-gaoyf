package com.gaoyf.config.interceptor;

import com.gaoyf.config.interfaces.Function;
import com.gaoyf.config.interfaces.Module;
import com.gaoyf.jpa.entity.SystemLog;
import com.gaoyf.jpa.service.SystemLogService;
import com.gaoyf.resultful.controller.UserController;
import com.gaoyf.util.request.IpUtils;
import com.gaoyf.util.request.OperaParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by 高宇飞 on 2018/10/8 14:54:35
 *
 * @Description: 系统日志拦截器
 */
@Component
public class SysLogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger("handledLog");

    @Autowired
    private SystemLogService systemLogService;


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
        String servletPath = request.getServletPath();
        String parameters = OperaParamUtil.getOperaParams(request);
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
        String ip = IpUtils.getIp(request);
        //记录日志
        logger.info("操作模块：" + moduleValue);
        logger.info("操作方法：" + descLogValue);
        logger.info("IP：" + ip);
        if (clazz.equals(UserController.class) && "login".equals(method.getName())) {
            logger.info("操作用户：" + "没有登陆");
        } else {
            // TODO: 2018/10/8 从缓存中或者其他途径获取登陆用户信息
            logger.info("操作用户：" + "已登录");
        }
        SystemLog systemLog = new SystemLog("", "测试", new Date(), ip, moduleValue,
                descLogValue, servletPath, parameters);
        if (systemLogService == null) {//解决service为null无法注入问题
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            systemLogService = (SystemLogService) factory.getBean("systemLogService");
        }
        systemLogService.save(systemLog);
    }
}
