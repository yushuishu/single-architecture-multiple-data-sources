package com.shuishu.demo.multiple.db.service;


import com.shuishu.demo.multiple.db.common.domain.PageDTO;
import com.shuishu.demo.multiple.db.entity.po.DataSourceInfo;
import org.springframework.data.domain.Page;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 12:59
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：数据源管理
 * <p></p>
 */
public interface DataSourceManageService {
    /**
     * 添加数据源
     *
     * @param dataSourceInfo -
     */
    void add(DataSourceInfo dataSourceInfo);

    /**
     * 分页查询
     *
     * @param name    数据源名称
     * @param type    数据源类型
     * @param pageDTO 分页参数
     * @return 分页数据
     */
    Page<DataSourceInfo> page(String name, String type, PageDTO pageDTO);

    /**
     * 数据源详情
     *
     * @param name 数据源名称
     * @return 数据源
     */
    DataSourceInfo details(String name);

    /**
     * 数据源更新
     *
     * @param dataSourceInfo -
     */
    void update(DataSourceInfo dataSourceInfo);

    /**
     * 删除
     *
     * @param name 数据源名称
     */
    void delete(String name);

    /**
     * 开启 或 关闭
     *
     * @param name 数据源名称
     */
    void enable(String name);
}
