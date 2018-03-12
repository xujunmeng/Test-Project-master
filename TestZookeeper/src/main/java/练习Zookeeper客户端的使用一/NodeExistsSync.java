package 练习Zookeeper客户端的使用一;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.ZooKeeper;

/**
@author junmeng.xu
@date  2016年9月20日下午4:32:52
 */
public class NodeExistsSync implements Watcher {

	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws Exception {
		
		zooKeeper = new ZooKeeper("192.168.250.207:2181", 5000, new NodeExistsSync());
		
		System.out.println("zooKeeper.getState() : " + zooKeeper.getState().toString());
		
		Thread.sleep(Integer.MAX_VALUE);
		
	}
	
	public void process(WatchedEvent event) {
		
		if(event.getState() == KeeperState.SyncConnected){
			if(event.getType() == EventType.None && null == event.getPath()){
				doSomething(zooKeeper);
			} else {
				try {
					if(event.getType() == EventType.NodeCreated){
						System.out.println(event.getPath() + " created ");
						System.out.println(zooKeeper.exists(event.getPath(), true));
					} else if (event.getType() == EventType.NodeDataChanged){
						System.out.println(event.getPath() + " updated ");
						System.out.println(zooKeeper.exists(event.getPath(), true));
					} else if (event.getType() == EventType.NodeDeleted){
						System.out.println(event.getPath() + " deleted ");
						System.out.println(zooKeeper.exists(event.getPath(), true));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private void doSomething(ZooKeeper zooKeeper){
		try {
			Stat stat = zooKeeper.exists("/node_5", true);
			System.out.println("stat : " + stat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
