package 源码实例ZooKeeper客户端的使用一;
import java.security.NoSuchAlgorithmException;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;


public class CreateAuthString {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(DigestAuthenticationProvider.generateDigest("jike:123456"));
	}
}
