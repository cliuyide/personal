package org.liuyi.projects.personalkeeper.test;

import java.util.Arrays;

import javax.websocket.DeploymentException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class JettyFactory {
	
	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
    private static final String WINDOWS_WEBDEFAULT_PATH = "org/liuyi/projects/personalkeeper/test/webdefault.xml";

	 public static Server createServerInSource(int port, String contextPath) throws Exception {
	        return createServerInSource(port, contextPath, (Class[])null);
	    }

	    public static Server createServerInSource(int port, String contextPath, Class<?>[] webscocketKlasses) throws Exception {
	        Server server = new Server(port);
	        server.setStopAtShutdown(true);
	        ClassList classlist = ClassList.setServerDefault(server);
	        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", new String[]{"org.eclipse.jetty.annotations.AnnotationConfiguration"});
	        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration", new String[]{"org.eclipse.jetty.plus.webapp.EnvConfiguration", "org.eclipse.jetty.plus.webapp.PlusConfiguration"});
	        WebAppContext webapp = new WebAppContext("src/main/webapp", contextPath);
	        webapp.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);
	        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$|.*/[^/]*cedu.*\\.jar");
	        server.setHandler(webapp);
	        WebSocketServerContainerInitializer.configureContext(webapp);
	        ServerContainer container = (ServerContainer)webapp.getServletContext().getAttribute(ServerContainer.class.getName());
	        if (webscocketKlasses != null && webscocketKlasses.length > 0) {
	            Arrays.asList(webscocketKlasses).forEach((t) -> {
	                try {
	                    container.addEndpoint(t);
	                } catch (DeploymentException var3) {
	                    var3.printStackTrace();
	                }

	            });
	        }

	        return server;
	    }
}
