package junmeng.step3_2_connection_holder;

import junmeng.BankFixture;
import junmeng.step3_1_connection_holder.ConnectionHolderUnsafeBankService;
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
public class ConnectionHolderSafeBankServiceTest extends BankFixture {

    private static ExecutorService executor = Executors.newFixedThreadPool(20);

    @Test
    public void mutliThread() throws InterruptedException {

        List<Callable<Object>> list = new ArrayList<>();
        list.add(method());
        list.add(method());
        list.add(method());
        list.add(method());
        list.add(method());
        executor.invokeAll(list);

    }

    public Callable method() {
        Callable callable = () -> {
            ConnectionHolderSafeBankService connectionHolderSafeBankService = new ConnectionHolderSafeBankService(dataSource);
            System.out.println("method() : " + Thread.currentThread().getName());
            //TODO 这里有问题，模拟多线程不应该是这样
            connectionHolderSafeBankService.transfer(1111, 2222, 200);
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
