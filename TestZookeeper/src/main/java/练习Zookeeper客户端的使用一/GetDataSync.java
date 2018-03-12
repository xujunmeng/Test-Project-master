package 练习Zookeeper客户端的使用一;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;

/**
@author junmeng.xu
@date  2016年9月20日下午3:08:39
 */
public class GetDataSync implements Watcher {

	private static ZooKeeper zooKeeper;
	
	private static Stat stat = new Stat();
	
	public static void main(String[] args) throws Exception {
		
		zooKeeper = new ZooKeeper("192.168.250.207:2181", 5000, new GetDataSync());
		
		System.out.println("zooKeeper.getState() : " + zooKeeper.getState().toString());
		
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	public void process(WatchedEvent event) {
		
		if(event.getState() == KeeperState.SyncConnected){
			if(event.getType() == EventType.None && null == event.getPath()){
				doSomething(zooKeeper);
			} else {
				if(event.getType() == EventType.NodeDataChanged){
					try {
						System.out.println("process.zooKeeper.getData() : " + new String(zooKeeper.getData(event.getPath(), true, stat)));
						System.out.println("stat : " + stat);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	private void doSomething(ZooKeeper zooKeeper){
		try {
			System.out.println("doSomething.zooKeeper.getData() : " + new String(zooKeeper.getData("/node_5", true, stat)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
