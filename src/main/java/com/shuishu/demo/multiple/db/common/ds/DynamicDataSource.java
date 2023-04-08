package com.shuishu.demo.multiple.db.common.ds;


import com.shuishu.demo.multiple.db.common.ds.holder.DataSourceContextHolder;
import com.shuishu.demo.multiple.db.common.ds.holder.TransactionHolder;
import com.shuishu.demo.multiple.db.common.ds.proxy.ConnectionProxy;
import com.shuishu.demo.multiple.db.common.ds.service.DynamicDataSourceService;
import com.shuishu.demo.multiple.db.common.ds.transaction.MultiTransactionManager;
import org.springframework.jdbc.datasource.AbstractDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 21:55
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：动态数据源
 * <p></p>
 */
public class DynamicDataSource extends AbstractDataSource implements DynamicDataSourceService {
    public final Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();
    private DataSource defaultDataSource;

    public void setDefaultTargetDataSource(DataSource dataSource) {
        this.defaultDataSource = dataSource;
    }

    public DataSource determineTargetDataSource() {
        String lookupKey = DataSourceContextHolder.getKey();
        DataSource dataSource = Optional.ofNullable(lookupKey)
                .map(dataSourceMap::get)
                .orElse(defaultDataSource);
        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine DataSource for lookup key [" + lookupKey + "]");
        }
        return dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        TransactionHolder transactionHolder = MultiTransactionManager.TRANSACTION_HOLDER_THREAD_LOCAL.get();
        if (Objects.isNull(transactionHolder)) {
            return determineTargetDataSource().getConnection();
        }
        ConnectionProxy ConnectionProxy = transactionHolder.getConnectionMap()
                .get(transactionHolder.getExecuteStack().peek());
        if (ConnectionProxy == null) {
            // 没开跨库事务，直接返回
            return determineTargetDataSource().getConnection();
        } else {
            transactionHolder.addCount();
            // 开了跨库事务，从当前线程中拿包装过的Connection
            return ConnectionProxy;
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public void put(String key, DataSource dataSource) {

    }

    @Override
    public DataSource get(String key) {
        return null;
    }

    @Override
    public Boolean hasDataSource(String key) {
        return null;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void closeDataSource(String key) {

    }

    @Override
    public Collection<DataSource> getAll() {
        return null;
    }
}
