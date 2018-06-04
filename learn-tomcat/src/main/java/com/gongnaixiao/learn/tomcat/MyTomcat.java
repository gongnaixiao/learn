package com.gongnaixiao.learn.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.startup.Tomcat;

/**
 * Created by xg on 2018/4/17.
 */
public class MyTomcat
{
    public static void run() throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();

        Service service = new StandardService();

        server.addService(service);
        server.start();
        server.await();
    }
}
