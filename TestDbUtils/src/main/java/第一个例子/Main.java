package 第一个例子;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

/**
 * @author junmeng.xu
 * @date 2016年6月23日下午4:08:41
 */
public class Main {

	@Test
	public void insert() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";
		Object params[] = { 3, "aaa", "456", "bb@sina.com", new Date() };
		queryRunner.update(sql, params);
	}

	@Test
	public void find() throws SQLException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users where id=?";
		User user = (User) runner.query(sql, 2, new BeanHandler(User.class));
		System.out.println(user.getEmail());
	}
	
	@Test
	public void findCount() throws SQLException{
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select count(1) from users where id = ?";
		Object params[] = {2};
		Object[] query = runner.query(sql, params, new ArrayHandler());
		for (Object object : query) {
			System.out.println(object);
		}
	}
      
    @Test  
    public void update() throws SQLException{  
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());  
        String sql = "update users set email=? where id=?";  
        Object params[] = {"aaaaaa@sina.com",1};  
        runner.update(sql, params);  
    }  
      
    @Test  
    public void delete() throws SQLException{  
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());  
        String sql = "delete from users where id=?";  
        runner.update(sql, 1);  
    }  
      
      
    @Test  
    public void getAll() throws Exception{  
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());  
        String sql = "select * from users";  
        List list = (List) runner.query(sql, new BeanListHandler(User.class));  
        System.out.println(list);  
    }  
      
    @Test  
    public void batch() throws SQLException{  
        QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());  
        String sql =  "insert into users(id,name,password,email,birthday) values(?,?,?,?,?)";  
        Object params[][] = new Object[3][5];  
        for(int i=0;i<params.length;i++){  //3  
            params[i] = new Object[]{i+1,"aa"+i,"123",i + "@sina.com",new Date()};  
        }  
        runner.batch(sql, params);  
    } 
}
