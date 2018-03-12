package simple;

import org.easymock.EasyMock;
import org.junit.Test;

/**
@author junmeng.xu
@date  2016年4月26日下午6:26:41
 */
public class TestStudentApplication {

	
	
	@Test
	public void testdoMethod(){
		//•使用 EasyMock 生成 Mock 对象；
		IStudent student = EasyMock.createMock(IStudent.class);
		//设定 Mock 对象的预期行为和输出
		EasyMock.expect(student.doMethod1()).andReturn("a").times(1);
		//将 Mock 对象切换到 Replay 状态
		EasyMock.replay(student);
		//调用 Mock 对象方法进行单元测试
		StudentApplication application = new StudentApplication();
		application.setStudent(student);
		
		String getStr = application.doMethod();
		//对 Mock 对象的行为进行验证
		String str = "abc";
		
		EasyMock.verify(student);
	}
	
}
