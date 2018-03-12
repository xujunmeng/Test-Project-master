package junmeng.springframework.transaction;

import junmeng.springframework.lang.Nullable;

/**
 *
 * 这是Spring事务基础架构的中心界面。
 * 应用程序可以直接使用它，但它主要不是API：
 * 通常情况下，应用程序将通过AOP与TransactionTemplate或声明式事务一起工作。
 * 对于实现者，建议从提供的类派生，该类预先实现定义的传播行为并负责事务同步处理。 子类必须为其实现模板方法
 * 基础事务的具体状态，
 * 例如：开始，暂停，恢复，提交。
 * 这个策略接口的默认实现是
 * {@link org.springframework.transaction.jta.JtaTransactionManager}和
 * {@link org.springframework.jdbc.datasource.DataSourceTransactionManager}，
 * 这可以作为其他事务策略的实施指南。
 *
 * Created by james on 2018/3/3.
 */
public interface PlatformTransactionManager {

    TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;


}
