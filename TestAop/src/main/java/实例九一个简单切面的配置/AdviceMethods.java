package 实例九一个简单切面的配置;

import org.aspectj.lang.ProceedingJoinPoint;


public class AdviceMethods {
	public void preGreeting() {
		System.out.println("--how are you!--");
	}

	public void postGreeting(){
		System.out.println("bye bye ...");
	}

    //后置增强对应方法
	public void afterReturning(String val){
	   System.out.println("----afterReturning()----");
	   System.out.println("returnValue:"+val);
	   System.out.println("----afterReturning()----");
	}

    //环绕增强对应方法
	public void aroundMethod(ProceedingJoinPoint pjp){
	   System.out.println("----aroundMethod()----");
	   System.out.println("args[0]:"+pjp.getArgs()[0]);
	   System.out.println("----aroundMethod()----");
	}

    //抛出异常增强
	public void afterThrowingMethod(IllegalArgumentException iae){
	   System.out.println("----afterThrowingMethod()----");
	   System.out.println("exception msg:"+iae.getMessage());
	   System.out.println("----afterThrowingMethod()----");
	}

    //final增强
	public void afterMethod(){
	   System.out.println("----afterMethod()----");
	}
	

	
      //------------绑定连接点参数----------//
	public void bindParams(int num,String name){
	   System.out.println("----bindParams()----");
	   System.out.println("name:"+name);
	   System.out.println("num:"+num);
	   System.out.println("----bindParams()----");
	}
}
