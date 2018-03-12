package junmeng.step3_connection_holder;

import junmeng.BankFixture;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by james on 2017/9/12.
 */
public class ConnectionHolderBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws SQLException {

        ConnectionHolderBankService connectionHolderBankService = new ConnectionHolderBankService(dataSource);
        connectionHolderBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException {
        ConnectionHolderBankService connectionHolderBankService = new ConnectionHolderBankService(dataSource);

        int toNonExistId = 3333;
        connectionHolderBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));

    }

}
