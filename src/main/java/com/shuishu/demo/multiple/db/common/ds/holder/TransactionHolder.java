package com.shuishu.demo.multiple.db.common.ds.holder;


import com.shuishu.demo.multiple.db.common.ds.proxy.ConnectionProxy;
import com.zaxxer.hikari.util.IsolationLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 23:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：事务上下文
 * <p></p>
 */
@Setter
@Getter
@ToString
public class TransactionHolder {
    /**
     * 是否开启了一个MultiTransaction
     */
    private boolean open;
    /**
     * 是否只读事务
     */
    private boolean readOnly;
    /**
     * 事务隔离级别
     */
    private IsolationLevel isolationLevel;
    /**
     * 维护当前线程事务ID和连接关系
     */
    private ConcurrentHashMap<String, ConnectionProxy> connectionMap;
    /**
     * 事务执行栈
     */
    private Stack<String> executeStack;
    /**
     * 数据源切换栈
     */
    private Stack<String> datasourceKeyStack;
    /**
     * 主事务ID
     */
    private String mainTransactionId;
    /**
     * 执行次数
     */
    private AtomicInteger transCount;
    /**
     * 事务和数据源key关系
     */
    private ConcurrentHashMap<String, String> executeIdDatasourceKeyMap;

    public void addCount() {
        this.transCount.incrementAndGet();
    }

}
