package org.liuyi.projects.personalkeeper.test;


import org.junit.Ignore;

/**
 * 使用Jetty运行调试Web应用
 */
@Ignore
public class JettyServerForPersonalKeeper {
	public static void main(String[] args) throws Exception {
		String webapp = "src/main/webapp";  
        new JettyServer(webapp, 8080, "/personalkeeper").start();  
	}
}