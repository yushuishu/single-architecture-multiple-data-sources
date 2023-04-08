package com.shuishu.demo.multiple.db.service.impl;


import com.shuishu.demo.multiple.db.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 13:01
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：测试动态数据源
 * <p></p>
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class TestServiceImpl implements TestService {
}
