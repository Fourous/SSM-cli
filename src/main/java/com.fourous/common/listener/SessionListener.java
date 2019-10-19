package com.fourous.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
* @author fourous
* @date: 2019/10/19
* @description: 监听实在整个请求返回过程中是持续存在的
*/
@WebListener
public class SessionListener implements HttpSessionAttributeListener, HttpSessionListener {
    /** 将在线人数保存在session中 **/
    private static int personOnline;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session created");
        System.out.println("online person"+se);
        personOnline++;
        se.getSession().setAttribute("personOnline",personOnline);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session destroyed");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("session attribute added");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("session attribute removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("session attribute replaced");
        System.out.println("online person"+event);
        personOnline--;
        event.getSession().setAttribute("personOnline",personOnline);
    }

}
