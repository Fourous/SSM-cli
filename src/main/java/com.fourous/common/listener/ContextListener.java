package com.fourous.common.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
* @author fourous
* @date: 2019/9/27
* @description: 公共监听类
 * listener具体分为八种，能够监听包括request域，session域，application域的产生，销毁和属性的变化
 * 常用场景
 * 1：获取在线人数，也可以获取历史在线人数，通过将信息保存在session中
 * 2：系统初始化时，获取项目绝对路径，这个也可以保存在系统变量中
*/

@WebListener
public class ContextListener implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext initialized");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("ServletContext attribute added");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("ServletContext attribute removed");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("ServletContext attribute replaced");
    }

}
