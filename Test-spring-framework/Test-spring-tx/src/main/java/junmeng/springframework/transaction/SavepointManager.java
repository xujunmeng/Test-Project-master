package junmeng.springframework.transaction;

/**
 * 指定API以通用方式以编程方式管理事务保存点的接口。 由TransactionStatus扩展以公开特定事务的保存点管理功能。
 * 请注意，保存点只能在活动事务中使用。 只需使用此编程保存点处理即可满足高级需求;
 * 否则，使用PROPAGATION_NESTED的子事务更可取。
 * <p>该接口受JDBC 3.0的Savepoint机制的启发，但独立于任何特定的持久性技术。
 *
 * Created by james on 2018/3/3.
 */
public interface SavepointManager {

    Object createSavepoint() throws TransactionException;

    void rollbackToSavepoint(Object savepoint) throws TransactionException;

    void releaseSavepoint(Object savepoint) throws TransactionException;

}
