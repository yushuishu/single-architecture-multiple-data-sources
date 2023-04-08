package com.shuishu.demo.multiple.db.common.ds.transaction;


import com.shuishu.demo.multiple.db.common.ds.annotation.MultiTransaction;
import com.shuishu.demo.multiple.db.common.ds.holder.TransactionHolder;
import com.zaxxer.hikari.util.IsolationLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 23:37
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：数据源管理
 * <p></p>
 */
public class MultiTransactionManager {
    public static final ThreadLocal<TransactionHolder> TRANSACTION_HOLDER_THREAD_LOCAL = new ThreadLocal<>();


    /**
     * 切数据源，如果失败使用默认库
     *
     * @param point            -
     * @param signature        -
     * @param multiTransaction -
     * @return -
     */
    public boolean switchDataSource(ProceedingJoinPoint point, MethodSignature signature, MultiTransaction multiTransaction) {

        return false;
    }

    /**
     * 开启事务栈
     *
     * @param prevKey                 -
     * @param isolationLevel          -
     * @param readOnly                -
     * @param multiTransactionManager -
     * @return -
     */
    public TransactionHolder startTransaction(String prevKey, boolean readOnly, IsolationLevel isolationLevel, MultiTransactionManager multiTransactionManager) {
        TransactionHolder transactionHolder = new TransactionHolder();
        transactionHolder.setOpen(true);
        transactionHolder.setReadOnly(readOnly);
        transactionHolder.setIsolationLevel(isolationLevel);
        transactionHolder.getDatasourceKeyStack().push(prevKey);
        return transactionHolder;
    }

    /**
     * 提交事务
     */
    public void commit() {

    }

    /**
     * 回滚事务
     */
    public void rollback() {

    }

    public TransactionHolder getTrans() {
        return null;
    }

    /**
     * 关闭事务
     *
     * @param transId 事务id
     */
    public void close(String transId) {

    }
}
