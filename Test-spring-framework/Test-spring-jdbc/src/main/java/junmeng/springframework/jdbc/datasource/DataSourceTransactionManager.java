package junmeng.springframework.jdbc.datasource;

import junmeng.springframework.transaction.TransactionException;
import junmeng.springframework.transaction.TransactionStatus;
import junmeng.springframework.transaction.support.AbstractPlatformTransactionManager;
import junmeng.springframework.transaction.support.ResourceTransactionManager;
import junmeng.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.sql.DataSource;

/**
 * Created by james on 2018/3/5.
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager implements ResourceTransactionManager, InitializingBean{

    @Nullable
    private DataSource dataSource;

    private boolean savepointAllowed = false;

    @Override
    protected Object doGetTransaction() throws TransactionException {
        DataSourceTransactionManager txObject = new DataSourceTransactionManager();
        txObject.setSavepointAllowed(isNestedTransactionAllowed());
        return null;
    }

    public void commit(TransactionStatus status) throws TransactionException {

    }

    public void rollback(TransactionStatus status) throws TransactionException {

    }

    public Object getResourceFactory() {
        return obtainDataSource();
    }

    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        Assert.state(dataSource != null, "No DataSource set");
        return dataSource;
    }

    @Nullable
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
