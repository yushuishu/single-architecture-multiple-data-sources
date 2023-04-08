package com.shuishu.demo.multiple.db.common.ds.service;


import javax.sql.DataSource;
import java.util.Collection;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 22:15
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：动态数据源处理
 * <p></p>
 */
public interface DynamicDataSourceService {
    /**
     * 添加数据源
     *
     * @param key        key
     * @param dataSource dataSource
     */
    void put(String key, DataSource dataSource);

    /**
     * 获取数据源
     *
     * @param key key
     * @return 数据源
     */
    DataSource get(String key);

    /**
     * 是否存在数据源
     *
     * @param key key
     * @return true存在 false不存在
     */
    Boolean hasDataSource(String key);

    /**
     * 移除数据源
     *
     * @param key key
     */
    void remove(String key);

    /**
     * 关闭数据源
     *
     * @param key key
     */
    void closeDataSource(String key);

    /**
     * 获取所有数据源
     *
     * @return 所有数据源
     */
    Collection<DataSource> getAll();
}
