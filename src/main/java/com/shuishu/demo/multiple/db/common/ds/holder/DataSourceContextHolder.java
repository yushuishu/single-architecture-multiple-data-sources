package com.shuishu.demo.multiple.db.common.ds.holder;


/**
 * @author ：谁书-ss
 * @date ：2023-04-07 23:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：数据源上下文
 * <p></p>
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> DS_CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setKey(String key) {
        DS_CONTEXT_HOLDER.set(key);
    }

    public static String getKey() {
        return DS_CONTEXT_HOLDER.get();
    }

    public static void clear() {
        DS_CONTEXT_HOLDER.remove();
    }

}
