package junmeng.step3_1_connection_holder;

import junmeng.BankFixture;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static junit.framework.Assert.assertEquals;

/**
 * Created by james on 2017/9/14.
 */
public class ConnectionHolderUnsafeBankServiceTest extends BankFixture {

    private static ExecutorService executor = Executors.newFixedThreadPool(20);

    @Test
    public void mutliThread() throws InterruptedException {
        ConnectionHolderUnsafeBankService connectionHolderUnsafeBankService = new ConnectionHolderUnsafeBankService(dataSource);

        List<Callable<Object>> list = new ArrayList<>();
        list.add(method(connectionHolderUnsafeBankService));
        list.add(method(connectionHolderUnsafeBankService));
        list.add(method(connectionHolderUnsafeBankService));
        list.add(method(connectionHolderUnsafeBankService));
        list.add(method(connectionHolderUnsafeBankService));
        executor.invokeAll(list);

    }

    public Callable method(ConnectionHolderUnsafeBankService connectionHolderUnsafeBankService) {
        Callable callable = () -> {
            System.out.println("method() : " + Thread.currentThread().getName());
            connectionHolderUnsafeBankService.transfer(1111, 2222, 200);
            return null;
        };
        return callable;
    }

    @Test
    public void transferSuccess() throws SQLException {

        ConnectionHolderUnsafeBankService connectionHolderUnsafeBankService = new ConnectionHolderUnsafeBankService(dataSource);
        connectionHolderUnsafeBankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));

    }

    @Test
    public void transferFailure() throws SQLException {
        ConnectionHolderUnsafeBankService connectionHolderUnsafeBankService = new ConnectionHolderUnsafeBankService(dataSource);

        int toNonExistId = 3333;
        connectionHolderUnsafeBankService.transfer(1111, toNonExistId, 200);

        assertEquals(1000, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));

    }

}
