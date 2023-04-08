package com.shuishu.demo.multiple.db.common.ds.annotation;




import com.zaxxer.hikari.util.IsolationLevel;

import java.lang.annotation.*;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 23:05
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ： 多事务注解
 * <p></p>
 * IsolationLevel:
 *      TRANSACTION_NONE(0), 表示不支持事务。
 *      TRANSACTION_READ_UNCOMMITTED(1), 脏读、不可重复读和幻影读。
 *      TRANSACTION_READ_COMMITTED(2), 禁止脏读;不可重复读取和幻影读取可能发生。
 *      TRANSACTION_REPEATABLE_READ(4), 禁止脏读和不可重复读;幻读可能发生。
 *      TRANSACTION_SERIALIZABLE(8), 禁止脏读、不可重复读和幻影读。
 *      TRANSACTION_SQL_SERVER_SNAPSHOT_ISOLATION_LEVEL(4096);
 *
 * 在使用 HikariCP创建新的数据库连接时，可以使用这些常量指定所需的隔离级别
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiTransaction {
    String transactionManager() default "multiTransactionManager";
    // 默认数据隔离级别，随数据库本身默认值
    IsolationLevel isolationLevel() default IsolationLevel.TRANSACTION_NONE;
    // 默认为主库数据源
    String datasourceId() default "default";
    // 只读事务，若有更新操作会抛出异常
    boolean readOnly() default false;
}
