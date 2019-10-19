package com.fourous.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author fourous
* @date: 2019/10/19
* @description: 拦截器实现
*/
public class interceptor implements HandlerInterceptor {
    /**
     * 进入Handler方法之前执行
     * 可以用于身份认证、身份授权。如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行，否则就放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("InterceptorUtil...........preHandle");
        String user= (String) request.getSession().getAttribute("user");
        if(user != null){
            System.out.println("this user is "+user);
            return true;
        }
        // 这里在未验证之前，所以带有URL链接都会定位到这个页面
        request.getRequestDispatcher("/WEB-INF/views/UserInfo.jsp").forward(request, response);
        //true表示放行，false表示不放行
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("InterceptorUtil...........postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("InterceptorUtil...........afterCompletion");
    }
}
