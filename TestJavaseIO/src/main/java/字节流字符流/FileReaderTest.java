package 字节流字符流;

import java.io.FileReader;

/**
@author junmeng.xu
@date  2016年5月20日上午11:21:32
 */
public class FileReaderTest {

	public static void main(String[] args) throws Exception {
		
		FileReader fileReader = null;
		try {
			//创建字符输入流
			fileReader = new FileReader("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/FileReaderTest.java");
			
			//创建一个长度为32的“竹筒”
			char[] buff = new char[1024];
			
			//用于保存实际读取的字符数
			int hasRead = 0;
			
			//使用循环来重复“取水”过程
			while((hasRead = fileReader.read(buff)) > 0){
				//取出"竹筒"中水滴，将字符数组转换为字符串输出
				System.out.println(new String(buff, 0, hasRead));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fileReader != null){
				fileReader.close();
			}
		}
		
	}
	
}
