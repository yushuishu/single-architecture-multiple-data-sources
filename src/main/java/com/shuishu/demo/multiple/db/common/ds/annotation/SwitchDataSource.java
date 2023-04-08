package com.shuishu.demo.multiple.db.common.ds.annotation;


import java.lang.annotation.*;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 22:39
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：切换数据源
 * <p></p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SwitchDataSource {
    String value();
}
