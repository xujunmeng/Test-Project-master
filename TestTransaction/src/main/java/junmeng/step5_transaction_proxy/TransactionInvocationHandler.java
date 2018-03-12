package junmeng.step5_transaction_proxy;

import junmeng.step3_connection_holder.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by james on 2017/9/12.
 */
public class TransactionInvocationHandler implements InvocationHandler {

    private Object proxy;

    private TransactionManager transactionManager;

    public TransactionInvocationHandler(Object object, TransactionManager transactionManager) {

        this.proxy = object;
        this.transactionManager = transactionManager;

    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {

        transactionManager.start();

        Object result = null;

        try {

            result = method.invoke(proxy, args);

            transactionManager.commit();

        } catch (Exception e) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }
        return result;
    }
}
