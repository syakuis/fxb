package org.fxb.module;

import org.fxb.module.bean.factory.ModuleContextManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2018. 3. 4.
 */
@Configuration
public class TestConfiguration {
  @Bean
  public ModuleContextManagerFactoryBean moduleContextManager() {
    ModuleContextManagerFactoryBean bean = new ModuleContextManagerFactoryBean();
    bean.setBasePackages("org.fxb.module.test");
    return bean;
  }
}
