package dao;

import model.User;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
@author junmeng.xu
@date  2016年1月11日下午3:47:01
 */
public class TestDao extends BaseTest {

	@Autowired
	UserDao userDao;

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	@Test
	public void testgetUsers(){
		List<User> users = userDao.getUsers(0, -1);
		System.out.println(users);
	}

	@Test
	public void testAddUesr() {
		User user = new User();
		user.setId(1);
		user.setUsername("xjmxjm");
		user.setPassword("123");
		long start = System.currentTimeMillis();
		userDao.addUser(user);
		System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
		System.out.println("添加成功");
	}

	// mybatis  执行时间1108毫秒
	@Test
	public void testBatch(){
		List<User> list = getObjs();
		long start = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++){
			userDao.insert2(list.get(i));
			if(i == 10){
				int b = 2/0;
			}
		}
		System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
	}

	// jdbc batch  执行时间1196毫秒
	@Test
	public void ttestjdbc() throws Exception{
		Connection conn = null;
		String sql = "insert into user (id, username, password) values(?, ? ,?)";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/springmybatis?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true", "root", "123456");
		conn.setAutoCommit(false);
		if(!conn.isClosed()){
			System.out.println("数据库连接成功");
		}
		List<User> list = getObjs();
		PreparedStatement ps = conn.prepareStatement(sql);
		long time1 = System.currentTimeMillis();
		for(User e : list){
			ps.setInt(1, e.getId());
			ps.setString(2, e.getUsername());
			ps.setString(3, e.getPassword());
			ps.addBatch();
		}
		long time2 = System.currentTimeMillis();
		System.out.println("1执行时间" + (time2 - time1) + "毫秒");
		ps.executeBatch();
		long time3 = System.currentTimeMillis();
		System.out.println("2执行时间" + (time3 - time2) + "毫秒");
		conn.commit();
		long time4 = System.currentTimeMillis();
		System.out.println("2执行时间" + (time4 - time3) + "毫秒");
		conn.setAutoCommit(true);
		long time5 = System.currentTimeMillis();
		System.out.println("执行时间" + (time5 - time4) + "毫秒");
		ps.close();
		conn.close();

	}

	@Test
	public void testkas() throws Exception{
		List<User> list = getObjs();
		String values = "";
		for (User obj : list){
			int id = obj.getId();
			String username = obj.getUsername();
			String password = obj.getPassword();
			values = values + "(" + id + ",'" + username + "'," + "'" + password + "'),";
		}
		String substring = values.substring(0, values.length() - 1);
		Connection conn = null;
		String sql = "insert into user (id, username, password) values" + substring;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/springmybatis", "root", "123456");
		Statement statement = conn.createStatement();
		long start = System.currentTimeMillis();
		statement.executeUpdate(sql);
		System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
		userDao.insert3(sql);
	}



	@Test
	public void testinsertBatch() throws Exception{
		List<User> list = getObjs();
		int n=list.size();
		long start = System.currentTimeMillis();
		// 新获取一个模式为BATCH，自动提交为false的session
		// 如果自动提交设置为true,将无法控制提交的条数，改为最后统一提交，可能导致内存溢出
		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, true);
		for (int i = 0; i < n; i++){
			session.insert("dao.UserDao.addUser", list.get(i));
		}
		session.commit();
		session.clearCache();
		System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
		session.clearCache();

	}

	@Test
	public void test22(){
		User user = new User();
		user.setId(1);
		user.setUsername("sdf");
		user.setPassword("asdfadsf");
		SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		session.insert("dao.UserDao.addUser", user);
		session.commit();
		session.clearCache();
	}

	// 测试事务
	@Test
	public void testTX(){
		User user = new User();

		long start = System.currentTimeMillis();



		System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
	}



	public static List<User> getObjs(){
		List<User> result = new ArrayList<User>();
		for (int i = 0; i < 10000; i++) {
			User entity = new User();
			entity.setId(i);
			entity.setUsername("上海" + i);
			entity.setPassword("你猜" + i);
			entity.setPassword1("你猜" + i);
			entity.setPassword2("你猜" + i);
			entity.setPassword3("你猜" + i);
			entity.setPassword4("你猜" + i);
			entity.setPassword5("你猜" + i);
			entity.setPassword6("你猜" + i);
			entity.setPassword7("你猜" + i);
			entity.setPassword8("你猜" + i);
			result.add(entity);
		}
		return result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
}
