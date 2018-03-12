package 练习zookeper客户端的使用二;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
@author junmeng.xu
@date  2016年9月21日下午2:00:16
 */
public class NodeDel {

	public static void main(String[] args) {
		
		ZkClient zc = new ZkClient("192.168.250.207:2181", 10000, 10000, new SerializableSerializer());
		
		System.out.println("connected ok !");
		
		boolean b1 = zc.delete("/node_4");
//		boolean b2 = zc.deleteRecursive("/node_4");
		System.out.println("b1 : " + b1);
//		System.out.println("b2 : " + b2);
		
	}
	
}
