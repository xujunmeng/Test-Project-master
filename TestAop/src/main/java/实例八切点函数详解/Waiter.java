package 实例八切点函数详解;

public interface Waiter {
	@NeedTest
	void greetTo(String clientName);
	void serveTo(String clientName);
}
