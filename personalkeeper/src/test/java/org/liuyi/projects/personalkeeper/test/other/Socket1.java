package org.liuyi.projects.personalkeeper.test.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.lowagie.text.Header;

public class Socket1 {
	public static void main(String[] args) {
		//监听端口
		ServerSocket serverSocket=null;
		try{		
			serverSocket= new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){ 
		    try{
		    	new SocketHandle(serverSocket.accept()).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class SocketHandle extends Thread {

        private Socket socket;

        public SocketHandle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
        	System.out.println("start----------------------------------------");
            OutputStream clientOutput = null;
            InputStream clientInput = null;
            Socket proxySocket = null;
            InputStream proxyInput = null;
            OutputStream proxyOutput = null;
            try {
                clientInput = socket.getInputStream();
                clientOutput = socket.getOutputStream();
                String line;
                String host = "";
                InputStreamReader isr = new InputStreamReader(clientInput,"UTF-8");
                BufferedReader lineBuffer = new BufferedReader(isr);
                StringBuilder headStr = new StringBuilder();
                //读取HTTP请求头，并拿到HOST请求头和method
                while (null != (line = lineBuffer.readLine())) {
                    headStr.append(line + "\r\n");
                    if (line.length() == 0) {
                        break;
                    } else {
                        String[] temp = line.split(" ");
                        if (temp[0].contains("Host")) {
                            host = temp[1];
                        }
                    }
                }
                System.out.println(headStr.toString());
                String type = headStr.substring(0, headStr.indexOf(" "));
//                //根据host头解析出目标服务器的host和port
                String[] hostTemp = host.split(":");
                host = hostTemp[0];
                int port = 80;
                if (hostTemp.length > 1) {
                    port = Integer.valueOf(hostTemp[1]);
                }
                //连接到目标服务器
                proxySocket = new Socket(host, port);
//                System.out.println("目标服务器地址host="+host+" port="+port);
                proxyInput = proxySocket.getInputStream();
                proxyOutput = proxySocket.getOutputStream();
//                //根据HTTP method来判断是https还是http请求
                if ("CONNECT".equalsIgnoreCase(type)) {//https先建立隧道
                    clientOutput.write("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes());
                    clientOutput.flush();
                } else {//http直接将请求头转发
                    proxyOutput.write(headStr.toString().getBytes());
                }
//                //新开线程转发客户端请求至目标服务器  
                new ProxyHandleThread(clientInput, proxyOutput).start();
//                //转发目标服务器响应至客户端
                InputStreamReader proxyInputReader=new InputStreamReader(proxyInput);
                BufferedReader bufferedReader=new BufferedReader(proxyInputReader);
                while (true) {
                	line=bufferedReader.readLine();
                	System.out.println(line + "\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (proxyInput != null) {
                    try {
                        proxyOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (proxyOutput != null) {
                    try {
                        proxyOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (proxySocket != null) {
                    try {
                        proxySocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (clientInput != null) {
                    try {
                        clientInput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (clientOutput != null) {
                    try {
                        clientOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("end+++++++++++++++++++++++++++++++++++++++++++");
        }
    }
	
	static class ProxyHandleThread extends Thread {

        private InputStream input;
        private OutputStream output;

        public ProxyHandleThread(InputStream input, OutputStream output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            try {
            	while(true){
            		output.write(input.read());
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
