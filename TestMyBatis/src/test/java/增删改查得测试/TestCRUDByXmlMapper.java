package 增删改查得测试;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.junmeng.entity.User;

import 工具方法.MyBatisUtil;

/**
@author junmeng.xu
@date  2016年8月19日下午2:34:12
 */
public class TestCRUDByXmlMapper {

	@Test
	public void testAdd(){
		
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		
		/**
		 * 映射sql得标识字符串,
		 * com.junmeng.mapping.userMapper是userMapper.xml文件中mapper标签得namespace属性得值
		 * addUser是insert标签得ID属性值，通过insert标签得ID属性值就可以找到要执行得sql
		 */
		String statement = "com.junmeng.mapping.userMapper.addUser"; //映射sql得标识字符串
		User user = new User();
		user.setName("徐军猛");
		user.setAge(20);
		
		//执行插入操作
		int retResult = sqlSession.insert(statement, user);
		
		//使用sqlsession执行完sql之后需要关闭sqlsession
		sqlSession.close();
		System.out.println(retResult);
	}
	
}
