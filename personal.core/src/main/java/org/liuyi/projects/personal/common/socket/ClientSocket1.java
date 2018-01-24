package org.liuyi.projects.personal.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket1 {
	public static void main(String[] args) {
		try {		
			Socket socket = new Socket("172.16.99.231", 8751);
//			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");  
//            PrintWriter pw = new PrintWriter(osw, true);
//            Scanner scanner = new Scanner(System.in);  
//            while(true){
//            	pw.write(scanner.nextLine());
//            }
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");   
			PrintWriter pw = new PrintWriter(osw, true);
            //pw.println("你好！服务器！");  
            //创建Scanner读取用户输入内容  
            Scanner scanner = new Scanner(System.in);  
            new Thread1(socket).start();
            while(true){  
                //scan.nextLine();  
                pw.println(scanner.nextLine());  
            }  
		} catch (Exception e) {

		}
	}
	
	static class Thread1 extends Thread{
		private Socket socket;
		
		public Thread1(Socket socket){
			this.socket=socket;
		}

		@Override
		public void run() {
			try {
				
				while(true){
					InputStreamReader is=new InputStreamReader(socket.getInputStream(),"UTF-8");
					BufferedReader br=new BufferedReader(is);
					System.out.println(br.readLine());
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
