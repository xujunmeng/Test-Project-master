package com.aihuishou.service.extension.readwrite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by james on 2017/6/29.
 */
public class ReadWriteRoutingDataSource extends AbstractDataSource implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(ReadWriteRoutingDataSource.class);

    private DataSource writeDataSource;

    private Map<String, DataSource> readDataSourceMap;

    private String[] readDataSourceNames;

    private DataSource[] readDataSources;

    private int readDataSourceCount;

    /**
     * AtomicInteger，一个提供原子操作的Integer的类。在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。
     * 而AtomicInteger则通过一种线程安全的加减操作接口。
     */
    private AtomicInteger counter = new AtomicInteger(1);

    @Override
    public void afterPropertiesSet() throws Exception {

        if (writeDataSource == null) {
            throw new IllegalArgumentException("property 'writeDataSource' is required");
        }
        if (CollectionUtils.isEmpty(readDataSourceMap)) {
            throw new IllegalArgumentException("property 'readDataSourceMap' is required");
        }

        readDataSourceCount = readDataSourceMap.size();

        readDataSources = new DataSource[readDataSourceCount];

        readDataSourceNames = new String[readDataSourceCount];

        int i = 0;
        for (Map.Entry<String, DataSource> entry : readDataSourceMap.entrySet()) {

            readDataSourceNames[i] = entry.getKey();

            readDataSources[i] = entry.getValue();

            i++;
        }
    }

    private DataSource determineDataSource() {
        if(ReadWriteDataSourceDecision.isChoiceWrite()) {
            log.debug("current determine is write datasource");
            return writeDataSource;
        }

        if(ReadWriteDataSourceDecision.isChoiceNone()) {
            log.debug("no choice read/write, default determine is write datasource");
            return writeDataSource;
        }
        return determineReadDataSource();
    }

    private DataSource determineReadDataSource() {
        //按照顺序选择读库
        //TODO 算法改进
        int index = counter.incrementAndGet() % readDataSourceCount;
        if(index < 0) {
            index = - index;
        }

        String dataSourceName = readDataSourceNames[index];

        log.debug("current determine read datasource : {}", dataSourceName);

        return readDataSources[index];
    }

    @Override
    public Connection getConnection() throws SQLException {
        return determineDataSource().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return determineDataSource().getConnection(username, password);
    }

    public void setReadDataSourceMap(Map<String, DataSource> readDataSourceMap) {
        this.readDataSourceMap = readDataSourceMap;
    }

    public void setWriteDataSource(DataSource writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

}
