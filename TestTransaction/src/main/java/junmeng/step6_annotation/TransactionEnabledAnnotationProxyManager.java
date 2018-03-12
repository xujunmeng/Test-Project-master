package junmeng.step6_annotation;

import junmeng.step3_connection_holder.TransactionManager;

import java.lang.reflect.Proxy;

/**
 * Created by james on 2017/9/12.
 */
public class TransactionEnabledAnnotationProxyManager {

    private TransactionManager transactionManager;

    public TransactionEnabledAnnotationProxyManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object proxyFor(Object object) {

        ClassLoader classLoader = object.getClass().getClassLoader();

        Class<?>[] interfaces = object.getClass().getInterfaces();

        AnnotationTransactionInvocationHandler annotationTransactionInvocationHandler = new AnnotationTransactionInvocationHandler(object, transactionManager);

        Object obj = Proxy.newProxyInstance(classLoader, interfaces, annotationTransactionInvocationHandler);

        return obj;
    }
}
