package junmeng.step3_2_connection_holder;

import junmeng.BankService;

import javax.sql.DataSource;

/**
 * Created by james on 2017/9/14.
 */
public class ConnectionHolderSafeBankService implements BankService {

    private TransactionManagerSafe transactionManagerSafe;

    private ConnectionHolderSafeBankDao connectionHolderSafeBankDao;

    private ConnectionHolderSafeInsuranceDao connectionHolderSafeInsuranceDao;

    public ConnectionHolderSafeBankService(DataSource dataSource) {

        transactionManagerSafe = new TransactionManagerSafe(dataSource);

        connectionHolderSafeBankDao = new ConnectionHolderSafeBankDao(dataSource);

        connectionHolderSafeInsuranceDao = new ConnectionHolderSafeInsuranceDao(dataSource);

    }

    @Override
    public void transfer(int fromId, int toId, int amount) {
        try {

            System.out.println("transfer() start() begin : " + Thread.currentThread().getName());

            transactionManagerSafe.start();

            System.out.println("transfer() start() end : " + Thread.currentThread().getName());

            System.out.println("transfer() withdraw() begin : " + Thread.currentThread().getName());

            connectionHolderSafeBankDao.withdraw(fromId, amount);

            System.out.println("transfer() withdraw() end : " + Thread.currentThread().getName());

            System.out.println("transfer() deposit() begin : " + Thread.currentThread().getName());

            connectionHolderSafeInsuranceDao.deposit(toId, amount);

            System.out.println("transfer() deposit() end : " + Thread.currentThread().getName());

            System.out.println("transfer() commit() begin : " + Thread.currentThread().getName());

            transactionManagerSafe.commit();

            System.out.println("transfer() commit() end : " + Thread.currentThread().getName());

        } catch (Exception e) {

            System.out.println("transfer() rollback() begin : " + Thread.currentThread().getName());

            transactionManagerSafe.rollback();

            System.out.println("transfer() rollback() end : " + Thread.currentThread().getName());

        } finally {

            System.out.println("transfer() close() begin : " + Thread.currentThread().getName());

            transactionManagerSafe.close();

            System.out.println("transfer() close() end : " + Thread.currentThread().getName());

        }
    }

}
