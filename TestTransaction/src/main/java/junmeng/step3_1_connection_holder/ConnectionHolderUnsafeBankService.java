package junmeng.step3_1_connection_holder;

import junmeng.BankService;
import junmeng.step3_connection_holder.TransactionManager;

import javax.sql.DataSource;

/**
 * Created by james on 2017/9/11.
 */
public class ConnectionHolderUnsafeBankService implements BankService {

    private TransactionManagerUnsafe transactionManagerUnsafe;

    private ConnectionHolderUnsafeBankDao connectionHolderUnsafeBankDao;

    private ConnectionHolderUnsafeInsuranceDao connectionHolderUnsafeInsuranceDao;

    public ConnectionHolderUnsafeBankService(DataSource dataSource) {

        transactionManagerUnsafe = new TransactionManagerUnsafe(dataSource);

        connectionHolderUnsafeBankDao = new ConnectionHolderUnsafeBankDao(dataSource);

        connectionHolderUnsafeInsuranceDao = new ConnectionHolderUnsafeInsuranceDao(dataSource);

    }

    @Override
    public void transfer(int fromId, int toId, int amount) {
        try {

            System.out.println("transfer() start() begin : " + Thread.currentThread().getName());

            transactionManagerUnsafe.start();

            System.out.println("transfer() start() end : " + Thread.currentThread().getName());

            System.out.println("transfer() withdraw() begin : " + Thread.currentThread().getName());

            connectionHolderUnsafeBankDao.withdraw(fromId, amount);

            System.out.println("transfer() withdraw() end : " + Thread.currentThread().getName());

            System.out.println("transfer() deposit() begin : " + Thread.currentThread().getName());

            connectionHolderUnsafeInsuranceDao.deposit(toId, amount);

            System.out.println("transfer() deposit() end : " + Thread.currentThread().getName());

            System.out.println("transfer() commit() begin : " + Thread.currentThread().getName());

            transactionManagerUnsafe.commit();

            System.out.println("transfer() commit() end : " + Thread.currentThread().getName());

        } catch (Exception e) {

            System.out.println("transfer() rollback() begin : " + Thread.currentThread().getName());

            transactionManagerUnsafe.rollback();

            System.out.println("transfer() rollback() end : " + Thread.currentThread().getName());

        } finally {

            System.out.println("transfer() close() begin : " + Thread.currentThread().getName());

            transactionManagerUnsafe.close();

            System.out.println("transfer() close() end : " + Thread.currentThread().getName());

        }
    }
}
