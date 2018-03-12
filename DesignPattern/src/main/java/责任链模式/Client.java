package 责任链模式;
/**
@author junmeng.xu
@date  2016年8月11日下午3:20:27
 */
public class Client {

	public static void main(String[] args) {
		
		//先要组装责任链
		Handler h1 = new GeneralManager();
		
		Handler h2 = new DeptManager();
		
		Handler h3 = new ProjectManager();
		
		h3.setSuccessor(h2);
		h2.setSuccessor(h1);
		
		//开始测试

        String test3 = h3.handleFeeRequest("张三", 700);
        
        System.out.println("test3 = " + test3);
        
        String test4 = h3.handleFeeRequest("张三", 1700);
        
        System.out.println("test4 = " + test4);
        
        System.out.println("---------------------------------------");
		
	}
	
}
