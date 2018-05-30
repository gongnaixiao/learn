package com.gongnaixiao.learn.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class EmbedTomcatServer {
    public static void main(String[] args) throws InterruptedException, LifecycleException {
        Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir("");

        tomcat.addContext("/", "");

        //tomcat.addServlet()

        tomcat.setPort(9080);
        tomcat.start();

        tomcat.wait();
    }
}
