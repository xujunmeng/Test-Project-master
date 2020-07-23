package callableFuture.b;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author junmeng.xu
 * @date 2016年4月3日下午12:58:10
 */
public class SingleMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String begin = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间
		
		
		List<String> list = addList();
		int count = list.size();
		int times = count / 20 + (count % 20 == 0 ? 0 : 1);
		
		for (int i = 0; i < times; i++) {
					System.out.print(Thread.currentThread().getName() + " : ");
				}
		String end = simpleDateFormat.format(new Date());// new Date()为获取当前系统时间
		System.out.println();
		System.out.println(begin.toString());
		System.out.println(end.toString());
		
	}

	public static List<String> addList() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 2000000; i++) {
			list.add("" + i);
		}
		return list;
	}

}
