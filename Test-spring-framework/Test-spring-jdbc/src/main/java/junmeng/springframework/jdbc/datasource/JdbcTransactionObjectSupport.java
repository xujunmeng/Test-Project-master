package junmeng.springframework.jdbc.datasource;

import junmeng.springframework.transaction.SavepointManager;
import junmeng.springframework.transaction.support.SmartTransactionObject;

/**
 * Created by james on 2018/3/5.
 */
public abstract class JdbcTransactionObjectSupport implements SavepointManager, SmartTransactionObject {
}
