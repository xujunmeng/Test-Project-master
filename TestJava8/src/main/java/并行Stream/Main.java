package 并行Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
@author junmeng.xu
@date  2016年4月22日上午11:54:13
 */
public class Main {

	public static void main(String[] args) {
		//串行
		int max = 1000000;
		List<String> values = new ArrayList<String>(max);
		for(int i = 0; i<max ; i++){
			UUID randomUUID = UUID.randomUUID();
			values.add(randomUUID.toString());
		}
		
		long t0 = System.nanoTime();
		
		long count = values.stream().sorted().count();
		System.out.println(count);
		
		long t1 = System.nanoTime();
		
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		
		System.out.println(millis);
		
	}
	//并行
	@Test
	public void test(){
		int max = 1000000;
		List<String> values = new ArrayList<String>(max);
		for(int i = 0; i<max ; i++){
			UUID randomUUID = UUID.randomUUID();
			values.add(randomUUID.toString());
		}
		
		long t0 = System.nanoTime();
		
		long count = values.parallelStream().sorted().count();
		System.out.println(count);
		
		long t1 = System.nanoTime();
		
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		
		System.out.println(millis);
	}
	
}
