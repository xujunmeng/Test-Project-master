package junmeng.step3_2_connection_holder;

import junmeng.step3_connection_holder.ConnectionHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by james on 2017/9/14.
 */
public class SingleThreadConnectionHolderSafe {

    private static ThreadLocal<ConnectionHolder> localConnectionHolder = new ThreadLocal<>();

    public static void removeConnection(DataSource dataSource) {

        ConnectionHolder connectionHolder = getConnectionHolder();

        connectionHolder.removeConnection(dataSource);

    }

    public static Connection getConnection(DataSource dataSource) throws SQLException {

        ConnectionHolder connectionHolder = getConnectionHolder();

        Connection connection = connectionHolder.getConnection(dataSource);

        return connection;
    }

    private static ConnectionHolder getConnectionHolder() {

        ConnectionHolder connectionHolder = localConnectionHolder.get();

        if (connectionHolder == null) {
            connectionHolder = new ConnectionHolder();
            localConnectionHolder.set(connectionHolder);
        }
        return connectionHolder;
    }

}
