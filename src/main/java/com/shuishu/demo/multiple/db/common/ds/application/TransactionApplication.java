package com.shuishu.demo.multiple.db.common.ds.application;


import com.shuishu.demo.multiple.db.common.ds.transaction.MultiTransactionManager;

/**
 * @author ：谁书-ss
 * @date ：2023-04-08 10:25
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：应用事务
 * <p></p>
 */
public class TransactionApplication {

    public static MultiTransactionManager resolve(String transactionManager) {
        MultiTransactionManager multiTransactionManager = new MultiTransactionManager();
        return multiTransactionManager;
    }

}
