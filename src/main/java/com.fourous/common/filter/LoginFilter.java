package com.fourous.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author fourous
* @date: 2019/9/27
* @description:
 * 这里一定要注意引入的包是javax.servlet.Filter，有一个java.util.logging.Filter也有这个Filter包，注意不要引入错误
*/
public class LoginFilter implements Filter {
    private FilterConfig filterConfig;

    /**
     * 通过继承Filter类，重写三个方法即可实现过滤器，当然还需要在web.xml配置
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println("过滤初始化");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("FilterUtil just supports HTTP requests");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpRequest.setCharacterEncoding(this.filterConfig.getInitParameter("encoding"));
        httpResponse.setCharacterEncoding(this.filterConfig.getInitParameter("encoding"));
        chain.doFilter(httpRequest, httpResponse);
    }
    @Override
    public void destroy() {
        System.out.println("过滤器Filter销毁");
    }
}
