package com.fourous.CommonTest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
* @author fourous
* @date: 2019/9/27
* @description: 对一些注解的单元测试
*/

public class AnnotationTest implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("===========================MyServletContextListener销毁");
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("===========================MyServletContextListener初始化");
        System.out.println(sce.getServletContext().getServerInfo());
    }
}
