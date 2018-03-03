package org.fxb.module;

import org.fxb.module.config.bean.factory.ModuleContextFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 4.
 */
@Configuration
public class TestConfiguration {
  @Autowired
  private Environment environment;

  @Bean
  public ModuleContextManager moduleContextManager() throws Exception {
    ModuleContextFactoryBean moduleContextFactoryBean = new ModuleContextFactoryBean();
    moduleContextFactoryBean.setBasePackages(environment.getProperty("moduleContext.basePackages"));
    moduleContextFactoryBean.afterPropertiesSet();
    return moduleContextFactoryBean.getObject();
  }
}
