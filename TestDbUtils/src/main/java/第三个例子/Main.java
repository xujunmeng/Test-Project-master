package 第三个例子;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * @author junmeng.xu
 * @date 2016年6月24日下午4:25:58
 */
public class Main {

	@SuppressWarnings("deprecation")
	@Test
	public void find() throws SQLException {
		String dt = "2016-02-16";
		String tick = "600578";
		String obj = "K";
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select " + obj
				+ " from comm_idx_tech_event_his_a where dt = '" + dt
				+ "' and tick = '" + tick + "' and " + obj + " is not null";
		List<Object[]> query = runner.query(sql, new ArrayListHandler());
		if (query.size() == 0) {
			System.out.println("asdafs");
		}

	}

	@Test
	public void update() throws SQLException {
		String dt = "2016-02-16";
		String tick = "600578";
		String obj = "K";
		String sqlInsert = "update comm_idx_tech_event_his_a t set " + obj
				+ " = 50 where dt = '" + dt + "' and tick = '" + tick + "'";
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		int update = runner.update(sqlInsert);
		System.out.println(update);
	}
	
	@Test
	public void update2() throws SQLException{
		Collection<String> indexs = Lists.newArrayList();
		indexs.add("M99121"+"=1");
		indexs.add("M99131"+"=1");
		String dt = "2016-02-16";
		String tick = "600578";
		String allIndex = StringUtils.join(indexs, ",");
		String sqlUpdate = "update comm_idx_tech_event_his_a t set " + allIndex + " where tick = '" + tick + "'and dt = '" + dt + "'" ;
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		int update = runner.update(sqlUpdate);
		System.out.println(update);
	}

}
