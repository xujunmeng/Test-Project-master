package junmeng.springframework.transaction;

import java.io.Flushable;

/**
 *
 * 交易状态的表示。
 * 事务代码可以使用它来检索状态信息，
 * 并以编程方式请求回滚（而不是抛出导致隐式回滚的异常）。
 * <p>从SavepointManager接口派生以提供对保存点的访问
 * 管理设施。 请注意，保存点管理仅在基础事务管理器支持时才可用。
 *
 * Created by james on 2018/3/3.
 */
public interface TransactionStatus extends SavepointManager, Flushable {

    boolean isNewTransaction();

    boolean hasSavepoint();

    void setRollbackOnly();

    boolean isRollbackOnly();

    void flush();

    boolean isCompleted();

}
