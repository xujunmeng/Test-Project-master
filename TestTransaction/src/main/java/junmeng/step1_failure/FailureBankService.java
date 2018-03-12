package junmeng.step1_failure;

import junmeng.BankService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by james on 2017/9/5.
 */
public class FailureBankService implements BankService {

    private FailureBankDao failureBankDao;

    private FailureInsuranceDao failureInsuranceDao;

    private DataSource dataSource;

    public FailureBankService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void transfer(int fromId, int toId, int amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            failureBankDao.withDraw(fromId, amount);
            failureInsuranceDao.deposit(toId, amount);

            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void setFailureBankDao(FailureBankDao failureBankDao) {
        this.failureBankDao = failureBankDao;
    }

    public void setFailureInsuranceDao(FailureInsuranceDao failureInsuranceDao) {
        this.failureInsuranceDao = failureInsuranceDao;
    }
}
