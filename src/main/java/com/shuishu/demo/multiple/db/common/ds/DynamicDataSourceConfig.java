package com.shuishu.demo.multiple.db.common.ds;


import com.shuishu.demo.multiple.db.common.exception.BusinessException;
import com.shuishu.demo.multiple.db.entity.po.DataSourceInfo;
import com.shuishu.demo.multiple.db.repository.DataSourceInfoRepository;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：谁书-ss
 * @date ：2023-04-08 11:43
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：动态数据源配置
 * <p></p>
 */
@Component
@Configuration
public class DynamicDataSourceConfig {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private long connectionTimeout;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private long idleTimeout;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private long maxLifetime;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;
    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;

    @Resource
    private DataSourceInfoRepository dataSourceInfoRepository;

    @Bean
    public DataSource masterDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMinimumIdle(minimumIdle);
        dataSource.setMaximumPoolSize(maximumPoolSize);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMaxLifetime(maxLifetime);
        dataSource.setConnectionTimeout(connectionTimeout);
        try (Connection c = dataSource.getConnection()) {
            c.close();
            return dataSource;
        } catch (Exception e) {
            throw new BusinessException("检查配置文件数据源无效，请检查配置（url、username、password）", e.getMessage());
        }
    }


    @Bean
    @Primary
    public DynamicDataSource initDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource());
        // 其它所有数据源
        List<DataSourceInfo> dataSourceInfoList = dataSourceInfoRepository.findAllByEnable(true);
        Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>(dataSourceInfoList.size() + 1);
        dataSourceMap.put("default", masterDataSource());
        for (DataSourceInfo dataSourceInfo : dataSourceInfoList) {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(dataSourceInfo.getJdbcUrl());
            dataSource.setUsername(dataSourceInfo.getUserName());
            dataSource.setPassword(dataSourceInfo.getPassWord());
            dataSource.setDriverClassName(dataSourceInfo.getDriverClassName());
            dataSource.setMinimumIdle(dataSourceInfo.getMinimumIdle() == null ? minimumIdle : dataSourceInfo.getMinimumIdle());
            dataSource.setMaximumPoolSize(dataSourceInfo.getMaximumPoolSize() == null ? maximumPoolSize : dataSourceInfo.getMaximumPoolSize());
            dataSource.setIdleTimeout(dataSourceInfo.getIdleTimeout() == null ? idleTimeout : dataSourceInfo.getIdleTimeout());
            dataSource.setMaxLifetime(dataSourceInfo.getMaxLifetime() == null ? maxLifetime : dataSourceInfo.getMaxLifetime());
            dataSource.setConnectionTimeout(dataSourceInfo.getConnectionTimeout() == null ? connectionTimeout : dataSourceInfo.getConnectionTimeout());
            dataSourceMap.put(dataSourceInfo.getCode(), dataSource);
        }
        dynamicDataSource.dataSourceMap.putAll(dataSourceMap);
        return dynamicDataSource;
    }

}
