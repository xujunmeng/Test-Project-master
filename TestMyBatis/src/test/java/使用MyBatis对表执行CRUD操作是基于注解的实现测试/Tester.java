package 使用MyBatis对表执行CRUD操作是基于注解的实现测试;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import 使用MyBatis对表执行CRUD操作是基于注解的实现.UserMapperI;
import 工具方法.MyBatisUtil;

import com.junmeng.entity.User;

/**
 * @author junmeng.xu
 * @date 2016年8月19日下午3:00:23
 */
public class Tester {
	@Test
	public void testAdd() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
		// 得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
		UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
		User user = new User();
		user.setName("用户xdp");
		user.setAge(20);
		int add = mapper.add(user);
		// 使用SqlSession执行完SQL之后需要关闭SqlSession
		sqlSession.close();
		System.out.println(add);
	}
}
