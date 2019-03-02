package com.gstart.common;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 14:55 2019/2/27
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@SpringBootApplication
/*
@ComponentScan(includeFilters ={
@ComponentScan.Filter(type=FilterType.REGEX,
        pattern="com\\.gstart\\.demo\\.repository\\.*"),
@ComponentScan.Filter(type=FilterType.REGEX,
        pattern="com\\.gstart\\.*\\.service\\.*\\..*")})*/
public interface BaseApplication {
}
