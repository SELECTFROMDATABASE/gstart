package com.gstart.common.base;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-04 9:39
 */
public interface BaseDao<T> {
   Object insert(T bean);
}
