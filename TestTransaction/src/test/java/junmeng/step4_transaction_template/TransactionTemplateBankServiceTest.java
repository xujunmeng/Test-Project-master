package junmeng.step4_transaction_template;

import junmeng.BankFixture;
import org.junit.Test;

import java.sql.SQLException;
import static junit.framework.Assert.assertEquals;

/**
 * Created by james on 2017/9/12.
 */
public class TransactionTemplateBankServiceTest extends BankFixture
{
    @Test
    public void transferSuccess() throws SQLException
    {
        TransactionTemplateBankService transactionTemplateBankService = new TransactionTemplateBankService(dataSource);
        transactionTemplateBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException
    {
        TransactionTemplateBankService transactionTemplateBankService = new TransactionTemplateBankService(dataSource);

        int toNonExistId = 3333;
        transactionTemplateBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }
}
