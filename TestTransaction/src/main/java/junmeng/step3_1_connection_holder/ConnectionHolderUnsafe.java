package junmeng.step3_1_connection_holder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by james on 2017/9/7.
 */
public class ConnectionHolderUnsafe {

    private static Map<DataSource, Connection> connectionMap = new HashMap<>();

    public static Connection  getConnection(DataSource dataSource) throws SQLException {
        Connection connection = connectionMap.get(dataSource);
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
            connectionMap.put(dataSource, connection);
        }
        return connection;
    }

    public static void removeConnection(DataSource dataSource) {
        connectionMap.remove(dataSource);
    }

}
