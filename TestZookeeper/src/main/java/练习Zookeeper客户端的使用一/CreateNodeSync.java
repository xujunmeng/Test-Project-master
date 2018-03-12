package 练习Zookeeper客户端的使用一;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
@author junmeng.xu
@date  2016年9月19日下午2:01:47
 */
public class CreateNodeSync implements Watcher {

	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws Exception {
		
		zooKeeper = new ZooKeeper("192.168.250.207", 5000, new CreateNodeSync());
		
		System.out.println("zooKeeper.getState() : " + zooKeeper.getState());
		
		Thread.sleep(Integer.MAX_VALUE);
		
	}
	
	public void process(WatchedEvent event) {
		
		System.out.println("收到的事件 : " + event);
		
		if(event.getState() == KeeperState.SyncConnected){
			if(event.getType() == EventType.None && null == event.getPath()){
				doSomething();
			}
		}
		
	}

	public void doSomething(){
		
		try {
			
			String path = zooKeeper.create("/node_4", "123".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			
			System.out.println("return path : " + path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
