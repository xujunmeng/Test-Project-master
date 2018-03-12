package TCP协议通信的实现;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
@author junmeng.xu
@date  2016年8月12日下午2:34:20
 */
public class Service {

	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(8000);
		
		while(true){
			
			Socket s = ss.accept();
			
			PrintStream ps = new PrintStream(s.getOutputStream());
			
			ps.println("您好，你收到了服务端的信息了哈哈哈哈哈哈");
			
			ps.close();
			
			s.close();
			
		}
		
		
		
	}
	
}
