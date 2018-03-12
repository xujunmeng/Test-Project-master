package 字节流字符流;

import java.io.FileReader;
import java.io.FileWriter;

/**
@author junmeng.xu
@date  2016年5月20日下午1:46:40
 */
public class FileWriterTest {

	public static void main(String[] args) throws Exception {
		
		FileReader fileReader = new FileReader("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/FileWriterTest.java");
		FileWriter fileWriter = new FileWriter("D:/javaWorkLianxi/TestJavaseIO/src/main/java/字节流字符流/FileWriterTestBak.java");
		
		char[] buff = new char[1024];
		int hasRead = 0;
		while((hasRead = fileReader.read(buff)) > 0){
			fileWriter.write(buff, 0, hasRead);
		}
		fileReader.close();
		fileWriter.close();
	}
	
}
