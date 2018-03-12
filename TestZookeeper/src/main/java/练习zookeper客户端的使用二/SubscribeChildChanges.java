package 练习zookeper客户端的使用二;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
@author junmeng.xu
@date  2016年9月21日下午2:12:25
 */
public class SubscribeChildChanges {

	public static void main(String[] args) throws Exception {
		
		ZkClient zc = new ZkClient("192.168.250.207:2181", 10000, 10000, new SerializableSerializer());
		
		System.out.println("connected ok !");
		
		zc.subscribeChildChanges("/node_8", new ZKChildListener());
		
		Thread.sleep(Integer.MAX_VALUE);
		
	}
	
}
class ZKChildListener implements IZkChildListener{

	public void handleChildChange(String parentPath, List<String> currentChilds)
			throws Exception {
		
		System.out.println("parentPath : " + parentPath);
		
		System.out.println("currentChilds : " + currentChilds.toString());
		
	}


	
}
