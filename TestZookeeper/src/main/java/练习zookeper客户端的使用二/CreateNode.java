package 练习zookeper客户端的使用二;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

/**
@author junmeng.xu
@date  2016年9月21日下午1:45:55
 */
public class CreateNode {

	public static void main(String[] args) {
		
		ZkClient zc = new ZkClient("192.168.250.207:2181", 10000, 10000, new SerializableSerializer());
		
		System.out.println("connected ok !");
		
		User u = new User();
		u.setId(1);
		u.setName("test");
		String path = zc.create("/node_6", u, CreateMode.PERSISTENT);
		System.out.println("created path : " + path);
		
		
	}
	
}
