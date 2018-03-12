package 第一次操作;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
@author junmeng.xu
@date  2016年7月1日下午3:24:13
 */
public class BadCode {

	public static void main(String[] args) throws Exception {
		Connection conn = null;
		String sql = "insert into test (name, city, phone) values(?, ? ,?)";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/batch?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true", "root", "123456");
		conn.setAutoCommit(false);
		if(!conn.isClosed()){
			System.out.println("数据库连接成功");
		}
		long start = System.currentTimeMillis();
		PreparedStatement ps = conn.prepareStatement(sql);
		List<Employee> list = getObjs();
		for(Employee e : list){
			ps.setString(1, e.getName());
			ps.setString(2, e.getCity());
			ps.setString(3, e.getPhone());
			ps.addBatch();
		}
		ps.executeBatch();
		conn.commit();
		conn.setAutoCommit(true);
		System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
		ps.close();
		conn.close();
		
		
		
	}
	
	public static List<Employee> getObjs(){
		List<Employee> result = new ArrayList<Employee>();
		for (int i = 0; i < 1000; i++) {
			Employee entity = new Employee();
			entity.setName("熊二"+i);
			entity.setCity("上海"+i);
			entity.setPhone("你猜"+i);
			result.add(entity);
		}
		return result;
	}


	@Test
	public void test() throws Exception {
		List<Employee> list = getObjs();
		String values = "";
		for (Employee obj : list){
			String name = obj.getName();
			String city = obj.getCity();
			String phone = obj.getPhone();
			values = values + "('" + name + "','" + city + "'," + "'" + phone + "'),";
		}
		String substring = values.substring(0, values.length() - 1);
		Connection conn = null;
		String sql = "insert into test (name, city, phone) values" + substring;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/batch", "root", "123456");
		Statement statement = conn.createStatement();
		long start = System.currentTimeMillis();
		int i = statement.executeUpdate(sql);
		System.out.println("执行时间" + (System.currentTimeMillis() - start) + "毫秒");
	}



}
