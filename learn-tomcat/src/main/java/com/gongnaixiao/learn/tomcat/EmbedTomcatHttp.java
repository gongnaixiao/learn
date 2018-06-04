package com.gongnaixiao.learn.tomcat;

import com.gongnaixiao.learn.tomcat.servlet.HomeServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xg on 2018/4/13.
 */
public class EmbedTomcatHttp {
    private static final Logger log = LoggerFactory.getLogger(EmbedTomcatHttp.class);

    private static final int port = 9080;
    private static final String baseDir = "H:/tmp/tomcat";

    public static void run() throws LifecycleException {
        log.info("tomcat is starting ");

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(baseDir);

        String contextPath = "/book";
        String docBase="/book";
        Context context = tomcat.addContext(contextPath, docBase);

        tomcat.addServlet(contextPath, "homeServlet", new HomeServlet());
        context.addServletMappingDecoded("/home", "homeServlet");

        tomcat.setPort(port);
        tomcat.start();
        log.info("tomcat has started");

        tomcat.getServer().await();
    }
}
