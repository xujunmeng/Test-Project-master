package 练习zookeper客户端的使用二;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.data.Stat;

/**
@author junmeng.xu
@date  2016年9月21日下午1:58:08
 */
public class GetData {

	public static void main(String[] args) {
		
		ZkClient zc = new ZkClient("192.168.250.207:2181", 10000, 10000, new SerializableSerializer());
		
		System.out.println("connected ok !");
		
		Stat stat = new Stat();
		
		User u = zc.readData("/node_6", stat);
		System.out.println(u.toString());
		System.out.println(stat);
		
	}
	
}
