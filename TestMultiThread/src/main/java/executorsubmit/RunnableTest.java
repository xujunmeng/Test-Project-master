package executorsubmit;


/**
@author junmeng.xu
@date  2016年2月22日下午5:04:40
 */
public class RunnableTest implements Runnable {

	private String taskName;
	
	public RunnableTest(String taskName) {
		this.taskName = taskName;
	}


	public void run() {
		System.out.println("inside : " + taskName);
	}

}
