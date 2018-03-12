package 字节流字符流.test1;

import java.io.FileReader;

/**
@author junmeng.xu
@date  2016年5月20日下午1:28:24
 */
public class FileReaderTest {

	public static void main(String[] args) throws Exception {
		
		FileReader fileReader = new FileReader("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/test1/FileReaderTest.java");
		
		char[] buff = new char[1024];
		
		int hasRead = 0;
		
		while((hasRead = fileReader.read(buff)) > 0){
			String str = new String(buff, 0, hasRead);
			System.out.println(str);
		}
		
	}
	
}
