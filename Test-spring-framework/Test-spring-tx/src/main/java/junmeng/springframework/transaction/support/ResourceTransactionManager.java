package junmeng.springframework.transaction.support;

import junmeng.springframework.transaction.PlatformTransactionManager;

/**
 *
 * 指示本地资源事务管理器的{@link org.springframework.transaction.PlatformTransactionManager}接口的扩展，在单个目标资源上运行。 这样的事务管理器与JTA事务管理器的不同之处在于它们不使用XA事务登记来获取公开数量的资源，而是专注于利用本地功能和简单的单个目标资源。
 * <p>此接口主要用于事务管理器的抽象内省，
 * 向客户提供他们已经获得什么类型的交易管理者以及交易经理操作的具体资源的暗示。
 *
 * Created by james on 2018/3/5.
 */
public interface ResourceTransactionManager extends PlatformTransactionManager {

    Object getResourceFactory();

}
