package org.fxb.configure;

import org.fxb.resources.properties.bean.factory.ConfigFactoryBean;
import org.fxb.resources.properties.bean.factory.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 14.
 */
@Configuration
public class PropertiesConfiguration {

  @Bean
  public PropertiesFactoryBean properties() {
    String[] locations = new String[]{
        "org/fxb/config/default.properties",
        "org/fxb/config/fxb.properties",
        "classpath*:org/fxb/config/*.config.properties",
        "config.properties"
    };

    PropertiesFactoryBean bean = new PropertiesFactoryBean();
    bean.setLocations(locations);

    return bean;
  }

  @Bean
  public ConfigFactoryBean config() throws Exception {
    ConfigFactoryBean configFactoryBean = new ConfigFactoryBean(
        properties().getObject());
    return configFactoryBean;
  }
}
