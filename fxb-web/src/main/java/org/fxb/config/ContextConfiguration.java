package org.fxb.config;

import org.fxb.web.module.ModuleContextManager;
import org.fxb.web.module.beans.factory.ModuleContextFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 1. 12.
 */
@Configuration
@ComponentScan(
  basePackages = "org.fxb.app",
  useDefaultFilters = false,
  includeFilters = {
    @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {
      Configuration.class,
      Service.class,
      Repository.class
    })
  }
)
public class ContextConfiguration {

//  @Bean
//  public ModuleContextManager moduleContextManager() throws Exception {
//    ModuleContextFactoryBean moduleContextFactoryBean = new ModuleContextFactoryBean();
//    moduleContextFactoryBean.setBasePackages("org.fxb.app");
//    return moduleContextFactoryBean.getObject();
//  }
}
