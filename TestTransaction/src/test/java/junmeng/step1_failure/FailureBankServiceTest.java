package junmeng.step1_failure;

import junmeng.BankFixture;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by james on 2017/9/5.
 */
public class FailureBankServiceTest extends BankFixture {

    @Test
    public void test() throws SQLException {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setFailureBankDao(failureBankDao);
        bankService.setFailureInsuranceDao(failureInsuranceDao);

        bankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));
    }

}
