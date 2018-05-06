package com.gstart.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-01-31 13:44
 */
public class BaseServiceImpl implements BaseService {

    private final static Logger logger = LoggerFactory.getLogger(BaseService.class);

    public BaseServiceImpl() {
        logger.info("Service init : " + this.getClass().getName());
    }
}
