package junmeng.springframework.transaction;

import junmeng.springframework.lang.Nullable;

import java.sql.Connection;

/**
 *
 * 定义Spring兼容事务属性的接口。
 * 基于类似于EJB CMT属性的传播行为定义。
 * <p>请注意，隔离级别和超时设置不会被应用，除非实际的新事务开始。 由于只有{@link #PROPAGATION_REQUIRED}，
 * {@link #PROPAGATION_REQUIRES_NEW}和{@link #PROPAGATION_NESTED}可能导致这种情况，在其他情况下指定这些设置通常没有意义。
 * 此外，请注意，并非所有事务管理器都支持这些高级功能，因此可能会在给定时引发相应的异常
 * 非默认值。
 * <@> #isReadOnly（）只读标志}适用于任何事务上下文，
 * 无论是由实际资源事务支持还是在资源级别以非事务方式操作。 在后一种情况下，该标志仅适用于应用程序内的托管资源，如Hibernate {Session Session}。
 * Created by james on 2018/3/3.
 */
public interface TransactionDefinition {

    int PROPAGATION_REQUIRED = 0;

    int PROPAGATION_SUPPORTS = 1;

    int PROPAGATION_MANDATORY = 2;

    int PROPAGATION_REQUIRES_NEW = 3;

    int PROPAGATION_NOT_SUPPORTED = 4;

    int PROPAGATION_NEVER = 5;

    int PROPAGATION_NESTED = 6;

    int ISOLATION_DEFAULT = -1;

    int ISOLATION_READ_UNCOMMITTED = Connection.TRANSACTION_READ_UNCOMMITTED;

    int ISOLATION_READ_COMMITTED = Connection.TRANSACTION_READ_COMMITTED;

    int ISOLATION_REPEATABLE_READ = Connection.TRANSACTION_REPEATABLE_READ;

    int ISOLATION_SERIALIZABLE = Connection.TRANSACTION_SERIALIZABLE;

    int TIMEOUT_DEFAULT = -1;

    /**
     * 返回传播行为
     */
    int getPropagationBehavior();

    /**
     * 返回隔离级别
     */
    int getIsolationLevel();

    int getTimeout();

    boolean isReadOnly();

    @Nullable
    String getName();

}
