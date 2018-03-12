package junmeng.step6_annotation;

import junmeng.BankService;
import junmeng.step3_connection_holder.ConnectionHolderBankDao;
import junmeng.step3_connection_holder.ConnectionHolderInsuranceDao;

import javax.sql.DataSource;

/**
 * Created by james on 2017/9/12.
 */
public class AnnotationBankService implements BankService {

    private ConnectionHolderBankDao connectionHolderBankDao;

    private ConnectionHolderInsuranceDao connectionHolderInsuranceDao;

    public AnnotationBankService(DataSource dataSource) {
        connectionHolderBankDao = new ConnectionHolderBankDao(dataSource);
        connectionHolderInsuranceDao = new ConnectionHolderInsuranceDao(dataSource);
    }

    @Transactional
    @Override
    public void transfer(int fromId, int toId, int amount) {
        try {

            connectionHolderBankDao.withdraw(fromId, amount);

            connectionHolderInsuranceDao.deposit(toId, amount);

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
