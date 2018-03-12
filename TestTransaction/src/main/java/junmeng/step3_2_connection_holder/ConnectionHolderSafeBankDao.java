package junmeng.step3_2_connection_holder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by james on 2017/9/14.
 */
public class ConnectionHolderSafeBankDao {

    private DataSource dataSource;

    public ConnectionHolderSafeBankDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void withdraw(int bankId, int amount) throws SQLException {

        System.out.println("withdraw() begin : " + Thread.currentThread().getName());

        Connection connection = SingleThreadConnectionHolderSafe.getConnection(dataSource);
        PreparedStatement selectStatement = connection.prepareStatement("SELECT BANK_AMOUNT FROM BANK_ACCOUNT WHERE BANK_ID = ?");
        selectStatement.setInt(1, bankId);
        ResultSet resultSet = selectStatement.executeQuery();
        resultSet.next();
        int previousAmount = resultSet.getInt(1);
        resultSet.close();
        selectStatement.close();


        int newAmount = previousAmount - amount;
        PreparedStatement updateStatement = connection.prepareStatement("UPDATE BANK_ACCOUNT SET BANK_AMOUNT = ? WHERE BANK_ID = ?");
        updateStatement.setInt(1, newAmount);
        updateStatement.setInt(2, bankId);
        updateStatement.execute();

        updateStatement.close();

        System.out.println("withdraw() end : " + Thread.currentThread().getName());

    }

}
