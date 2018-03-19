package org.fxb.module.config;

import org.aspectj.lang.annotation.Aspect;
import org.fxb.context.mybatis.annotation.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 7.
 */
@Configuration
@ComponentScan(
    basePackages = "org.fxb.module",
    useDefaultFilters = false,
    includeFilters = {
        @Filter(type = FilterType.ANNOTATION, classes = {Component.class, Aspect.class, Mapper.class }),
    }
)
public class TestConfig {

}
