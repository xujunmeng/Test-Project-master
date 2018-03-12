package 练习Zookeeper客户端的使用一;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author junmeng.xu
 * @date 2016年9月20日下午1:53:40
 */
public class GetChildrenSync implements Watcher {

	private static ZooKeeper zooKeeper;

	public static void main(String[] args) throws Exception {
		zooKeeper = new ZooKeeper("192.168.250.207:2181", 5000,
				new GetChildrenSync());
		
		System.out.println("zooKeeper.getState() : "
				+ zooKeeper.getState().toString());
		
		Thread.sleep(Integer.MAX_VALUE);
		
	}

	public void process(WatchedEvent event) {

		if (event.getState() == KeeperState.SyncConnected) {
			if (event.getType() == EventType.None && null == event.getPath()) {
				doSomething(zooKeeper);
			} else {
				if (event.getType() == EventType.NodeChildrenChanged) {
					try {
						System.out.println(zooKeeper.getChildren(event.getPath(), true));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void doSomething(ZooKeeper zooKeeper){
		try {
			List<String> children = zooKeeper.getChildren("/", true);
			System.out.println("children : " + children);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
