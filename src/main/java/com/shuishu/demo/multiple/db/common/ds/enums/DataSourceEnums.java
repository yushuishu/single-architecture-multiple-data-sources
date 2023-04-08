package com.shuishu.demo.multiple.db.common.ds.enums;


import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ：谁书-ss
 * @date ：2023-04-08 14:35
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：数据源枚举
 * <p></p>
 */
public interface DataSourceEnums {

    enum Type{
        /**
         * 数据源类型
         */
        MYSQL,
        POSTGRESQL;

        public static boolean assertType(String type) {
            if (!StringUtils.hasText(type)) {
                return false;
            }
            return Arrays.stream(values()).map(Enum::name).toList().contains(type);
        }

        Type() {
        }

        public static Type getType(String type) {
            if (StringUtils.hasText(type)) {
                for (Type value : values()) {
                    if (value.name().equals(type)) {
                        return value;
                    }
                }
            }
            return null;
        }
    }

}
