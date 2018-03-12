package junmeng.step3_1_connection_holder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by james on 2017/9/11.
 */
public class TransactionManagerUnsafe {

    private DataSource dataSource;

    public TransactionManagerUnsafe(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return ConnectionHolderUnsafe.getConnection(dataSource);
    }

    public final void start() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    public final void commit() throws SQLException {
        Connection connection = getConnection();
        connection.commit();
    }

    public final void rollback() {
        Connection connection = null;
        try {
            System.out.println("rollback() begin : " + Thread.currentThread().getName());
            connection = getConnection();
            System.out.println("rollback() middle : " + Thread.currentThread().getName() + ", connection : " + connection);
            connection.rollback();
            System.out.println("rollback() end : " + Thread.currentThread().getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final void close() {
        Connection connection = null;
        try {
            System.out.println("close() begin : " + Thread.currentThread().getName());
            connection = getConnection();
            System.out.println("close() middle : " + Thread.currentThread().getName() + ", connection : " + connection);
            connection.setAutoCommit(true);
            connection.setReadOnly(false);
            connection.close();
            ConnectionHolderUnsafe.removeConnection(dataSource);
            System.out.println("close() end : " + Thread.currentThread().getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
