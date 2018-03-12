package junmeng.step2_ugly;

import junmeng.BankFixture;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by james on 2017/9/12.
 */
public class UglyBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws SQLException {

        UglyBankService bankService = new UglyBankService(dataSource);
        UglyBankDao failureBankDao = new UglyBankDao();
        UglyInsuranceDao failureInsuranceDao = new UglyInsuranceDao();
        bankService.setUglyBankDao(failureBankDao);
        bankService.setUglyInsuranceDao(failureInsuranceDao);

        bankService.transfer(1111, 2222,200);
    }

    @Test
    public void transferFailure() throws SQLException {
        UglyBankDao failureBankDao = new UglyBankDao();
        UglyInsuranceDao failureInsuranceDao = new UglyInsuranceDao();

        UglyBankService bankService = new UglyBankService(dataSource);
        bankService.setUglyBankDao(failureBankDao);
        bankService.setUglyInsuranceDao(failureInsuranceDao);

        int toNonExistId = 3333;
        bankService.transfer(1111, toNonExistId,200);

        assertEquals(1000,getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }

}
