package 实例九一个简单切面的配置;

import 实例八切点函数详解.NeedTest;

public interface Waiter {
	@NeedTest
	void greetTo(String clientName);
	void serveTo(String clientName);
}
