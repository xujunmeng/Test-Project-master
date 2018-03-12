package 实例一;

import org.junit.Test;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.CoreAnnotated;

/**
@author junmeng.xu
@date  2016年8月24日下午1:33:52
 */
public class Main {

	
	@Test
	public void test1(){
		
		Core core = new Core();
		
		int addLookback = core.addLookback();
		
		System.out.println(addLookback);
	}
	
	@Test
	public void test2(){
		CoreAnnotated coreAnnotated = new CoreAnnotated();
		int addLookback = coreAnnotated.addLookback();
		System.out.println(addLookback);
	}
	
	@Test
	public void test3(){
		
	}
	
}
