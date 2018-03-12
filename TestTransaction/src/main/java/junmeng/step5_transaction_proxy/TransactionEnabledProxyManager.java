package junmeng.step5_transaction_proxy;

import junmeng.step3_connection_holder.TransactionManager;

import java.lang.reflect.Proxy;

/**
 * Created by james on 2017/9/12.
 */
public class TransactionEnabledProxyManager {

    private TransactionManager transactionManager;

    public TransactionEnabledProxyManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object proxyFor(Object object) {

        ClassLoader classLoader = object.getClass().getClassLoader();

        Class<?>[] interfaces = object.getClass().getInterfaces();

        TransactionInvocationHandler transactionInvocationHandler = new TransactionInvocationHandler(object, transactionManager);

        Object obj = Proxy.newProxyInstance(classLoader, interfaces, transactionInvocationHandler);

        return obj;
    }
}
