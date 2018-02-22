package org.fxb.config;

import org.aspectj.lang.annotation.Aspect;
import org.fxb.web.module.ModuleContextManager;
import org.fxb.web.module.beans.factory.ModuleContextFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(
  basePackages = "org.fxb.app",
  useDefaultFilters = false,
  includeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
      Configuration.class, Aspect.class, Service.class, Repository.class,
    })
  }
)
public class ContextConfiguration {

  @Bean("moduleContextManager")
  public ModuleContextManager moduleContextManager() throws Exception {
    System.out.println("------------------ moduleContextManager");
    ModuleContextFactoryBean moduleContextFactoryBean = new ModuleContextFactoryBean();
    moduleContextFactoryBean.setBasePackages("org.fxb.app");
    moduleContextFactoryBean.afterPropertiesSet();
    return moduleContextFactoryBean.getObject();
  }
}
