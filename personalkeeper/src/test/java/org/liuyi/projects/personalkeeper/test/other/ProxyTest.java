package org.liuyi.projects.personalkeeper.test.other;

import java.net.*;
import java.io.*;

class ActionSocket extends Thread {
	private Socket socket = null;

	public ActionSocket(Socket s) {
		this.socket = s;
	}

	public void run() {
		try {
			this.action();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void action() throws Exception {
		if (this.socket == null) {
			return;
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		String line;
		StringBuilder headStr=new StringBuilder();
//		 //读取HTTP请求头，并拿到HOST请求头和method
        while (null != (line = br.readLine())) {
            System.out.println(line);
            headStr.append(line + "\r\n");
            if (line.length() == 0) {
                break;
            } else {
//                String[] temp = line.split(" ");
//                if (temp[0].contains("Host")) {
//                    host = temp[1];
//                }
            }
        }
		System.out.println(headStr.toString());
		br.close();
	}
}

public class ProxyTest {
	public static void main(String args[]) throws Exception {
		ServerSocket server = new ServerSocket(8888);
		while (true) {
			Socket socket = server.accept();
			ActionSocket ap = new ActionSocket(socket);
			ap.start();
		}
	}
}