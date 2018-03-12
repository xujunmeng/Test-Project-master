package 字节流字符流;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
@author junmeng.xu
@date  2016年5月20日上午11:10:44
 */
public class FileInputStreamTest {

	public static void main(String[] args) throws Exception {
		
		//创建字节输入流
		FileInputStream fileInputStream = new FileInputStream("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/FileInputStreamTest.java");
		
		//创建一个长度为1024的“竹筒”
		byte[] buff = new byte[1024];
		
		//用于保存实际读取的字节数
		int hasRead = 0;
		
		//使用循环来重复“取水”过程
		while((hasRead = fileInputStream.read(buff)) > 0){
			//取出“竹筒”中水滴（字节），将字节数组转换成字符串输出！
			System.out.println(new String(buff, 0, hasRead));
		}
		
		//关闭文件输入流，放在finally块里更安全
		fileInputStream.close();
		
	}
	
}
