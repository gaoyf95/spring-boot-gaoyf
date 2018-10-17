package com.gaoyf.util.request;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 高宇飞 on 2018/10/15 14:07:28
 */
public class OperaParamUtil {

    /**
     * 获取Request的参数，并将其"Key=Value&Key=Value"的格式返回
     *
     * @param request 请求
     * @return "Key=Value&Key=Value"格式的字符串
     */
    public static String getOperaParams(HttpServletRequest request) {
        StringBuilder parameters = new StringBuilder();// 定义所有参数值
        Map<String, String[]> map = request.getParameterMap();
        // /取得所有参数值，用&号组装起来
        Object[] obj;
        obj = map.keySet().toArray();
        for (Object anObj : obj) {
            parameters.append(anObj.toString()).append("=").append(request.getParameter(anObj.toString())).append("&");
        }
        return parameters.toString();
    }
}
