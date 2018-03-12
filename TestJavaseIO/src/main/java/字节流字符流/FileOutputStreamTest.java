package 字节流字符流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
@author junmeng.xu
@date  2016年5月20日下午1:38:57
 */
public class FileOutputStreamTest {

	public static void main(String[] args) throws Exception {
		
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		
		try {
			//创建字节输入流
			fileInputStream = new FileInputStream("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/FileOutputStreamTest.java");
			
			//创建字节输出流
			fileOutputStream = new FileOutputStream("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/FileOutputStreamTestBak.java");
			
			byte[] buff = new byte[1024];
			
			int hasRead = 0;
			
			//循环从输入流中取出数据
			while((hasRead = fileInputStream.read(buff)) > 0){
				//每读取一次，即写入文件输出流，读了多少，就写多少
				fileOutputStream.write(buff, 0, hasRead);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fileInputStream.close();
			fileOutputStream.close();
		}
		
	}
	
}
