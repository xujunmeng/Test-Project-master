package junmeng.springframework.transaction.support;

import java.io.Flushable;

/**
 *
 * 接口由可以返回内部仅回滚标记的事务对象实现，通常来自另一个已参与并且标记为仅回滚的事务。
 * 由DefaultTransactionStatus自动检测，总是返回当前的rollbackOnly标志，即使不是由当前TransactionStatus产生的。
 *
 * Created by james on 2018/3/5.
 */
public interface SmartTransactionObject extends Flushable {

    boolean isRollbackOnly();

    void flush();
}
