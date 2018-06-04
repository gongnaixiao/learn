package com.gongnaixiao.learn.tomcat;

import com.gongnaixiao.learn.tomcat.servlet.HomeServlet;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;

/**
 * Created by xg on 2018/4/13.
 */
public class EmbedTomcatHttps {

    public static final String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";
    static final String docBase = "e:/tmp/tomcat";
    static final int port = 9080;
    static final int ports = 9090;

    public static void run() throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(docBase);

        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setPort(ports);

        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        protocol.setKeystorePass("123456");
        protocol.setKeystoreFile("e:/tmp/ssl/boot.keystore");
        protocol.setKeyAlias("mykey");
        protocol.setSSLEnabled(true);

        tomcat.getService().addConnector(connector);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = "/book";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(context);

        tomcat.addServlet(contextPath, "homeServlet", new HomeServlet());
        context.addServletMappingDecoded("/home", "homeServlet");
        tomcat.start();
        tomcat.getServer().await();
    }
}
