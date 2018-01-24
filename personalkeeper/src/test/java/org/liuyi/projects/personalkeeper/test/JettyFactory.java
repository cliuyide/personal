package org.liuyi.projects.personalkeeper.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;


public class JettyFactory{
  private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
  private static final String WINDOWS_WEBDEFAULT_PATH = "jetty/webdefault-windows.xml";

  public static Server createServerInSource(int port, String contextPath)
  {
    Server server = new Server();

    server.setStopAtShutdown(true);

//    SelectChannelConnector connector = new SelectChannelConnector();
//    connector.setPort(port);
//
//    connector.setReuseAddress(false);
//    server.setConnectors(new Connector[] { connector });

    WebAppContext webContext = new WebAppContext("src/main/webapp", contextPath);

    webContext.setDefaultsDescriptor("jetty/webdefault-windows.xml");
    server.setHandler(webContext);
    return server;
  }

  public static void setTldJarNames(Server server, String[] jarNames)
  {
    WebAppContext context = (WebAppContext)server.getHandler();
    List list = new ArrayList();
    list.addAll(Arrays.asList(new String[] { ".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$" }));
    List jarNameExprssions = list;
    for (String jarName : jarNames) {
      jarNameExprssions.add(new StringBuilder().append(".*/").append(jarName).append("-[^/]*\\.jar$").toString());
    }

    context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", join(jarNameExprssions.toArray(new String[0]), "|"));
  }

  public static void reloadContext(Server server)
    throws Exception
  {
    WebAppContext context = (WebAppContext)server.getHandler();

    System.out.println("Application reloading");
    context.stop();

    WebAppClassLoader classLoader = new WebAppClassLoader(context);
    classLoader.addClassPath("target/classes");
    classLoader.addClassPath("target/test-classes");
    context.setClassLoader(classLoader);

    context.start();

    System.out.println("Application reloaded");
  }

  public static <T> String join(T[] ids, String split) {
    StringBuilder sb = new StringBuilder();
    if ((ids != null) && (ids.length > 0)) {
      boolean isFirst = true;
      for (Object id : ids) {
        if (!isFirst)
          sb.append(split);
        else {
          isFirst = false;
        }
        sb.append(id);
      }
    }
    return sb.toString();
  }

  static
  {
    try
    {
      InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j-test.properties");
      Properties properties = new Properties();
      properties.load(is);
      is.close();
      is = null;
//      PropertyConfigurator.configure(properties);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
}