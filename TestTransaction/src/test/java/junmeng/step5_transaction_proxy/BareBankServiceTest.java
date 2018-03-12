package junmeng.step5_transaction_proxy;

import junmeng.BankFixture;
import junmeng.BankService;
import junmeng.step3_connection_holder.TransactionManager;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by james on 2017/9/12.
 */
public class BareBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws SQLException {
        TransactionEnabledProxyManager transactionEnabledProxyManager = new TransactionEnabledProxyManager(new TransactionManager(dataSource));
        Object bankService = new BareBankService(dataSource);
        BankService proxyBankService = (BankService) transactionEnabledProxyManager.proxyFor(bankService);
        proxyBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException {

        TransactionManager transactionManager = new TransactionManager(dataSource);

        TransactionEnabledProxyManager transactionEnabledProxyManager = new TransactionEnabledProxyManager(transactionManager);

        Object bankService = new BareBankService(dataSource);

        BankService proxyBankService = (BankService) transactionEnabledProxyManager.proxyFor(bankService);

        int toNonExistId = 3333;
        proxyBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }


}
