package 字节流字符流.test1;

import java.io.FileInputStream;

/**
@author junmeng.xu
@date  2016年5月20日上午11:29:10
 */
public class FileInputStreamTest {

	public static void main(String[] args) throws Exception {
		
		FileInputStream fileInputStream = new FileInputStream("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/test1/FileInputStreamTest.java");
		
		byte[] buff = new byte[1024];
		
		int hasRead = 0;
		
		while((hasRead = fileInputStream.read(buff)) > 1){
			String str = new String(buff, 0, hasRead);
			System.out.println(str);
		}
		fileInputStream.close();
		
	}
	
}
