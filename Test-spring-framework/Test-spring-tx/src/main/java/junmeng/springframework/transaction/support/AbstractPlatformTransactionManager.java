package junmeng.springframework.transaction.support;

import junmeng.springframework.lang.Nullable;
import junmeng.springframework.transaction.PlatformTransactionManager;
import junmeng.springframework.transaction.TransactionDefinition;
import junmeng.springframework.transaction.TransactionException;
import junmeng.springframework.transaction.TransactionStatus;

import java.io.Serializable;

/**
 *
 * 实现Spring标准事务工作流的抽象基类，
 * 作为具体平台事务管理的基础
 * {@link org.springframework.transaction.jta.JtaTransactionManager}。
 * <p>此基类提供以下工作流程处理：
 * <UL>
 * <li>确定是否存在现有的事务;
 * <li>应用适当的传播行为;
 * <li>必要时暂停和恢复事务;
 * <li>在提交时检查仅回滚标志;
 * <li>在回滚中应用适当的修改
 * （实际回滚或设置回滚）;
 * <li>触发注册的同步回调
 * （如果事务同步处于活动状态）。
 * </ UL>
 * <p>子类必须为特定的实现特定的模板方法
 * 事务的状态，例如：开始，暂停，恢复，提交，回滚。
 * 其中最重要的是抽象的，必须由具体的提供
 * 实施;其余的，默认提供，所以重写是可选的。
 * 事务同步是注册回调的通用机制
 * 在事务完成时调用。这主要用于内部
 * 由JDBC，Hibernate，JPA等数据访问支持类运行时
 * 在JTA交易中：他们注册在JTA中打开的资源
 * 在事务完成时结束事务，允许例如重用
 * 同一个Hibernate Session在事务中。同样的机制可以
 * 也可用于应用程序中的自定义同步需求。
 * <p>这个类的状态是可序列化的，以允许序列化
 * 交易策略以及携带交易拦截器的代理。
 * 如果他们希望使自己的状态可序列化，那么就要由子类来决定了。
 * 他们应该在中实现{@code java.io.Serializable}标记接口
 * 这种情况，并可能是一个私有的readObject（）方法（根据
 * 到Java序列化规则）如果他们需要恢复任何瞬态状态。
 *
 * Created by james on 2018/3/5.
 */
public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager, Serializable {

    public final TransactionStatus getTransaction(@Nullable TransactionDefinition definition) throws TransactionException {
        Object transaction = doGetTransaction();
        return null;
    }

    protected abstract Object doGetTransaction() throws TransactionException;


}
