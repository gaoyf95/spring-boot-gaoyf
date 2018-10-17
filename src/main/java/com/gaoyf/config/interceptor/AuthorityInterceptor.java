package com.gaoyf.config.interceptor;

import com.gaoyf.util.request.OperaParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 高宇飞 on 2018/9/29 10:10:24
 * 拦截所有请求记录日志
 */
public class AuthorityInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger("operationLog");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String path = request.getServletPath();
        path = path.substring(1);
        String parameters = OperaParamUtil.getOperaParams(request);
        //记日志
        try {
            logOperation(path, parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: 2018/9/29  判断是否登陆
        if (true) {
            // TODO: 2018/9/29  这里校验是否有权限操作
            if (true) {
                //记录数据库日志
                return true;
            } else {
                return true;
            }
        }
        return true;
    }
    //其他postHandle,afterCompletion空继承

    /**
     * 记录文本日志
     */
    private void logOperation(String path, String parameters) {
        String log = "【请求:】" + "[INFO]-" + path + "-" + parameters;
        logger.info(log);
    }
}

