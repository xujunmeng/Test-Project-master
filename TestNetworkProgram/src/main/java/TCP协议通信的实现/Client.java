package TCP协议通信的实现;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
@author junmeng.xu
@date  2016年8月12日下午2:42:25
 */
public class Client {

	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket("192.168.1.43" , 8000);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String line = br.readLine();
		
		System.out.println("来自服务器的数据 ： " + line);
		
		br.close();
		socket.close();
		
	}
	
}
