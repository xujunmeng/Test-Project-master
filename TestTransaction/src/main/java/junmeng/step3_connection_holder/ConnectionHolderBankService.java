package junmeng.step3_connection_holder;

import junmeng.BankService;

import javax.sql.DataSource;

/**
 * Created by james on 2017/9/11.
 */
public class ConnectionHolderBankService implements BankService {

    private TransactionManager transactionManager;

    private ConnectionHolderBankDao connectionHolderBankDao;

    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public ConnectionHolderBankService(DataSource dataSource) {

        transactionManager = new TransactionManager(dataSource);

        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);

        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);

    }

    @Override
    public void transfer(int fromId, int toId, int amount) {
        try {

            transactionManager.start();

            connectionHolderBankDao.withdraw(fromId, amount);

            connectionHolderInsuranceDao.deposit(toId, amount);

            transactionManager.commit();

        } catch (Exception e) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }
    }
}
