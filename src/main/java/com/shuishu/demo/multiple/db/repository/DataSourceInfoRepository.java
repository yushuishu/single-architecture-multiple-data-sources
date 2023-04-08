package com.shuishu.demo.multiple.db.repository;


import com.shuishu.demo.multiple.db.common.jdbc.BaseRepository;
import com.shuishu.demo.multiple.db.entity.po.DataSourceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：谁书-ss
 * @date ：2023-04-08 14:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Repository
public interface DataSourceInfoRepository extends BaseRepository<DataSourceInfo, Long> {
    /**
     * 获取所有 已启用 的数据源
     *
     * @param enable enable
     * @return 所有数据源
     */
    List<DataSourceInfo> findAllByEnable(Boolean enable);
}
