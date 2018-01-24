package org.liuyi.projects.personalkeeper.test;


import org.junit.Ignore;

/**
 * 使用Jetty运行调试Web应用
 */
@Ignore
public class JettyServerForPersonalKeeper {
	public static void main(String[] args) throws Exception {
//		// 服务器的监听端口
//		Server server = new Server(9090);
//		// 关联一个已经存在的上下文
//		WebAppContext context = new WebAppContext("src/main/webapp","/personalkeeper");
//
//		// 设置描述符位置
//		context.setDefaultsDescriptor("webdefault.xml");
//		server.setHandler(context);
//
//		try {
//			server.start();
//			// server.join();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("server is  start");
		String webapp = "src/main/webapp";  
        new JettyServerStart(webapp, 9090, "/personalkeeper").start();  
	}
}