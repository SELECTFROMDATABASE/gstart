package com.gstart.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public BaseController() {
        logger.info("Controller init : " + this.getClass().getName());
    }

    @ExceptionHandler
    public String exceptionHandle(Exception exception){
        logger.error(this.getClass().getName() + " error：" + exception.getMessage());
        exception.printStackTrace();
        return "/error.jsp";
    }
}
