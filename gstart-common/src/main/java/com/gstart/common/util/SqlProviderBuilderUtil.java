package com.gstart.common.util;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-01 15:05
 */
public class SqlProviderBuilderUtil {
    public static String buildGetUsersByName(final String name) {
        return new SQL(){{
            SELECT("*");
            FROM("users");
            if (name != null) {
                WHERE("name like #{value} || '%'");
            }
            ORDER_BY("id");
        }}.toString();
    }
}
