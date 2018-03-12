package 管道流;

import java.io.BufferedReader;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
@author junmeng.xu
@date  2016年5月19日下午2:05:51
 */
class ReaderThread extends Thread{
	
	private PipedReader pipedReader;
	//用于包装管道流的BufferReader对象
	private BufferedReader bufferedReader;
	public ReaderThread(){
		
	}
	public ReaderThread(PipedReader pipedReader){
		this.pipedReader = pipedReader;
		this.bufferedReader = new BufferedReader(pipedReader);
	}
	@Override
	public void run() {
		String buf = null;
		try {
			//逐行读取管道输入流中的内容
			while((buf = bufferedReader.readLine()) != null){
				System.out.println(buf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bufferedReader != null){
					bufferedReader.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
class WriterThread extends Thread{
	
	String[] books = new String[]{
			"Struts2权威指南",
			"ROR敏捷开发指南",
			"基于J2EE的Ajax宝典",
			"轻量级J2EE企业应用指南"
	};
	private PipedWriter pipedWriter;
	public WriterThread(){
		
	}
	public WriterThread(PipedWriter pipedWriter){
		this.pipedWriter = pipedWriter;
	}
	@Override
	public void run() {
		try {
			//循环100次，向管道输出流中写入100个字符串
			for (int i = 0; i < 100; i++) {
				pipedWriter.write(books[i % 4] + "\n");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pipedWriter != null){
					pipedWriter.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
public class PipedCommunicationTest {

	public static void main(String[] args) {
		
		PipedWriter pipedWriter = null;
		PipedReader pipedReader = null;
		try {
			//分别创建两个独立的管道输出流，输入流
			pipedWriter = new PipedWriter();
			pipedReader = new PipedReader();
			//连接管道输出流，输入流
			pipedWriter.connect(pipedReader);
			//将连接好的管道流分别传入两个线程   就可以让两个线程通过管道流进行通信
			new WriterThread(pipedWriter).start();
			new ReaderThread(pipedReader).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
