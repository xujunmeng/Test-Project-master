package optional;

import org.junit.Test;

import java.util.Optional;

/**
@author junmeng.xu
@date  2016年4月29日上午10:15:43
 */
public class Main {
	@Test
	public void testd2d(){
		Optional<String> of = Optional.of("asdfasdf");
		String string = of.toString();
		System.out.println(string);
		Optional<String> optional = Optional.of(null);
	}
	
	@Test
	public void test(){
		Optional<String> optional = Optional.ofNullable("asdfasdf");
		//isPresent方法用来检查Optional实例中是否包含值
		if(optional.isPresent()){
			String string = optional.get();
			System.out.println(string);
		}
	}
	@Test
	public void test2(){
		Optional<String> optional = Optional.ofNullable(null);
		String orElse = optional.orElse("asdfasdfasdfasdfasdf");
		System.out.println(orElse);
	}

	@Test
	public void test3(){
		Double a = null;
		Double aDouble = Optional.ofNullable(a).orElse(0D);
		System.out.println(aDouble);
	}
	
}
