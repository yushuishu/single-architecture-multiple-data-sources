package com.shuishu.demo.multiple.db.common.ds.aop;


import com.shuishu.demo.multiple.db.common.ds.annotation.MultiTransaction;
import com.shuishu.demo.multiple.db.common.ds.application.TransactionApplication;
import com.shuishu.demo.multiple.db.common.ds.holder.DataSourceContextHolder;
import com.shuishu.demo.multiple.db.common.ds.holder.TransactionHolder;
import com.shuishu.demo.multiple.db.common.ds.transaction.MultiTransactionManager;
import com.shuishu.demo.multiple.db.common.exception.BusinessException;
import com.zaxxer.hikari.util.IsolationLevel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 23:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：多数据源事务AOP
 * <p></p>
 */
@Slf4j
@Order(99999)
@Component
@Aspect
public class MultiTransactionAop {
    @Pointcut("@annotation(com.shuishu.demo.multiple.db.common.ds.annotation.MultiTransaction)")
    public void pointcut() {
        if (log.isDebugEnabled()) {
            log.debug("start in transaction pointcut...");
        }
    }


    @Around("pointcut()")
    public Object aroundTransaction(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 从切面中获取当前方法
        Method method = signature.getMethod();
        MultiTransaction multiTransaction = method.getAnnotation(MultiTransaction.class);
        if (multiTransaction == null) {
            return point.proceed();
        }
        IsolationLevel isolationLevel = multiTransaction.isolationLevel();
        boolean readOnly = multiTransaction.readOnly();
        String prevKey = DataSourceContextHolder.getKey();
        MultiTransactionManager multiTransactionManager = TransactionApplication.resolve(multiTransaction.transactionManager());
        // 切数据源，如果失败使用默认库
        if (multiTransactionManager.switchDataSource(point, signature, multiTransaction)) {
            return point.proceed();
        }
        // 开启事务栈
        TransactionHolder transactionHolder = multiTransactionManager.startTransaction(prevKey, readOnly, isolationLevel, multiTransactionManager);
        Object proceed;

        try {
            proceed = point.proceed();
            multiTransactionManager.commit();
        } catch (Throwable ex) {
            log.error("execute method:{}#{},err:", method.getDeclaringClass(), method.getName(), ex);
            multiTransactionManager.rollback();
            throw new BusinessException("系统异常：%s", ex.getMessage());
        } finally {
            // 当前事务结束出栈
            String transId = multiTransactionManager.getTrans().getExecuteStack().pop();
            transactionHolder.getDatasourceKeyStack().pop();
            // 恢复上一层事务
            DataSourceContextHolder.setKey(transactionHolder.getDatasourceKeyStack().peek());
            // 最后回到主事务，关闭此次事务
            multiTransactionManager.close(transId);
        }
        return proceed;

    }

}
