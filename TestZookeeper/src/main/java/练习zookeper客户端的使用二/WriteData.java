package 练习zookeper客户端的使用二;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
@author junmeng.xu
@date  2016年9月21日下午2:08:22
 */
public class WriteData {

	public static void main(String[] args) {
		
		ZkClient zc = new ZkClient("192.168.250.207:2181", 10000, 10000, new SerializableSerializer());
		
		System.out.println("connected ok !");
		
		User u = new User();
		u.setId(2);
		u.setName("test2");
		zc.writeData("/node_5", u, -1);
		
		
	}
	
}
