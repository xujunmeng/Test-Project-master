package 练习zookeper客户端的使用二;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
@author junmeng.xu
@date  2016年9月21日下午1:56:15
 */
public class GetChild {

	public static void main(String[] args) {
		
		ZkClient zc = new ZkClient("192.168.250.207:2181", 10000, 10000, new SerializableSerializer());
		
		System.out.println("connected ok !");
		
		List<String> cList = zc.getChildren("/");
		
		System.out.println(cList.toString());
		
	}
	
}
