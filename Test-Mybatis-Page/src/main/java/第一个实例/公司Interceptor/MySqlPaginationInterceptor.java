package 第一个实例.公司Interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class MySqlPaginationInterceptor extends AbstractPaginationInterceptor {

	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append(sql);
		pagingSelect.append(" LIMIT ");
		pagingSelect.append(offset);
		pagingSelect.append(" , ");
		pagingSelect.append(limit);
		return pagingSelect.toString();
	}
}
