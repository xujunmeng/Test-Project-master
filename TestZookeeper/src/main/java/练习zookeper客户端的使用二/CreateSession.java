package 练习zookeper客户端的使用二;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
@author junmeng.xu
@date  2016年9月21日下午1:34:51
 */
public class CreateSession {

	public static void main(String[] args) {
		
		ZkClient zc = new ZkClient("192.168.250.207:2181", 10000, 10000, new SerializableSerializer());
		
		System.out.println("connected ok !");
		
		
		
	}
	
}
