package 练习Zookeeper客户端的使用一;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

/**
@author junmeng.xu
@date  2016年9月19日下午1:55:12
 */
public class CreateSession implements Watcher {

	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws Exception {
		
		zooKeeper = new ZooKeeper("192.168.250.207:2181", 5000, new CreateSession());
		
		System.out.println("zooKeeper.getState() : " + zooKeeper.getState());
		
		Thread.sleep(Integer.MAX_VALUE);
		
	}
	
	public void process(WatchedEvent event) {
		
		System.out.println("收到的事件 : " + event);
		
		System.out.println("event.getState() : " + event.getState());
		
		if(event.getState() == KeeperState.SyncConnected){
			
			if(event.getType() == EventType.None && null == event.getPath()){
				
				doSomething();
				
			}
			
		}
		
	}
	
	public void doSomething(){
		
		System.out.println("do something");
		
	}

}
