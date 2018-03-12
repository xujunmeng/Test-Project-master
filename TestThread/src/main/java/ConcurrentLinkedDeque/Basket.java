package ConcurrentLinkedDeque;

import java.util.concurrent.ConcurrentLinkedDeque;
/**
@author junmeng.xu
@date  2016年8月19日上午10:07:14
 */

/**
 * 定义装苹果的篮子 
 */
public class Basket {

	//篮子，能够容纳3个苹果
	ConcurrentLinkedDeque<String> basket = new ConcurrentLinkedDeque<String>();
	
	//生产苹果，放入篮子
	public void produce() throws Exception{
		//put方法放入一个苹果，若basket满了，等到basket有位置
		basket.add("An apple 1");
	}
	
	//消费苹果，从篮子中取走
	public String consume() throws Exception{
		//take方法取出一个苹果，若basket为空，等到basket友苹果为止（获取并移除次队列的头部）
		return basket.pop();
	}
	
}
