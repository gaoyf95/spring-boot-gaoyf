package com.gaoyf.config.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 高宇飞 on 2018/9/29 11:47:55
 * 编码、压缩拦截器
 */

@Order(1)
@WebFilter(filterName = "GzipJsFilter", urlPatterns = "/*")
public class GzipJsFilter implements Filter {

    @SuppressWarnings("rawtypes")
    private Map headers = new HashMap();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
        } else {
            chain.doFilter(req, res);
        }
    }

    @SuppressWarnings("rawtypes")
    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        for (Object o : headers.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            response.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        chain.doFilter(request, response);
    }

    @SuppressWarnings("unchecked")
    public void init(FilterConfig config) {
        String headersStr = config.getInitParameter("headers");
        if (headersStr != null) {
            String[] headers = headersStr.split(",");
            for (String header : headers) {
                String[] temp = header.split("=");
                this.headers.put(temp[0].trim(), temp[1].trim());
            }
        }
    }
}
