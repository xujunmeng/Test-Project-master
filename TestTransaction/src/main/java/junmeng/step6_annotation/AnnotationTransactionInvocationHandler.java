package junmeng.step6_annotation;

import junmeng.step3_connection_holder.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by james on 2017/9/12.
 */
public class AnnotationTransactionInvocationHandler implements InvocationHandler {

    private Object proxied;

    private TransactionManager transactionManager;

    public AnnotationTransactionInvocationHandler(Object object, TransactionManager transactionManager) {

        this.proxied = object;

        this.transactionManager = transactionManager;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {

        Method originalMethod = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());

        boolean b = originalMethod.isAnnotationPresent(Transactional.class);

        if (!b) {
            return method.invoke(proxied, objects);
        }

        transactionManager.start();

        Object result = null;

        try {
            result = method.invoke(proxied, objects);
            transactionManager.commit();
        } catch (Exception e) {
            transactionManager.rollback();
        } finally {
            transactionManager.close();
        }

        return result;
    }
}
