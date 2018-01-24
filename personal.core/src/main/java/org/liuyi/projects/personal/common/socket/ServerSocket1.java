package org.liuyi.projects.personal.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocket1 {
	public static void main(String[] args) {
		while(true){
			ServerSocket serverSocket=null;
			try {
				serverSocket=new ServerSocket(8751);
			} catch (IOException e) {			
				e.printStackTrace();
			}
			while(true){
				try {
					new socketAccept(serverSocket.accept()).start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		}
	}
	
	static class socketAccept extends Thread{
		private Socket socket;
		public socketAccept(Socket socket){
			this.socket=socket;
		}
		@Override
		public void run() {
			try {	
				OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");   
				PrintWriter pw = new PrintWriter(osw, true);
				Scanner sca=new Scanner(socket.getInputStream());
				while(sca.hasNext()){
					String a=sca.nextLine();
					if(a.equals("你好")){	
						System.out.println("是否走分支");
						pw.println("好个毛线");
						pw.flush();
					}
					System.out.println(a);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
