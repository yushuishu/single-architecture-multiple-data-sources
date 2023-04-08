package com.shuishu.demo.multiple.db.service.impl;


import com.shuishu.demo.multiple.db.common.domain.PageDTO;
import com.shuishu.demo.multiple.db.entity.po.DataSourceInfo;
import com.shuishu.demo.multiple.db.service.DataSourceManageService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 12:59
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：数据源管理
 * <p></p>
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DataSourceManageServiceImpl implements DataSourceManageService {
    @Override
    public void add(DataSourceInfo dataSourceInfo) {

    }

    @Override
    public Page<DataSourceInfo> page(String name, String type, PageDTO pageDTO) {
        return null;
    }

    @Override
    public DataSourceInfo details(String name) {
        return null;
    }

    @Override
    public void update(DataSourceInfo dataSourceInfo) {

    }

    @Override
    public void delete(String name) {

    }

    @Override
    public void enable(String name) {

    }
}
