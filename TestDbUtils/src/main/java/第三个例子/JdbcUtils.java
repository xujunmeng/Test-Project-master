package 第三个例子;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
@author junmeng.xu
@date  2016年6月23日下午4:14:37
 */
@SuppressWarnings("static-access")
public class JdbcUtils {
	private static DataSource ds;
	
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();  //map
	static{
		try{
			Properties prop = new Properties();
			InputStream in = JdbcUtils.class.getClassLoader().getSystemResourceAsStream("quantconfig.properties");
			prop.load(in);
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(prop);
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static DataSource getDataSource(){
		return ds;
	}
	
	public static Connection getConnection() throws SQLException{
		try{
			//得到当前线程上绑定的连接
			Connection conn = tl.get();
			if(conn==null){  //代表线程上没有绑定连接
				conn = ds.getConnection();
				tl.set(conn);
			}
			return conn;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
