package org.liuyi.projects.personal.common.socket;  
  
import java.io.BufferedReader;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class Server {  
  
    private ServerSocket serverSocket;  
      
    public Server(){  
        try{  
            serverSocket = new ServerSocket(8088);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
      
    public void start(){  
        try{  
            System.out.println("等待客户端连接。。。");  
            //方法会产生阻塞，直到某个Socket连接，返回请求连接的Socket  
            Socket socket = serverSocket.accept();  
            System.out.println("客户端已连接！");  
            InputStream in = socket.getInputStream();  
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");  
            BufferedReader br = new BufferedReader(isr);  
            //System.out.println("客户端说：" + br.readLine());  
            //不断读取客户端数据  
            while(true){  
                System.out.println("客户端说：" + br.readLine());  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        Server server = new Server();  
        server.start();  
    }  
  
}  