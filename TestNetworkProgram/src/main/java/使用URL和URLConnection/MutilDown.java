package 使用URL和URLConnection;

import java.io.InputStream;
import java.io.RandomAccessFile;

/**
@author junmeng.xu
@date  2016年5月20日下午2:55:43
 */

//定义下载从start到end的内容的线程
class DownThread extends Thread{
	
	//定义字节数组（取水的竹筒）的长度
	private final int buff_len = 1024;
	
	//定义下载的起始点
	private long start;
	//定义下载的结束点
	private long end;
	
	//下载资源对应的输入流
	private InputStream inputStream;
	
	//将下载到的字节输出到randomAccessFile
	private RandomAccessFile randomAccessFile;
	
	//构造器，传入输入流，输出流和下载起始点，结束点
	public DownThread(long start, long end, InputStream inputStream, RandomAccessFile randomAccessFile){
		//输出该线程负责下载的字节位置
		System.out.println(start + "---->" + end);
		this.start = start;
		this.end = end;
		this.inputStream = inputStream;
		this.randomAccessFile = randomAccessFile;
	}
	
	@Override
	public void run() {
		try {
			inputStream.skip(start);
			randomAccessFile.seek(start);
			
			//定义读取输入流内容的缓存数组（竹筒）
			byte[] buff = new byte[1024];
			
			//本线程负责下载资源的大小
			long contentLen = end - start;
			
			//定义最多需要读取几次就可以完成本线程的下载
			long times = contentLen / buff_len + 4;
			
			//实际读取的字节数
			int hasRead = 0;
			
			for (int i = 0; i < times; i++) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
public class MutilDown {

}
