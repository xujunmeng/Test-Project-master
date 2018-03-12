package junmeng.step4_transaction_template;

import junmeng.BankService;
import junmeng.step3_connection_holder.ConnectionHolderBankDao;
import junmeng.step3_connection_holder.ConnectionHolderInsuranceDao;

import javax.sql.DataSource;

/**
 * Created by james on 2017/9/12.
 */
public class TransactionTemplateBankService implements BankService {

    private DataSource dataSource;

    private ConnectionHolderBankDao connectionHolderBankDao;

    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public TransactionTemplateBankService(DataSource dataSource) {

        this.dataSource = dataSource;

        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);

        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);

    }

    @Override
    public void transfer(int fromId, int toId, int amount) {
        new TransactionTemplate(dataSource) {
            @Override
            protected void doJob() throws Exception {
                connectionHolderBankDao.withdraw(fromId, amount);
                connectionHolderInsuranceDao.deposit(toId, amount);
            }
        }.doJobInTransaction();
    }

}
