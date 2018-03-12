package 使用InetAddress;

import java.net.InetAddress;

/**
@author junmeng.xu
@date  2016年5月20日上午9:56:07
 */
public class InetAddressTest {

	public static void main(String[] args) throws Exception {
		
		//根据主机名来获取对应的InetAddress实例
		InetAddress inetAddress = InetAddress.getByName("www.crazyit.org");
		//判断是否可达
		System.out.println("crazyit是否可达 : " + inetAddress.isReachable(2000));
		//获取该InetAddress实例的IP字符串
		System.out.println(inetAddress.getHostAddress());
		
		
		//根据原始IP地址来获取对应的InetAddress实例
		InetAddress inetAddress2 = InetAddress.getByAddress(new byte[]{(byte) 192,(byte) 168,1,43});
		System.out.println("本机是否可达 : " + inetAddress2.isReachable(2000));
		//获取该InetAddress实例对应的全限定域名
		System.out.println(inetAddress2.getCanonicalHostName());
		
	}
	
}
